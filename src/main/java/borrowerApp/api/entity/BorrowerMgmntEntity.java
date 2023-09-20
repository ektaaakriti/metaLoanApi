package borrowerApp.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Borrower", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"borrower_id"}),})

public class BorrowerMgmntEntity {
	@Id
	int borrower_id;
	int user_id;
	Long mobile_no;
	String First_Name;
	String Last_name;
	String address;
	Date date_of_birth;
	String relationship_manager;
	long relationship_manager_number;
	public String getRelationship_manager() {
		return relationship_manager;
	}
	public void setRelationship_manager(String relationship_manager) {
		this.relationship_manager = relationship_manager;
	}
	public long getRelationship_manager_number() {
		return relationship_manager_number;
	}
	public void setRelationship_manager_number(long relationship_manager_number) {
		this.relationship_manager_number = relationship_manager_number;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public int getBorrower_id() {
		return borrower_id;
	}
	public void setBorrower_id(int borrower_id) {
		this.borrower_id = borrower_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Long getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(Long mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_name() {
		return Last_name;
	}
	public void setLast_name(String last_name) {
		Last_name = last_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
