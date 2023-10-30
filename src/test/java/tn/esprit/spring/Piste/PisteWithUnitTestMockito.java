package tn.esprit.spring.Piste;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.IPisteServices;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PisteWithUnitTestMockito {

    @Mock
    IPisteRepository magasinRepo;
    //ou MagasinRepository magasinRepo = Mockito.mock(MagasinRepository.class);

    @InjectMocks
    IPisteServices magasinService;
    Piste piste  = Piste.builder().numPiste(12L).namePiste("Piste-1").color(Color.RED).length(2).slope(100).build();
    List<Piste> list= new ArrayList<Piste>() {
        {
            add(Piste.builder().numPiste(12L).namePiste("Piste-1").color(Color.RED).length(2).slope(100).build());
            add(Piste.builder().numPiste(12L).namePiste("Piste-2").color(Color.RED).length(2).slope(100).build());
        }
    };

    @Test
    public void addMagasinTest() {
        Mockito.when(magasinRepo.save(Mockito.any(Piste.class))).then(invocation -> {
            Piste model = invocation.getArgument(0, Piste.class);
            model.setNumPiste(1L);
            return model;
        });
        log.info("Avant ==> " + piste.toString());
        Piste newPiste = magasinService.addPiste(piste);
        assertSame(newPiste, piste);
        log.info("Après ==> " + piste.toString());
    }

    @Test
    public void retreiveMagasinTest() {
        Mockito.when(magasinRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(piste));
        Piste newPiste = magasinService.retrievePiste((long) 1566);
        assertNotNull(newPiste);
        log.info("get ==> " + newPiste.toString());
        verify(magasinRepo).findById(Mockito.anyLong());

    }

    @Test
    public void retreiveAllMagasinTest() {
        Mockito.when(magasinRepo.findAll()).thenReturn(list);
        List<Piste> list_piste = magasinService.retrieveAllPistes();
        assertNotNull(list_piste);
    }
}