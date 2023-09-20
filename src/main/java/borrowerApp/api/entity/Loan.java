package borrowerApp.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity

@Table(name = "loan", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"Loan_no"}),})
public class Loan {
	@Id
	int Loan_no;
	Float Loan_amount;
	Float loan_amt_pending;
	Float emi_amount;
	Date emi_due_date;
	Float emi_pending;
	int default_days;
	String Branch_code;
	
	public Date getEmi_due_date() {
		return emi_due_date;
	}
	public void setEmi_due_date(Date emi_due_date) {
		this.emi_due_date = emi_due_date;
	}
	public int getLoan_no() {
		return Loan_no;
	}
	public void setLoan_no(int loan_no) {
		Loan_no = loan_no;
	}
	public Float getLoan_amount() {
		return Loan_amount;
	}
	public void setLoan_amount(Float loan_amount) {
		Loan_amount = loan_amount;
	}
	public Float getLoan_amt_pending() {
		return loan_amt_pending;
	}
	public void setLoan_amt_pending(Float loan_amt_pending) {
		this.loan_amt_pending = loan_amt_pending;
	}
	public Float getEmi_amount() {
		return emi_amount;
	}
	public void setEmi_amount(Float emi_amount) {
		this.emi_amount = emi_amount;
	}
	
	public Float getEmi_pending() {
		return emi_pending;
	}
	public void setEmi_pending(Float emi_pending) {
		this.emi_pending = emi_pending;
	}
	public int getDefault_days() {
		return default_days;
	}
	public void setDefault_days(int default_days) {
		this.default_days = default_days;
	}
	public String getBranch_code() {
		return Branch_code;
	}
	public void setBranch_code(String branch_code) {
		Branch_code = branch_code;
	}
	
}
