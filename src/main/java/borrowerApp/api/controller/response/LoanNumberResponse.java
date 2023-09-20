package borrowerApp.api.controller.response;

import java.util.List;

public class LoanNumberResponse {
List<String> loan_no;
String message;
String status;

public LoanNumberResponse(List<String> loan_no, String message, String status) {
	super();
	this.loan_no = loan_no;
	this.message = message;
	this.status = status;
}
public List<String> getLoan_no() {
	return loan_no;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public void setLoan_no(List<String> loan_no) {
	this.loan_no = loan_no;
}


}
