package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Slf4j
@SpringBootTest
class RegistrationServicesImplTest {

    @Mock
    IRegistrationRepository registrationRepository;
    @Mock
    ISkierRepository skierRepository;
    @Mock
    ICourseRepository courseRepository;

    @InjectMocks
    RegistrationServicesImpl registrationServices;

    Skier s = Skier.builder().firstName("Skier").lastName("Skier").build();
    Course c = Course.builder().level(2).price(300.5f).build();
    Registration r = Registration.builder().numWeek(3).skier(s).course(c).build();

    List<Registration> registrationList = new ArrayList<Registration>(){
        {
            add(Registration.builder().numWeek(6).build());
            add(Registration.builder().numWeek(18).build());
        }
    };

    @Test
    void addRegistrationAndAssignToSkier() {

        Skier skier = new Skier();
        skier.setNumSkier(1L);
        Mockito.when(registrationRepository.save(Mockito.any(Registration.class))).then(invocation -> {
            Registration registration = invocation.getArgument(0, Registration.class);
            registration.setNumRegistration(1L);
            registration.setSkier(skier);
            return registration;
        });

        log.info("Avant ==> " + r.toString());
        Registration registration = registrationServices.addRegistrationAndAssignToSkier(r, s.getNumSkier());
        assertSame(registration, r);
        log.info("Après ==> " + r.toString());

    }

    @Test
    void numWeeksCourseOfInstructorBySupport() {

        Long numInstructor = 1L;
        Support support = Support.SKI;
        List<Integer> expectedWeeks = Arrays.asList(1, 2, 3);

        Mockito.when(registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support))
                .thenReturn(expectedWeeks);

        List<Integer> result = registrationServices.numWeeksCourseOfInstructorBySupport(numInstructor, support);
        assertEquals(expectedWeeks, result);
        log.info("get ==> " + result.toString());

    }

    @Test
    void numWeeksCourseOfInstructorBySupportWithNoResults() {

        Long numInstructor = 1L;
        Support support = Support.SNOWBOARD;
        List<Integer> expectedWeeks = Collections.emptyList();

        Mockito.when(registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support))
                .thenReturn(expectedWeeks);

        List<Integer> result = registrationServices.numWeeksCourseOfInstructorBySupport(numInstructor, support);
        verify(registrationRepository).numWeeksCourseOfInstructorBySupport(numInstructor, support);
        assertEquals(expectedWeeks, result);
        log.info("get ==> " + result.toString());
    }
}

