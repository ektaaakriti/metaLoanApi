package borrowerApp.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sun.mail.util.MailSSLSocketFactory;
@Component
public class SendMail {
	
    
   
		static Properties props = new Properties();
		
	    public static void emailToUser( String Email,int otp,String from,String password,String host, String port1)  {
	    	 System.out.println("In email to user");
	    	
	       
	        String to = Email;
	       // System.out.println("from email is"+from);
	        System.out.println("to email is user"+to);
	        System.out.println("to email is host"+host);
	        System.out.println("to email is port"+port1);
	       System.out.println("to email is"+from);
	        System.out.println("to email is password"+password);
	       
	     
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.host",host); // 
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.debug", "true"); 
	        props.put("mail.smtp.starttls.enable", "false");
	        props.put("mail.smtp.port", "port");
	        props.put("mail.smtp.socketFactory.port", "port");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "true");

	        MailSSLSocketFactory sf = null;
	       
	        try {
	        	// SSLSocketFactory sf = new SSLSocketFactory();
	            sf = new MailSSLSocketFactory();
	        } catch (GeneralSecurityException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	            
	        }
	        sf.setTrustAllHosts(true);
	        props.put("mail.smtp.ssl.socketFactory", sf);

	        
	       /* mailSender.setHost(env.getProperty("spring.mail.host"));
	        mailSender.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
	        mailSender.setUsername(env.getProperty("spring.mail.username"));
	        mailSender.setPassword(env.getProperty("spring.mail.password"));*/
	        
	        // Get the Session object.// and pass username and password
	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

	            protected PasswordAuthentication getPasswordAuthentication() {

	                return new PasswordAuthentication(from,password);

	            }

	        });

	        // Used to debug SMTP issues
	        session.setDebug(true);

	        try {
	            // Create a default MimeMessage object.
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(from));

	            // Set To: header field of the header.
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	            // Set Subject: header field
	            message.setSubject("OTP Generation");

	            // Now set the actual message
	            message.setText("Dear Customer, \r \nThis is system generated mail for the otp verification."
	            		+ "\r \nOTP: "+otp
	            	
	            		+"\r \nRegards"
	            		+"\r Arthavedika");

	           
	            // Send message
	            Transport.send(message);
	            System.out.println("Sent message successfully....");
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        }

	    }
public void send_mail() {
	String to = "ektasharma011@gmail.com";

    // Sender's email ID needs to be mentioned
    String from = "ekta.sharma@arthavedika.com";

    // Assuming you are sending email from localhost
    String host = "mail.arthavedika.com";

    // Get system properties
    Properties properties = System.getProperties();

    // Setup mail server
    properties.setProperty("mail.smtp.host", host);

    // Get the default Session object.
    Session session = Session.getDefaultInstance(properties);

    try {
       // Create a default MimeMessage object.
       MimeMessage message = new MimeMessage(session);

       // Set From: header field of the header.
       message.setFrom(new InternetAddress(from));

       // Set To: header field of the header.
       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

       // Set Subject: header field
       message.setSubject("This is the Subject Line!");

       // Now set the actual message
       message.setText("This is actual message");

       // Send message
       Transport.send(message);
       System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
       mex.printStackTrace();
    }
}
	}


