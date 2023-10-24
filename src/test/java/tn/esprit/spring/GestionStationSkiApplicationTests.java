package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ISkierRepository;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
class GestionStationSkiApplicationTests {

	@Autowired
	ISkierRepository skierRepository;

	Skier s= Skier.builder().firstName("Iheb").lastName("Iheb").dateOfBirth(LocalDate.parse("1997-10-05")).city("Sidi bouzid").build();

	@Test
	@Order(0)
	public void addSkier() {
		s = skierRepository.save(s);
		log.info(s.toString());
	}

	@Test
	@Order(2)
	public void listSkiers() {
		List<Skier> list= skierRepository.findAll();
		log.info(list.size()+"");
		Assertions.assertTrue(list.size() > 0);
	}

	@Test
	@Order(3)
	public void deleteSkier(){skierRepository.delete(s);}

	@Test
	@Order(4)
	public void count() {
		long size = skierRepository.count();
		Assertions.assertEquals(size, skierRepository.findAll().size());
	}

}
