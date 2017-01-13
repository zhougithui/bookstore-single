package org.bear.bookstore.test.email;

import java.io.IOException;

import javax.mail.MessagingException;

public interface MailService {

	void sendMail(Email email) throws MessagingException, IOException;

	void sendMailByAsynchronousMode(Email email);

	void sendMailBySynchronizationMode(Email email) throws MessagingException, IOException;

}
