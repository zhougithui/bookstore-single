package org.bear.bookstore.common.observer;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlackListEvent extends ApplicationEvent {
	private static final long serialVersionUID = 2688570366565185176L;
	private final String address;
    private final String test;

    public BlackListEvent(Object source, String address, String test) {
        super(source);
        this.address = address;
        this.test = test;
    }

}
