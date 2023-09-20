package borrowerApp.api.controller.response;

public class PersonalDtls {
	String Name;
	String Address;
	String mobile_no;
	String relationship_mgr_name;
	String relationship_mgr_number;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getRelationship_mgr_name() {
		return relationship_mgr_name;
	}
	public void setRelationship_mgr_name(String relationship_mgr_name) {
		this.relationship_mgr_name = relationship_mgr_name;
	}
	public String getRelationship_mgr_number() {
		return relationship_mgr_number;
	}
	public void setRelationship_mgr_number(String relationship_mgr_number) {
		this.relationship_mgr_number = relationship_mgr_number;
	}
}
