package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.ICourseServices;
import tn.esprit.spring.services.InstructorServicesImpl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
class GestionStationSkiApplicationTests {

	@Autowired
	private InstructorServicesImpl instructorService;



	@Autowired
	private ICourseRepository courseRepository;
	@Autowired
	private IInstructorRepository instructorRepository;
	Instructor instructor = Instructor.builder()
			.firstName("nouha")
			.lastName("kouki")
			.dateOfHire(LocalDate.parse("1997-10-05"))
			.build();






	@Test
	@Order(0)
		public void addInstructorTest() {
			instructor = instructorRepository.save(instructor);
			log.info(instructor.toString());
	}

	@Test
	@Order(1)
	public void retrieveInstructorTest() {
		Long instructorId = 1L; // Provide an existing instructor ID from your test data
		Instructor instructor = instructorService.retrieveInstructor(instructorId);
		log.info("Retrieved Instructor: " + instructor);
		Assertions.assertNotNull(instructor);
	}

	@Test
	@Order(2)
	public void retrieveAllInstructorsTest() {
		List<Instructor> instructors = instructorService.retrieveAllInstructors();
		log.info("List of Instructors:");
		instructors.forEach(instructor -> log.info(instructor.toString()));
		Assertions.assertTrue(instructors.size() > 0);
	}

	@Test
	@Order(3)
	public void addInstructorAndAssignToCourseTest() {
		Long instructorId = 1L; // Provide an existing instructor ID from your test data
		Long courseId = 1L; // Provide an existing course ID from your test data

		Instructor instructor = instructorRepository.findById(instructorId).orElse(null);
		Course course = courseRepository.findById(courseId).orElse(null);

		Instructor updatedInstructor = instructorService.addInstructorAndAssignToCourse(instructor, courseId);
		log.info("Updated Instructor: " + updatedInstructor);
		Assertions.assertNotNull(updatedInstructor);
		Set<Course> assignedCourses = updatedInstructor.getCourses();
		Assertions.assertTrue(assignedCourses.contains(course));
	}
}