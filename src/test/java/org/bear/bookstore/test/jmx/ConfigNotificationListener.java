package org.bear.bookstore.test.jmx;

import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

public class ConfigNotificationListener implements NotificationListener, NotificationFilter {
	/**  */
	private static final long serialVersionUID = 1099818764372891903L;
	/**
	 * @see javax.management.NotificationListener#handleNotification(javax.management.Notification, java.lang.Object)
	 */
	public void handleNotification(Notification notification, Object handback) {
		log("SequenceNumber:" + notification.getSequenceNumber());
		log("Type:" + notification.getType());
		log("Message:" + notification.getMessage());
		log("Source:" + notification.getSource());
		log("TimeStamp:" + notification.getTimeStamp());
	}
	private void log(String message) {
		System.out.println(message);
	}
	/**
	 * @see javax.management.NotificationFilter#isNotificationEnabled(javax.management.Notification)
	 */
	public boolean isNotificationEnabled(Notification notification) {
		return true;
	}
}