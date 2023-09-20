package borrowerApp.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import borrowerApp.api.Repository.BorrowerLoanrepo;
import borrowerApp.api.Repository.BorrowerMgmntRepo;
import borrowerApp.api.Repository.BorrowerRepo;
import borrowerApp.api.Repository.HelpMessageRepo;
import borrowerApp.api.Repository.LoanRepo;
import borrowerApp.api.controller.response.FetchpersonalDtlsresponse;
import borrowerApp.api.controller.response.HeplMessageResponse;
import borrowerApp.api.controller.response.LoanDetails;
import borrowerApp.api.controller.response.LoanDtlsResponse;
import borrowerApp.api.controller.response.LoanNumberResponse;
import borrowerApp.api.controller.response.PersonalDtls;
import borrowerApp.api.controller.response.SignInResponse;
import borrowerApp.api.entity.BorrowerMgmntEntity;
import borrowerApp.api.entity.HelpMessageEntity;
import borrowerApp.api.entity.Loan;
import borrowerApp.api.payload.FetchPersonalDtlsPaylaod;
import borrowerApp.api.payload.HelpMessagePayload;
import borrowerApp.api.payload.SignUpPayload;

@CrossOrigin ()
@RestController
@RequestMapping("/BorrowerApp/borrower/")
public class BorrowerController {
	
	@Autowired
	BorrowerMgmntRepo brwRepo;
	@Autowired 
	BorrowerLoanrepo brwloanRepo;
	@Autowired
	LoanRepo loanRepo;
	@Autowired
	BorrowerRepo borrowerRepo;
	@Autowired
	HelpMessageRepo helpRepo;
	@Value("${SignInUpdationError}") String SignInUpdationError;
	@Value("${PersonaldtlsFetched}") String PersonaldtlsFetched;
	@Value("${PersonaldtlsFetchedError}") String PersonaldtlsFetchedError;
	@Value("${BorrowerNoLoan}") String BorrowerNoLoan;
	@Value("${LoanNoFetched}") String LoanNoFetched;
	@Value("${LoanNotAvailable}") String LoanNotAvailable;
	@Value("${LoanDtlsFetched}") String LoanDtlsFetched;
	@Value("${HeplMessageSaved}") String HeplMessageSaved;
	@Value("${HelpMessageAlreadyExist}") String HelpMessageAlreadyExist;
	EncryptionDecryptionClass enc=new EncryptionDecryptionClass();
	public static Logger log = LogManager.getLogger(AuthController.class.getName());
	 @PostMapping(
	            value = "/fetchBorrowerPersnlDtls",
	            consumes = {MediaType.APPLICATION_JSON_VALUE},
	            produces = {MediaType.APPLICATION_JSON_VALUE})
	 
	    public ResponseEntity<FetchpersonalDtlsresponse> fetchBorrowerPesnlDtls(@RequestBody  FetchPersonalDtlsPaylaod dtlsPayload){
		 PersonalDtls per=new PersonalDtls();
	    	try {
	    		
	    		Long mobile_no=(Long.parseLong(enc.decryptnew(dtlsPayload.getMobile_no())));
	    		System.out.println(mobile_no);
	    		BorrowerMgmntEntity en=brwRepo.fetchpersonalDtls(mobile_no);
	    		System.out.println(en);
	    		if(null==en) {
	    			return ResponseEntity.status(HttpStatus.OK)
	    					.body(new FetchpersonalDtlsresponse(per,enc.encryptnew(PersonaldtlsFetchedError),Boolean.TRUE));
	    		}
	    		else {
	    		per.setName(enc.encryptnew(en.getFirst_Name()));
	    		per.setMobile_no(enc.encryptnew((String.valueOf(en.getMobile_no()))));
	    		per.setAddress(enc.encryptnew(en.getAddress()));
	    		per.setRelationship_mgr_name(enc.encryptnew(en.getRelationship_manager()));
	    		per.setRelationship_mgr_number(enc.encryptnew((String.valueOf(en.getRelationship_manager_number()))));
	    		return ResponseEntity.status(HttpStatus.OK)
    					.body(new FetchpersonalDtlsresponse(per,enc.encryptnew(PersonaldtlsFetched),Boolean.TRUE));
    		}}
    	
	   catch(Exception e) {
	    	  System.out.println("error in  api"+e);
	    	 log.error("error in signin of mobile_no"+e);
	    
	    	  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new FetchpersonalDtlsresponse(per,("error in fetching detials"+e.getClass()),Boolean.FALSE));
	      }
	    	}
	 @PostMapping(
	            value = "/fetchBorrowerLoanDtls",
	            consumes = {MediaType.APPLICATION_JSON_VALUE},
	            produces = {MediaType.APPLICATION_JSON_VALUE})
	 public ResponseEntity<LoanDtlsResponse> fetchBorrowerLoanDtls(@RequestBody  FetchPersonalDtlsPaylaod dtlsPayload){
		 LoanDetails lnD=new LoanDetails();
	    	try {
	    		
	    		int loan_no=(Integer.parseInt(enc.decryptnew(dtlsPayload.getLoan_no())));
	    		System.out.println(loan_no);
	    		Loan ln=loanRepo.Loan_dtls(loan_no);
	    		System.out.println(ln);
	    		if(null==ln) {
	    			return ResponseEntity.status(HttpStatus.OK)
	    					.body(new LoanDtlsResponse(lnD,enc.encryptnew(LoanNotAvailable),enc.encryptnew("True")));
	    		}
	    		else {
	    		lnD.setLoan_no(dtlsPayload.getLoan_no());
	    		lnD.setLoan_amount(enc.encryptnew(Float. toString(ln.getLoan_amount())));
	    		lnD.setLoan_amount_pending(enc.encryptnew(Float. toString(ln.getLoan_amt_pending())));
	    		lnD.setEmi_amount(enc.encryptnew(Float. toString(ln.getEmi_amount())));
	    		lnD.setEmi_pnding(enc.encryptnew(Float. toString(ln.getEmi_pending())));	    		
	    		lnD.setEmi_due_date(enc.encryptnew(ln.getEmi_due_date().toString()));
	    		lnD.setDefault_days(enc.encryptnew(Integer.toString(ln.getDefault_days())));
	    		return ResponseEntity.status(HttpStatus.OK)
    					.body(new LoanDtlsResponse(lnD,enc.encryptnew(LoanDtlsFetched),enc.encryptnew("True")));
    		}}
    	
	   catch(Exception e) {
	    	  System.out.println("error in  api"+e);
	    	 log.error("error in fecthing loan details"+e);
	    
	    	  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new LoanDtlsResponse(lnD,("error in fetching  loan details"+e.getClass()),enc.encryptnew("False")));
	      }
	    	}
	    	

@PostMapping(
        value = "/fetchBorrowerLoanNumbers",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public ResponseEntity<LoanNumberResponse> fetchBorrowerLoanNumbers(@RequestBody  FetchPersonalDtlsPaylaod dtlsPayload){
 List<String> loan_no= new ArrayList<>();
	try {
		Long mobile_no=(Long.parseLong(enc.decryptnew(dtlsPayload.getMobile_no())));
	
		int user_id=brwRepo.fetchUser_id(mobile_no);
		if(user_id==0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new LoanNumberResponse(loan_no,enc.encryptnew(PersonaldtlsFetchedError),enc.encryptnew("FALSE")));
		}else {
				List<Integer> loan_noInt=brwloanRepo.loan_no(user_id);
						if(loan_noInt==null) {
							return ResponseEntity.status(HttpStatus.OK)
							.body(new LoanNumberResponse(loan_no,enc.encryptnew(BorrowerNoLoan),enc.encryptnew("TRUE")));
							}
						else {
								for(int i=0;i<loan_noInt.size();i++)
								{
									String loan=enc.encryptnew(Integer. toString( loan_noInt.get(i).intValue()));
									loan_no.add(i, loan);}
			 
									return ResponseEntity.status(HttpStatus.OK)
											.body(new LoanNumberResponse(loan_no,enc.encryptnew(LoanNoFetched),enc.encryptnew("TRUE")));
								
								}
						}
	}
	
	catch(Exception e) {
  	  System.out.println("error in fechting loan number"+e);
  	 log.error("error in fechting loan number "+e);
  
  	  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new LoanNumberResponse(loan_no,("error in fetching detials"+e.getClass()),"FALSE"));
    }
  	}

@PostMapping(
        value = "/SavehelpMessage",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public ResponseEntity<HeplMessageResponse> SaveHelpMessage(@RequestBody  HelpMessagePayload helpPayload){
	try {
		//String Message=enc.decryptnew(helpPayload.getHelp_message());
		Long mobile_no=(Long.parseLong(enc.decryptnew(helpPayload.getMobile_no())));
		int user_id= borrowerRepo.user_Id4mMobile(mobile_no);
		System.out.println(user_id);
		if(user_id==0) {
			System.out.println("user_id is zero");
			return ResponseEntity.status(HttpStatus.OK)
					.body(new HeplMessageResponse(enc.encryptnew(PersonaldtlsFetchedError),enc.encryptnew("FALSE")));
		}
		else {
			String help_message=enc.decryptnew(helpPayload.getHelp_message());
			System.out.println(help_message);
			HelpMessageEntity en=helpRepo.getHelpNo(user_id,help_message);
			//System.out.println(help_no1);
			if (null==en){
			HelpMessageEntity hlp=new HelpMessageEntity();
			hlp.setHelp_message(enc.decryptnew(helpPayload.getHelp_message()));
			hlp.setUser_id(user_id);
			Date currentdt=new Date();
			hlp.setHelp_date_time(currentdt);
			helpRepo.save(hlp);
			System.out.println(user_id);
			System.out.println(currentdt);
			
			HelpMessageEntity hlen=helpRepo.getHelpNo(user_id,enc.decryptnew(helpPayload.getHelp_message()));
			System.out.println(hlen.getHelp_no());
			String message=HeplMessageSaved+hlen.getHelp_no();
			System.out.println(message);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new HeplMessageResponse(enc.encryptnew(message),enc.encryptnew("TRUE")));}
			else {
				String response =HelpMessageAlreadyExist+en.getHelp_no();
				System.out.println(response);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new HeplMessageResponse(enc.encryptnew(response),enc.encryptnew("False")));
			}
		}}
	catch(Exception e) {
	  	  System.out.println("error in saving help message"+e);
	  	 log.error("error i saving help message "+e);
	  
	  	  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new HeplMessageResponse(("error in saving help message"+e.getClass()),enc.encryptnew("FALSE")));
	}
		
	}

}
