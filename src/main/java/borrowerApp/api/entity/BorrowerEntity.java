package borrowerApp.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;




@Entity
@Table(name = "system_managment", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mobile_no"}),})
public class BorrowerEntity {
	@Id
	int User_id;
	String Username;
	int Mpin;
	Long mobile_no;
	String is_login;
	Date login_datetime;
	
	String email;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	
	
	public int getMpin() {
		return Mpin;
	}
	public void setMpin(int mpin) {
		Mpin = mpin;
	}
	public Long getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(Long mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getIs_login() {
		return is_login;
	}
	public void setIs_login(String is_login) {
		this.is_login = is_login;
	}
	public Date getLogin_datetime() {
		return login_datetime;
	}
	public void setLogin_datetime(Date login_datetime) {
		this.login_datetime = login_datetime;
	}

	
		
}
		

		
		


