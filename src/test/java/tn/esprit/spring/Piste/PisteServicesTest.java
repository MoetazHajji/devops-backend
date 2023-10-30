package tn.esprit.spring.Piste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.IPisteServices;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//mvn test -Dtest=PisteServicesTest
public class PisteServicesTest {

    @Autowired
    private IPisteServices pisteServices;

    @Test
    public void testAddPiste() {
        Piste piste = new Piste();
        piste.setNamePiste("Test Piste");
        piste.setColor(Color.GREEN);
        piste.setLength(1000);
        piste.setSlope(25);
        Piste savedPiste = pisteServices.addPiste(piste);
        assertNotNull(savedPiste.getNumPiste());
    }

    @Test
    public void testRetrievePiste() {
        Piste piste = new Piste();
        piste.setNamePiste("Test Piste");
        piste.setColor(Color.GREEN);
        piste.setLength(1000);
        piste.setSlope(25);

        Piste savedPiste = pisteServices.addPiste(piste);
        Long numPiste = savedPiste.getNumPiste();

        Piste retrievedPiste = pisteServices.retrievePiste(numPiste);

        assertNotNull(retrievedPiste);
        assertEquals("Test Piste", retrievedPiste.getNamePiste());
    }

    @Test
    public void testRetrieveAllPistes() {
        Piste piste1 = new Piste();
        piste1.setNamePiste("Piste 1");
        piste1.setColor(Color.GREEN);
        piste1.setLength(1000);
        piste1.setSlope(25);

        Piste piste2 = new Piste();
        piste2.setNamePiste("Piste 2");
        piste2.setColor(Color.BLUE);
        piste2.setLength(1500);
        piste2.setSlope(30);

        pisteServices.addPiste(piste1);
        pisteServices.addPiste(piste2);

        List<Piste> pisteList = pisteServices.retrieveAllPistes();

        assertTrue(pisteList.size() >= 2);
    }

    @Test
    public void testRemovePiste() {
        Piste piste = new Piste();
        piste.setNamePiste("Test Piste");
        piste.setColor(Color.GREEN);
        piste.setLength(1000);
        piste.setSlope(25);

        Piste savedPiste = pisteServices.addPiste(piste);
        Long numPiste = savedPiste.getNumPiste();

        pisteServices.removePiste(numPiste);

        Piste removedPiste = pisteServices.retrievePiste(numPiste);

        assertNull(removedPiste);
    }
}
