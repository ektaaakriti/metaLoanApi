package borrowerApp.api.controller.response;

public class HeplMessageResponse {
String message;
String Status;
public HeplMessageResponse(String message, String status) {
	super();
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
