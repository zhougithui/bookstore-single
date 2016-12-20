package org.bear.bookstore.common.observer;

import org.springframework.context.ApplicationListener;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlackListNotifier implements ApplicationListener<BlackListEvent> {

    private String notificationAddress;

    public void setNotificationAddress(String notificationAddress) {
        this.notificationAddress = notificationAddress;
    }

    public void onApplicationEvent(BlackListEvent event) {
        // notify appropriate parties via notificationAddress...
    	System.out.println("receive blacklist event......");
    }

}