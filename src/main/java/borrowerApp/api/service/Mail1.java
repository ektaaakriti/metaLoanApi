package borrowerApp.api.service;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@Transactional
@Service
public class Mail1 {
	
	@Autowired
	private JavaMailSender javaMailSender;
	

	public void sendEmail1(String email,int otp) throws Exception {
		System.out.println("Sending Email to "+email);
		String subject = "Please Verify Your Account";
		String senderName = "Arthavedika";
		String mailContent = "<p> Dear " + "customer" + ",</p>";
		mailContent += "<p>Please Use the OTP to verify your account </p>";
		mailContent += "<p>Your OTP is valid only for 5 minutes </p>";
		// String verifyURL = siteURL + "/verify?code=" + newUser.getVerificationCode();
		// mailContent += "<h3><a href=\"" + verifyURL + "\">CLICK TO VERIFY </a></h3>";
		mailContent += "<p> Your OTP is " + otp + "</p>";
		mailContent += "<p>Thank You<br>Arthavedika Team</p>";
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("test1@arthavedika.com", senderName);
		helper.setTo(email);
		System.out.println("Email Sent...1");
		helper.setSubject(subject);
		System.out.println("Email Sent...2");
		helper.setText(mailContent, true);
		System.out.println("Email Sent...3");
		System.out.println(message);
		javaMailSender.send(message);
		System.out.println("Email Sent...");

	}

	
	

}

