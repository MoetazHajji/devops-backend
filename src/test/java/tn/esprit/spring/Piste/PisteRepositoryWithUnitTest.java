package tn.esprit.spring.Piste;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.List;

import static org.junit.Assert.assertNotNull;
//mvn test -Dtest=PisteRepositoryTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
class PisteRepositoryWithUnitTest {

    @Autowired
    private IPisteRepository pisteRepository;
    Piste piste  = Piste.builder().numPiste(12L).namePiste("Piste-1").color(Color.RED).length(2).slope(100).build();



    @Test
    @Order(0)
    void ajouterPisteTest() {
        piste = pisteRepository.save(piste);
        log.info(piste.toString());
        Assertions.assertNotNull(piste.getNumPiste());
    }

    @Test
    @Order(1)
    void modifierPisteTest() {
        piste.setNamePiste("Piste-2");
        piste = pisteRepository.save(piste);
        log.info(piste.toString());
        Assertions.assertNotEquals(piste.getNamePiste(), "Piste-3");
    }

    @Test
    @Order(2)
    void listerPistes() {
        List<Piste> list = pisteRepository.findAll();
        log.info(list.size()+"");
        Assertions.assertTrue(list.size() > 0);
    }

   /* @Test
    @Order(3)
    public void chercherMagasinAvecId() {
        log.info(m.getId()+"");
        Magasin m1 = magasinRepository.findById(m.getId()).get();
        Assertions.assertEquals(m1.getAdresse(),m.getAdresse());
    }*/

    @Test
    @Order(4)
    void supprimerPiste() {
        pisteRepository.delete(piste);
    }

    @Test
    @Order(5)
     void compter() {
        long taille = pisteRepository.count();
        Assertions.assertEquals(taille,pisteRepository.findAll().size());
    }
}