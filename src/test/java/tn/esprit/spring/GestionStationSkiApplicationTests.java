package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.ISubscriptionServices;


import java.time.LocalDate;
import java.util.Set;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class GestionStationSkiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	ISubscriptionServices subscriptionServices;

	Subscription s = Subscription.builder().price(200f).typeSub(TypeSubscription.MONTHLY).startDate(LocalDate.now()).build();

	@Test
	@Order(0)
	public void ajouterSubscriptionTest() {
		s = subscriptionServices.addSubscription(s);
		log.info(s.toString());
		Assertions.assertNotNull(s.getNumSub());
	}


	@Test
	@Order(1)
	public void modifierSubscriptionTest() {
		s.setPrice(300f);
		s = subscriptionServices.updateSubscription(s);
		log.info(s.toString());
		Assertions.assertNotEquals(s.getPrice(), 200f);
	}


}
