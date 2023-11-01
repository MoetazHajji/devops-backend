package tn.esprit.spring;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
public class InstructorServicesImplTest {

    @Autowired
    private InstructorServicesImpl instructorService;

    @Autowired
    private IInstructorRepository instructorRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Test
    void addInstructor() {
        Instructor instructor = new Instructor(); // Create an Instructor object with necessary details
        Instructor savedInstructor = instructorService.addInstructor(instructor);
        assertNotNull(savedInstructor.getNumInstructor());
    }

    @Test
    @DatabaseSetup("/data-set/instructor-data.xml")
    void retrieveInstructor() {
        Long instructorId = 1L; // Provide the ID of an existing instructor in your test data
        Instructor instructor = instructorService.retrieveInstructor(instructorId);
        assertNotNull(instructor);

    }

    @Test
    @DatabaseSetup("/data-set/instructor-data.xml")
    void retrieveAllInstructors() {
        List<Instructor> instructors = instructorService.retrieveAllInstructors();

        assertEquals(1, instructors.size());
    }

    @Test
    @DatabaseSetup("/data-set/instructor-data.xml")
    void addInstructorAndAssignToCourse() {
        Long instructorId = 1L;
        Long courseId = 1L;

        Instructor instructor = instructorRepository.findById(instructorId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        Instructor updatedInstructor = instructorService.addInstructorAndAssignToCourse(instructor, courseId);
        assertNotNull(updatedInstructor);

        Optional<Course> assignedCourse = updatedInstructor.getCourses().stream().findFirst();
        assertEquals(course, assignedCourse.orElse(null));
    }
}
