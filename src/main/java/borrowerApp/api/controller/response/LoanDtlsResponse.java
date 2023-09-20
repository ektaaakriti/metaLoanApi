package borrowerApp.api.controller.response;

import borrowerApp.api.entity.Loan;

public class LoanDtlsResponse {
LoanDetails LoanDetails;
String message;
String status;

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
public LoanDetails getLoanDetails() {
	return LoanDetails;
}
public void setLoanDetails(LoanDetails loanDetails) {
	LoanDetails = loanDetails;
}
public LoanDtlsResponse(borrowerApp.api.controller.response.LoanDetails loanDetails, String message, String status) {
	super();
	LoanDetails = loanDetails;
	this.message = message;
	this.status = status;
}

}
