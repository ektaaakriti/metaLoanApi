package borrowerApp.api.controller.response;

public class SignUpResponse {
String Message;
String Status;
String Name;
String Mobile_no;
String DOB;
public String getMessage() {
	return Message;
}
public void setMessage(String message) {
	Message = message;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getMobile_no() {
	return Mobile_no;
}
public void setMobile_no(String mobile_no) {
	Mobile_no = mobile_no;
}
public String getDOB() {
	return DOB;
}
public void setDOB(String dOB) {
	DOB = dOB;
}
public SignUpResponse(String message, String status, String name, String mobile_no, String dOB) {
	super();
	Message = message;
	Status = status;
	Name = name;
	Mobile_no = mobile_no;
	DOB = dOB;
}


}
