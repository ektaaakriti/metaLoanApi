package borrowerApp.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "masterdataborrower", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"applict_id"}),})
public class MasterDataEntity {
@Id
int applict_id;
long mobile;
String member_name;
String date_of_birth;
public String getDate_of_birth() {
	return date_of_birth;
}
public void setDate_of_birth(String date_of_birth) {
	this.date_of_birth = date_of_birth;
}
public int getApplict_id() {
	return applict_id;
}
public void setApplict_id(int applict_id) {
	this.applict_id = applict_id;
}
public long getMobile() {
	return mobile;
}
public void setMobile(long mobile) {
	this.mobile = mobile;
}
public String getMember_name() {
	return member_name;
}
public void setMember_name(String member_name) {
	this.member_name = member_name;
}

}
