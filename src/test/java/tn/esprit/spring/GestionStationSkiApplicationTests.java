package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.ICourseServices;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class GestionStationSkiApplicationTests {



	@Mock
	private IInstructorRepository instructorRepository;

	@InjectMocks
	private InstructorServicesImpl instructorServices;

	@Mock
	private ICourseServices courseServices;
	@Mock
	private ICourseRepository courseRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddInstructor() {
		Instructor instructor = new Instructor();
		when(instructorRepository.save(instructor)).thenReturn(instructor);

		Instructor result = instructorServices.addInstructor(instructor);

		assertEquals(instructor, result);
	}

	@Test
	public void testRetrieveAllInstructors() {
		List<Instructor> instructorList = new ArrayList<>();
		when(instructorRepository.findAll()).thenReturn(instructorList);

		List<Instructor> result = instructorServices.retrieveAllInstructors();

		assertEquals(instructorList, result);
	}

	@Test
	public void testUpdateInstructor() {
		Instructor instructor = new Instructor();
		when(instructorRepository.save(instructor)).thenReturn(instructor);

		Instructor result = instructorServices.updateInstructor(instructor);

		assertEquals(instructor, result);
	}

	@Test
	public void testRetrieveInstructor() {
		Long numInstructor = 1L;
		Instructor instructor = new Instructor();
		when(instructorRepository.findById(numInstructor)).thenReturn(Optional.of(instructor));

		Instructor result = instructorServices.retrieveInstructor(numInstructor);

		assertEquals(instructor, result);
	}

	@Test
	public void testAddInstructorAndAssignToCourse() {
		Instructor instructor = new Instructor();
		Long numCourse = 1L;

		when(instructorRepository.save(instructor)).thenReturn(instructor);
		when(courseRepository.findById(numCourse)).thenReturn(Optional.of(new Course()));

		Instructor result = instructorServices.addInstructorAndAssignToCourse(instructor, numCourse);

		assertEquals(instructor, result);
	}
}