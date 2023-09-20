package borrowerApp.api.controller.response;

import java.util.List;

import borrowerApp.api.entity.EMIEntity;

public class EmiResponse {
List<EmiDtls> emi;
String message;
String Status;


public List<EmiDtls> getEmi() {
	return emi;
}
public void setEmi(List<EmiDtls> emi) {
	this.emi = emi;
}
public EmiResponse(List<EmiDtls> emi, String message, String status) {
	super();
	this.emi = emi;
	this.message = message;
	Status = status;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
}
}
