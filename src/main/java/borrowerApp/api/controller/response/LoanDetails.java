package borrowerApp.api.controller.response;

public class LoanDetails {
String loan_no;
String Loan_amount;
String Loan_amount_pending;
String Emi_amount;
String Emi_due_date;
String Emi_pnding;
String default_days;
public String getLoan_no() {
	return loan_no;
}
public void setLoan_no(String loan_no) {
	this.loan_no = loan_no;
}
public String getLoan_amount() {
	return Loan_amount;
}
public void setLoan_amount(String loan_amount) {
	Loan_amount = loan_amount;
}
public String getLoan_amount_pending() {
	return Loan_amount_pending;
}
public void setLoan_amount_pending(String loan_amount_pending) {
	Loan_amount_pending = loan_amount_pending;
}
public String getEmi_amount() {
	return Emi_amount;
}
public void setEmi_amount(String emi_amount) {
	Emi_amount = emi_amount;
}
public String getEmi_due_date() {
	return Emi_due_date;
}
public void setEmi_due_date(String emi_due_date) {
	Emi_due_date = emi_due_date;
}
public String getEmi_pnding() {
	return Emi_pnding;
}
public void setEmi_pnding(String emi_pnding) {
	Emi_pnding = emi_pnding;
}
public String getDefault_days() {
	return default_days;
}
public void setDefault_days(String default_days) {
	this.default_days = default_days;
}
}
