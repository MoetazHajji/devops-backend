package tn.esprit.spring;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.services.SkierServicesImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
public class SkierServiceImplTest {
    @Autowired
    private SkierServicesImpl skierService;

    @Test
    void addSkier() {
    }

    @Test
    @DatabaseSetup("/data-set/skier-data.xml")
    void retrieveSkier() {
        final Skier skier = this.skierService.retrieveSkier(1L);
        assertEquals("skier 1", skier.getNumSkier());
    }

//    @Test
//    @DatabaseSetup("/data-set/skier-data.xml")
//    void retrieveAllSkier() {
//        final List<Skier> allSkier = this.skierService.retrieveAllSkiers();
//        if (!CollectionUtils.isEmpty(allSkier)) {
//            assertEquals(allSkier.size(), 1);
//        }
//    }

}
