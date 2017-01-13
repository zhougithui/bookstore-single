package org.bear.bookstore.test.email;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailServiceImpl implements MailService {  
  
    private JavaMailSenderImpl mailSender;// 注入Spring封装的javamail，Spring的xml中已让框架装配  
    private TaskExecutor taskExecutor;// 注入Spring封装的异步执行器  
  
    private Log log = LogFactory.getLog(getClass());  
    private StringBuffer message = new StringBuffer();  
  
    @Override  
    public void sendMail(Email email) throws MessagingException, IOException {  
        /*if (email.getAddress().length > 5) {// 收件人大于5封时，采用异步发送 
            sendMailByAsynchronousMode(email); 
            this.message.append("收件人过多，正在采用异步方式发送...<br/>"); 
        } else {*/  
        sendMailBySynchronizationMode(email);  
        //this.message.append("正在同步方式发送邮件...<br/>");  
        //}  
    }  
  
      
    @Override  
    public void sendMailByAsynchronousMode(final Email email) {  
        taskExecutor.execute(new Runnable() {  
            public void run() {  
                try {  
                    sendMailBySynchronizationMode(email);  
                } catch (Exception e) {  
                    log.info(e);  
                }  
            }  
        });  
    }  
  
    @Override  
    public void sendMailBySynchronizationMode(Email email)  
            throws MessagingException, IOException {  
        MimeMessage mime = mailSender.createMimeMessage();  
        MimeMessageHelper helper = new MimeMessageHelper(mime, true, "utf-8");  
        helper.setFrom(email.getToAddress());// 发件人  
        helper.setTo(email.getToAddress());// 收件人  
        helper.setReplyTo(email.getToAddress());// 回复到  
        helper.setSubject(email.getSubject());// 邮件主题  
        helper.setText(email.getContent(), true);// true表示设定html格式  
        mailSender.send(mime);  
    }  
  
      
    public JavaMailSenderImpl getMailSender() {  
        return mailSender;  
    }  
  
    public void setMailSender(JavaMailSenderImpl mailSender) {  
        this.mailSender = mailSender;  
    }  
  
    public TaskExecutor getTaskExecutor() {  
        return taskExecutor;  
    }  
  
    public void setTaskExecutor(TaskExecutor taskExecutor) {  
        this.taskExecutor = taskExecutor;  
    }  
  
    public Log getLog() {  
        return log;  
    }  
  
    public void setLog(Log log) {  
        this.log = log;  
    }  
  
    public StringBuffer getMessage() {  
        return message;  
    }  
  
    public void setMessage(StringBuffer message) {  
        this.message = message;  
    }  
  
}  