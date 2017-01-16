package org.bear.bookstore.quartz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleBusinessObject {

    // properties and collaborators

    public void doIt() {
        log.debug(getClass().getName() + " doit ..");
    }
}