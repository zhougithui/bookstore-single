package org.bear.bookstore.common.observer;

import org.springframework.context.ApplicationListener;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntityCreatedNotifier<T> implements ApplicationListener<EntityCreatedEvent<T>> {

    public void onApplicationEvent(EntityCreatedEvent<T> event) {
        // notify appropriate parties via notificationAddress...
    	System.out.println("receive EntityCreated event......");
    }

}