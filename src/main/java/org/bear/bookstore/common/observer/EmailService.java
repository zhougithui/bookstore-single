package org.bear.bookstore.common.observer;

import java.util.List;

import org.bear.bookstore.domain.Custom;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class EmailService implements ApplicationEventPublisherAware {

    private List<String> blackList;
    private ApplicationEventPublisher publisher;

    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void sendEmail(String address, String text) {
        if (blackList.contains(address)) {
            BlackListEvent event = new BlackListEvent(this, address, text);
            publisher.publishEvent(event);
            
            Custom cus = new Custom();
            cus.setCusName("be created custom....");
            EntityCreatedEvent<Custom> cusEvent = new EntityCreatedEvent<Custom>(cus);
            publisher.publishEvent(cusEvent);
            return;
        }
        // send email...
    }

}