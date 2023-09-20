package borrowerApp.api.controller.response;

import java.util.List;

import borrowerApp.api.entity.BorrowerMgmntEntity;

public class FetchpersonalDtlsresponse {
PersonalDtls details;
String message;
Boolean Status;

public FetchpersonalDtlsresponse(PersonalDtls details, String message, Boolean status) {
	super();
	this.details = details;
	this.message = message;
	Status = status;
}

public PersonalDtls getDetails() {
	return details;
}

public void setDetails(PersonalDtls details) {
	this.details = details;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
public Boolean getStatus() {
	return Status;
}
public void setStatus(Boolean status) {
	Status = status;
}



}
