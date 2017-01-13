package org.bear.bookstore.test.email;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmailTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("beans.xml", EmailTest.class);
	    
		MailService mailService = app.getBean(MailService.class);
		Email email = new Email();  
        //String toAddress = request.getParameter("email");  
        if(true) {//toAddress != null) {  
            email.setToAddress("1528695560@qq.com");  
            email.setSubject("正在注册账户");  
            email.setContent("正在注册账户，您的激活码为xxxxxx");  
            try {  
                mailService.sendMail(email);  
                System.out.println("发送邮件");  
            } catch (Exception e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
		
		System.in.read();
	    ((ClassPathXmlApplicationContext)app).close();
	}
}
