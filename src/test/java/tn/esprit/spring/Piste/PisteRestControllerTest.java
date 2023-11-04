package tn.esprit.spring.Piste;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tn.esprit.spring.controllers.PisteRestController;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.IPisteServices;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PisteRestController.class)
public class PisteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPisteServices pisteServices;

    @Test
    void testGetById() throws Exception {
        Piste piste = new Piste();
        piste.setNamePiste("Test Piste");
        piste.setColor(Color.GREEN);
        piste.setLength(1000);
        piste.setSlope(25);

        when(pisteServices.retrievePiste(1L)).thenReturn(piste);

        mockMvc.perform(MockMvcRequestBuilders.get("/piste/get/1"))
                .andExpect(status().isOk());
    }
}