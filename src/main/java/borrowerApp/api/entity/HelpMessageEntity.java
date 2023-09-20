package borrowerApp.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity

@Table(name = "help_message", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"help_no"}),})
public class HelpMessageEntity {
	@Id
	int help_no;
	int user_id;
	String help_message;
	Date help_date_time;
	public int getHelp_no() {
		return help_no;
	}
	public void setHelp_no(int help_no) {
		this.help_no = help_no;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getHelp_message() {
		return help_message;
	}
	public void setHelp_message(String help_message) {
		this.help_message = help_message;
	}
	public Date getHelp_date_time() {
		return help_date_time;
	}
	public void setHelp_date_time(Date help_date_time) {
		this.help_date_time = help_date_time;
	}
	
}
