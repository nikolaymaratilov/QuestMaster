package app.subscription.service;

import app.subscription.model.Subscription;
import app.subscription.model.SubscriptionPeriod;
import app.subscription.model.SubscriptionStatus;
import app.subscription.model.SubscriptionType;
import app.subscription.repository.SubscriptionRepository;
import app.transaction.model.Transaction;
import app.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeMBeanException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription createDefaultSubscription(User user) {

        Subscription subscription = Subscription.builder()
                        .owner(user)
                        .status(SubscriptionStatus.ACTIVE)
                        .period(SubscriptionPeriod.MONTHLY)
                        .type(SubscriptionType.DEFAULT)
                        .price(BigDecimal.ZERO)
                        .renewalAllowed(true)
                        .createdOn(LocalDateTime.now())
                        .completedOn(LocalDateTime.now().plusMonths(1))
                        .build();

        return subscriptionRepository.save(subscription);
    }

    public Transaction upgrade(User user, SubscriptionType subscriptionType) {

       Optional<Subscription> currentlyActiveSubscriptionOpt = subscriptionRepository.findByStatusAndOwnerId(SubscriptionStatus.ACTIVE,user.getId());

       if (currentlyActiveSubscriptionOpt.isEmpty()){
           throw new RuntimeException("No active subscription was found for user with id [%s]".formatted(user.getId()));
       }

        return null;
    }
}
