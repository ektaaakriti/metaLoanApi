package borrowerApp.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EMI_Id implements Serializable {
int loan_no;
Date emi_date;


public int getloan_no() {
	return loan_no;
}
public void setloan_no(int loan_no) {
	this.loan_no = loan_no;
}
public Date getEmi_date() {
	return emi_date;
}
public void setEmi_date(Date emi_date) {
	this.emi_date = emi_date;
}
}
