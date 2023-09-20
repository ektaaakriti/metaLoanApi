package borrowerApp.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
@Entity
@Table(name="emi")
@IdClass(EMI_Id.class)
public class EMIEntity {
	@Id
	 int loan_no;
	 Float emi_amount;
	 String emi_mode;
	 @Id
	 Date emi_date;
	
	public int getLoan_no() {
		return loan_no;
	}
	public void setLoan_no(int loan_no) {
		this.loan_no = loan_no;
	}
	public Float getEmi_amount() {
		return emi_amount;
	}
	public void setEmi_amount(Float emi_amount) {
		this.emi_amount = emi_amount;
	}
	public String getEmi_mode() {
		return emi_mode;
	}
	public void setEmi_mode(String emi_mode) {
		this.emi_mode = emi_mode;
	}
	public Date getEmi_date() {
		return emi_date;
	}
	public void setEmi_date(Date emi_date) {
		this.emi_date = emi_date;
	}
	 
}
