package org.bear.bookstore.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailSendJob {
	
	@Scheduled(fixedRate=2000)
	public void send1(){
		log.debug(getClass().getName() + " send1 email");
	}
	
	@Scheduled(cron="*/5 * * * * MON-FRI")
	public void doSomething() {
	    // something that should execute on weekdays only
	}
}
