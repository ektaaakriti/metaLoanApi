package borrowerApp.api.payload;

public class ChangeMpinPayload {
	String mobile_no;
String mpin;
String otp;
public String getMobile_no() {
	return mobile_no;
}
public void setMobile_no(String mobile_no) {
	this.mobile_no = mobile_no;
}
public String getMpin() {
	return mpin;
}
public void setMpin(String mpin) {
	this.mpin = mpin;
}
public String getOtp() {
	return otp;
}
public void setOtp(String otp) {
	this.otp = otp;
}


}
