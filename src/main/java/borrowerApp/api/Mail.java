package borrowerApp.api;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
public class Mail {
	@Autowired
	private JavaMailSender javaMailSender;
	public void sendEmail(int otp,String email) throws Exception {
		System.out.println("Sending Email");
		String subject = "Please Verify Your Account";
		String senderName = "Arthavedika";
		String mailContent = "<p> Dear " + "Customer" + ",</p>";
		mailContent += "<p>Please Use the OTP to verify your account </p>";
		mailContent += "<p>Your OTP is valid only for 5 minutes </p>";
		mailContent += "<p> Your OTP is " + otp + "</p>";
		mailContent += "<p>Thank You<br>Arthavedika Team</p>";
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("ekta.sharma@arthavedika.com", senderName);
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(mailContent, true);
		javaMailSender.send(message);
		System.out.println("Email Sent...");

	}

}
