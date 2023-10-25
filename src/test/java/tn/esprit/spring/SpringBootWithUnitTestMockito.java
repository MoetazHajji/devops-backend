package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.ISubscriptionServices;
import tn.esprit.spring.services.SubscriptionServicesImpl;

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
public class SpringBootWithUnitTestMockito {
    @Mock
    ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    SubscriptionServicesImpl subscriptionService;

    Subscription s = Subscription.builder().price(200f).typeSub(TypeSubscription.MONTHLY).startDate(LocalDate.now()).build();

    List<Subscription> list= new ArrayList<Subscription>() {
        {
            add(Subscription.builder().price(250f).typeSub(TypeSubscription.ANNUAL).startDate(LocalDate.now()).build());
            add(Subscription.builder().price(400f).typeSub(TypeSubscription.SEMESTRIEL).startDate(LocalDate.now()).build());
        }
    };

    @Test
    public void  addSubscriptionTest() {
        Mockito.when(subscriptionRepository.save(Mockito.any(Subscription.class))).then(invocation -> {
            Subscription model = invocation.getArgument(0, Subscription.class);
            model.setNumSub(1L);
            return model;
        });
        log.info("Avant ==> " + s.toString());
        Subscription subscription = subscriptionService.addSubscription(s);
        assertSame(subscription, s);
        log.info("Après ==> " + s.toString());
    }

    @Test
    public void retreiveMagasinTest() {
        Mockito.when(subscriptionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
        Subscription subscription = subscriptionService.retrieveSubscriptionById((long) 1566);
        assertNotNull(subscription);
        log.info("get ==> " + subscription.toString());
        verify(subscriptionRepository).findById(Mockito.anyLong());
    }
}
