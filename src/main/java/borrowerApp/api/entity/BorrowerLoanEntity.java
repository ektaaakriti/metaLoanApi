package borrowerApp.api.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Borrower_loan")
//uniqueConstraints = {
   //     @UniqueConstraint(columnNames = {""}),})
public class BorrowerLoanEntity {
int Borrower_user_id;
@Id
int loan_no;
public int getBorrower_user_id() {
	return Borrower_user_id;
}
public void setBorrower_user_id(int borrower_user_id) {
	Borrower_user_id = borrower_user_id;
}
public int getLoan_no() {
	return loan_no;
}
public void setLoan_no(int loan_no) {
	this.loan_no = loan_no;
}

}
