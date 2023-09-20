package borrowerApp.api.controller.response;

public class metaOpenURLResponse {
String Message;
String Status;
public String getMessage() {
	return Message;
}
public void setMessage(String message) {
	Message = message;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
}
public metaOpenURLResponse(String message, String status) {
	super();
	Message = message;
	Status = status;
}

}
