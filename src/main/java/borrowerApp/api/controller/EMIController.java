package borrowerApp.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import borrowerApp.api.EncryptionDecryptionClass;
import borrowerApp.api.Repository.EMIRepo;
import borrowerApp.api.controller.response.EmiDtls;
import borrowerApp.api.controller.response.EmiResponse;
import borrowerApp.api.controller.response.FetchpersonalDtlsresponse;
import borrowerApp.api.controller.response.LoanDetails;
import borrowerApp.api.controller.response.LoanDtlsResponse;
import borrowerApp.api.entity.EMIEntity;
import borrowerApp.api.payload.FetchPersonalDtlsPaylaod;
import borrowerApp.api.payload.emiPayload;
@CrossOrigin ()
@RestController
@RequestMapping("/BorrowerApp/EMI/")
public class EMIController {
	public static Logger log = LogManager.getLogger(EMIController.class.getName());
	EncryptionDecryptionClass enc=new EncryptionDecryptionClass();
	@Autowired
	EMIRepo emiRepo;
	@Value("${EMIDtlsFetched}") String EMIDtlsFetched;
	@Value("${EMIDtlsNotAvailable}") String EMIDtlsNotAvailable;
	 @PostMapping(
	            value = "/fetchEMIDtls",
	            consumes = {MediaType.APPLICATION_JSON_VALUE},
	            produces = {MediaType.APPLICATION_JSON_VALUE})
	 public ResponseEntity<EmiResponse> fetchEMIDtls(@RequestBody  emiPayload emiPayload){
		List<EmiDtls> emiList=new ArrayList<>();
		
	    	try {
	    		int loan_no=(Integer.parseInt(enc.decryptnew(emiPayload.getLoan_no())));
	      		List<EMIEntity> emEn =emiRepo.getEmiDtls(loan_no);
	      		if(null==emEn) {
	      			return ResponseEntity.status(HttpStatus.OK)
							.body(new EmiResponse(emiList,enc.encryptnew(EMIDtlsNotAvailable),enc.encryptnew("TRUE")));	
	      		}
	      		else {
	    		for(int i=0;i<5;i++) {
	    			EmiDtls emi=new EmiDtls();
	    			emi.setEmi_amount(enc.encryptnew(Float.toString((emEn.get(i).getEmi_amount()))));
	    		emi.setEmi_mode(enc.encryptnew(emEn.get(i).getEmi_mode()));
	    		emi.setEmi_date(enc.encryptnew(emEn.get(i).getEmi_date().toString()));
	    		emiList.add(i, emi);
	    		}
	    		return ResponseEntity.status(HttpStatus.OK)
						.body(new EmiResponse(emiList,enc.encryptnew(EMIDtlsFetched),enc.encryptnew("TRUE")));
	    		
}}
	    	catch(Exception e) {
		    	  System.out.println("error in  api"+e);
		    	 log.error("error in fetching emi details"+e);
		    
		    	  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body(new EmiResponse(emiList,("error in fetching emi detials"+e.getClass()),"FALSE"));
		      }
	 }
}
