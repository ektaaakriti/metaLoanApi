package borrowerApp.api.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "OTP", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"otp"}),})

public class OTPEntity {
@Id
int otp;
String email;
Long mobile_no;
Date sendDateTime;
public int getOtp() {
	return otp;
}
public void setOtp(int otp) {
	this.otp = otp;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Long getMobile_no() {
	return mobile_no;
}
public void setMobile_no(Long mobile_no) {
	this.mobile_no = mobile_no;
}
public Date getSendDateTime() {
	return sendDateTime;
}
public void setSendDateTime(Date sendDateTime) {
	this.sendDateTime = sendDateTime;
}

}
