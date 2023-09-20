package borrowerApp.api.payload;

public class SignUpPayload {
	
	String user_id;


String mobile_no;
String OTP;
String Mpin;
String date_of_birth;
String Name;
String BorrowerCheck;
String GenerateOTP;
String verifyOTP;
public String getVerifyOTP() {
	return verifyOTP;
}
public void setVerifyOTP(String verifyOTP) {
	this.verifyOTP = verifyOTP;
}
public String getGenerateOTP() {
	return GenerateOTP;
}
public void setGenerateOTP(String generateOTP) {
	GenerateOTP = generateOTP;
}
public String getBorrowerCheck() {
	return BorrowerCheck;
}
public void setBorrowerCheck(String borrowerCheck) {
	BorrowerCheck = borrowerCheck;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getDate_of_birth() {
	return date_of_birth;
}
public void setDate_of_birth(String date_of_birth) {
	this.date_of_birth = date_of_birth;
}





public String getMobile_no() {
	return mobile_no;
}
public void setMobile_no(String mobile_no) {
	this.mobile_no = mobile_no;
}


public String getOTP() {
	return OTP;
}
public void setOTP(String oTP) {
	OTP = oTP;
}
public String getMpin() {
	return Mpin;
}
public void setMpin(String mpin) {
	Mpin = mpin;
}


}
