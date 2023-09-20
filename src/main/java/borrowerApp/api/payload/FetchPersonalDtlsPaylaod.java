package borrowerApp.api.payload;

public class FetchPersonalDtlsPaylaod {
String mobile_no;
String Loan_no;
public String getLoan_no() {
	return Loan_no;
}

public void setLoan_no(String loan_no) {
	Loan_no = loan_no;
}

public String getMobile_no() {
	return mobile_no;
}

public void setMobile_no(String mobile_no) {
	this.mobile_no = mobile_no;
}



}
