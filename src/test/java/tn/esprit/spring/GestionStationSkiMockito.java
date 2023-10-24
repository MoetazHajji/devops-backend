package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.SkierServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GestionStationSkiMockito {

    @Mock
    ISkierRepository skierRepository;

    @InjectMocks
    SkierServicesImpl skierServices;

    Skier s= Skier.builder().firstName("Ihebo").lastName("Ihebo").dateOfBirth(LocalDate.parse("1997-10-05")).city("Sidi bouzid").build();


    List<Skier> list= new ArrayList<Skier>() {
        {
            add(Skier.builder().firstName("lafi").lastName("lafi").dateOfBirth(LocalDate.parse("1997-10-05")).city("Sidi bouzid").build());
            add(Skier.builder().firstName("lafio").lastName("lafio").dateOfBirth(LocalDate.parse("1997-10-05")).city("Sidi bouzid").build());
        }
    };

    @Test
    public void addSkiertest() {
        Mockito.when(skierRepository.save(Mockito.any(Skier.class))).then(invocation -> {
            Skier model = invocation.getArgument(0, Skier.class);
            model.setNumSkier((long) 1);
            return model;
        });
        log.info("Avant ==> " + s.toString());
        Skier skier = skierServices.addSkier(s);
        assertSame(skier, s);
        log.info("Après ==> " + s.toString());
    }

    @Test
    public void retreiveSkierTest() {
        Mockito.when(skierRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
        Skier skier = skierServices.retrieveSkier((long) 1566);
        assertNotNull(skier);
        log.info("get ==> " + skier.toString());
        verify(skierRepository).findById(Mockito.anyLong());

    }

    @Test
    public void retrieveAllSkiersTest() {
        Mockito.when(skierRepository.findAll()).thenReturn(list);
        List<Skier> skiers = skierServices.retrieveAllSkiers();
        assertNotNull(skiers);
    }

}
