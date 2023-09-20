package borrowerApp.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

import borrowerApp.api.controller.response.*;
import borrowerApp.api.payload.Authpayload;
import borrowerApp.api.payload.ChangeMpinPayload;
import borrowerApp.api.payload.FetchPersonalDtlsPaylaod;
import borrowerApp.api.payload.MetaOpenURLPayload;
import borrowerApp.api.payload.SignUpPayload;
import borrowerApp.api.service.Mail1;
import borrowerApp.api.EncryptionDecryptionClass;
import borrowerApp.api.Mail;
import borrowerApp.api.SendMail;
import borrowerApp.api.Repository.*;
import borrowerApp.api.entity.*;
import java.net.URL;  
import java.net.URLConnection;  
import java.util.Scanner;
import java.net.HttpURLConnection;


@CrossOrigin ()
@RestController
@RequestMapping("/BorrowerApp/api")
public class AuthController {
    EncryptionDecryptionClass enc=new EncryptionDecryptionClass();

	public static Logger log = LogManager.getLogger(AuthController.class.getName());
	@Autowired
	BorrowerRepo borrowerRepo;
	@Autowired
	BorrowerMgmntRepo brwRepo;
	@Autowired
	OTPRepo OTPRepo;
	
	@Autowired
	private Mail1 sendEmailService;
   // @Value("${spring.mail.host}")  String host;

	@Value("${SignInOk}") 
	private String SignInOk;
	@Value("${SignInWrongCredential}") String SignInWrongCredential;
	@Value("${SignInError}") String SignInError;
	@Value("${SignInUpdationError}") String SignInUpdationError;
	@Value("${SignUpBorrowerWrongCredential}") String SignUpBorrowerWrongCredential;
	@Value("${SignUpBorrowerExist}") String SignUpBorrowerExist;
	@Value("${SignUpOk}") String SignUpOk;
	@Value("${IncorrectOTP}") String IncorrectOTP;
	@Value("${OtpgenerationError}") String OtpgenerationError;
	@Value("${UserExist}") String UserExist;
	@Value("${Mpinchanged}") String Mpinchanged;
	@Value("${OTPVerified}")String OTPVerified;
	@PostMapping(
            value = "/SignIn",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
 
    public ResponseEntity<SignInResponse> signIn(@RequestBody  Authpayload authPayload){
	  
    	try {
    		String response="";
    		System.out.println(authPayload.getMobile_no());
    		System.out.println(authPayload.getMpin());
    		Long mobile_no=Long.parseLong(enc.decryptnew(authPayload.getMobile_no()));
    		
    	System.out.println(mobile_no);
    		int mpin=Integer.parseInt(enc.decryptnew(authPayload.getMpin()));
    		System.out.println(mpin);
    	BorrowerEntity brw=borrowerRepo.signin(mobile_no,mpin);
    	if(null==brw) {
    		response="signin credentials are incorrect";
    		log.info( response);;
    		return ResponseEntity.status(HttpStatus.OK)
					.body(new SignInResponse(enc.encryptnew(SignInWrongCredential),Boolean.FALSE));
    	}
    	else {
    		response="Borrower is sign in successfully";
    		log.info( response);
    		//log.info(null, response, authPayload, authPayload, authPayload, authPayload, response, mobile_no, brw, authPayload);
    		log.info("user signed in successfully with mobile no"+mobile_no);
    		Boolean update=updateSignInDetails(mobile_no);
    		if(update==true)
    			{
    			log.info( SignInOk);
    			return ResponseEntity.status(HttpStatus.OK)
					.body(new SignInResponse(enc.encryptnew(SignInOk),Boolean.TRUE));}
    		else {
    			log.info( SignInUpdationError);
    			return ResponseEntity.status(HttpStatus.OK)
    					.body(new SignInResponse(enc.encryptnew(SignInUpdationError),Boolean.FALSE));
    		}
    	}
    		}
    		
	   
	   catch(Exception e) {
	    	  System.out.println("error in  api"+e);
	    	 log.error("error in signin of mobile_no"+e);
	    
	    	  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new SignInResponse((SignInError+e),Boolean.FALSE));
	      }
    }
	public Boolean updateSignInDetails( Long mobile_no) {
		try {
			Date currDatetime=new Date();
			borrowerRepo.updateSignInDetails(currDatetime,mobile_no);
			log.info( "signin details are updated for mobile no"+mobile_no);
			return true;
		}
			catch(Exception e){
				 System.out.println("error in   updation of sign details api"+e);
		    	 log.error("error in  updation of signin details of mobile_no"+mobile_no +e);
		    	 return false;
			}
		}

	@PostMapping(
            value = "/Signup",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
 
    public ResponseEntity<SignUpResponse> signUp(@RequestBody  SignUpPayload signUpPayload){
	  
    	try {
    	
    		
    		Long mobile_no=Long.parseLong(enc.decryptnew(signUpPayload.getMobile_no()));
    		
    	     
    	Date DOB=new SimpleDateFormat("yyyy-MM-dd").parse(enc.decryptnew(signUpPayload.getDate_of_birth()));
    	System.out.println(DOB);
    	System.out.println(enc.decryptnew(signUpPayload.getMobile_no()));
    	System.out.println(enc.decryptnew(signUpPayload.getGenerateOTP()));
    	if(enc.decryptnew(signUpPayload.getGenerateOTP()).equals(("true"))) {
    		String Name=enc.decryptnew(signUpPayload.getName());
    		System.out.println(Name);
    		Boolean borrower_check=borrowerCheck(enc.decryptnew(signUpPayload.getName()) , DOB,mobile_no);
    		System.out.println(borrower_check);
    		if(borrower_check.equals(true)) {
    			System.out.println("borrower exist");
    			Boolean generateOTP=generateOTP(mobile_no,"ramgopalrai566@gmail.com");
				System.out.println("generateOTP");
				if (generateOTP==true) {
					System.out.println("otp is generated");
					return ResponseEntity.status(HttpStatus.OK)
							.body(new SignUpResponse(enc.encryptnew(SignUpBorrowerExist),"true",null,null,null));
				}
				else {
					System.out.println("otp not generated");
					return ResponseEntity.status(HttpStatus.OK)
							.body(new SignUpResponse(enc.encryptnew(OtpgenerationError),"False",null,null,null));
				}
    		}
						
    		else {
    			System.out.println("borrower does not exist");
    			return ResponseEntity.status(HttpStatus.OK)
						.body(new SignUpResponse(enc.encryptnew(SignUpBorrowerWrongCredential),"false",null,null,null));
    		}
    	}
    		 
    		if(enc.decryptnew(signUpPayload.getVerifyOTP()).equals(("true"))) {
    			System.out.println("otp is not null..verifying it");
    			int otp=Integer.parseInt(enc.decryptnew(signUpPayload.getOTP()));
	    		Boolean verifyOTP=verifyOTP(otp,mobile_no);
	    		System.out.println("verifyOTP");
	        	System.out.println(verifyOTP);
				    			if (verifyOTP.equals(true))
				    			{
				    				System.out.println("verifyOTp is true");
				    				return ResponseEntity.status(HttpStatus.OK)
											.body(new SignUpResponse(enc.encryptnew(OTPVerified),"true",signUpPayload.getName(),
													signUpPayload.getMobile_no(),signUpPayload.getDate_of_birth()));
				    			}
				    			else {
				    				return ResponseEntity.status(HttpStatus.OK)
											.body(new SignUpResponse(enc.encryptnew(IncorrectOTP),"false",null,null,null));
				    			}
    		}
    		else {
    			
    			BorrowerEntity brwEntity=new BorrowerEntity();
	    		brwEntity.setMobile_no(mobile_no);
	    		brwEntity.setMpin(Integer.parseInt(enc.decryptnew(signUpPayload.getMpin())));
	    		brwEntity.setUsername(enc.decryptnew(signUpPayload.getName()));
	    		borrowerRepo.save(brwEntity);
	    		return ResponseEntity.status(HttpStatus.OK)
						.body(new SignUpResponse(enc.encryptnew(SignUpOk),"True",null,null,null));
    		}
    		}
    	catch(Exception e){
    		System.out.println("error is"+e);
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SignUpResponse(enc.encryptnew("Error in sign up")+e+e.getClass(),"FALSE",null,null,null));
    	}}
    	
				    				
    	/*				Boolean generateOTP=generateOTP(mobile_no,"ektasharma011@gmail.com");
    						System.out.println("generateOTP");
    							System.out.println(generateOTP);
    							if (generateOTP==true) {
    								System.out.println("otp is generated");
    								return ResponseEntity.status(HttpStatus.OK)
    										.body(new SignInResponse(enc.encryptnew(IncorrectOTP),Boolean.FALSE));
    							}
    							else {
    								System.out.println("otp not generated");
    								return ResponseEntity.status(HttpStatus.OK)
    										.body(new SignInResponse(enc.encryptnew(OtpgenerationError),Boolean.FALSE));
    							}}
    			else {System.out.println("otp is not null..verifying it");
    			int otp=Integer.parseInt(enc.decryptnew(signUpPayload.getOTP()));
		    				Boolean verifyOTP=verifyOTP(otp,mobile_no);
		    			System.out.println("verifyOTP");
		        		System.out.println(verifyOTP);
					    			if (verifyOTP.equals(true))
					    			{
					    				System.out.println("verifyOTp is true");
					    		BorrowerMgmntEntity brw=new BorrowerMgmntEntity();
					    	//	brw.setUser_id(user_id);
					    		//brw.setFirst_Name(enc.decryptnew(signUpPayload.getFirst_Name()));
					    	//	brw.setLast_name(enc.decryptnew(signUpPayload.getLast_Name()));
					    		//System.out.println("Last Name is"+enc.decryptnew(signUpPayload.getLast_Name()));
					
					    	//	brw.setAddress(enc.decryptnew(signUpPayload.getAddress()));
					    		System.out.println("address is"+enc.decryptnew(signUpPayload.getAddress()));
					    	//	brw.setMobile_no(mobile_no);
					    		
					    		BorrowerEntity brwEntity=new BorrowerEntity();
					    		brwEntity.setMobile_no(mobile_no);
					    		brwEntity.setEmail(email);
					    		brwEntity.setMpin(Integer.parseInt(enc.decryptnew(signUpPayload.getMpin())));
					    		brwEntity.setUser_id(user_id);
					    		brwEntity.setUsername(enc.decryptnew(signUpPayload.getFirst_Name()));
					    		//brwRepo.save(brw);
					    		borrowerRepo.save(brwEntity);
					    		System.out.println("signup ok");
					    		return ResponseEntity.status(HttpStatus.OK)
										.body(new SignInResponse(enc.encryptnew(SignUpOk),Boolean.TRUE));
					    			}
					    			else {
					    				System.out.println("verifyOTP is false");
					    				return ResponseEntity.status(HttpStatus.OK)
					    						.body(new SignInResponse(enc.encryptnew(IncorrectOTP),Boolean.FALSE));
					    			}
		    			}
    	}
	
    	
    	else {
    		System.out.println("user already exist");
    			return ResponseEntity.status(HttpStatus.OK)
						.body(new SignInResponse(enc.encryptnew(UserExist),Boolean.FALSE));
    		}}
    		else {
    			System.out.println("borrowre does not exist");
    			return ResponseEntity.status(HttpStatus.OK)
						.body(new SignInResponse(enc.encryptnew(SignUpBorrowerWrongCredential),Boolean.FALSE));
    		}	
	
	}
	
    	catch(Exception e){
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new SignInResponse(enc.encryptnew("Error in sign up")+e,Boolean.FALSE));
    	}*/
	
	 public Boolean generateOTP(Long mobile_no,String email) {
		try{
			//OTPRepo.deleteOTP(mobile_no);
			 Random rnd = new Random();
			 int otp = 100000 + rnd.nextInt(900000);
			 OTPEntity otpEn=new OTPEntity();
			 otpEn.setMobile_no(mobile_no);
			 otpEn.setEmail(email);
			 otpEn.setOtp(otp);
			 Date currDatetime=new Date();
			 otpEn.setSendDateTime(currDatetime);
			 OTPRepo.save(otpEn);
			 //send mail
			// sendMail.emailToUser(email,otp,username_mail,password,host,port);	
			// mail.sendEmail(otp, email);
			 sendEmailService.sendEmail1(email,otp);
			return true;
		}
catch(Exception e){
	log.error("error in otp generation"+e);
    		return true;
    	}
		 
	 }
	 public Boolean verifyOTP(int OTP, Long mobile_no) {
		 try {
			 ArrayList<Date> op=OTPRepo.verifyOTP(mobile_no, OTP);
			 System.out.println("outcome of verify otp list");
			 System.out.println(op);
			 if(null==op) {
				 log.info("otp is not verified for mobile no",mobile_no);
			 return false;
		 }
			 else {
				 Date otpDate=op.get(0);
				 System.out.println("Otp date is");
				 System.out.println(otpDate);
				 Date currDatetime=new Date();
				 System.out.println(currDatetime);
				 Long difference=currDatetime.getTime()-otpDate.getTime();
				 System.out.println("difference is");
				 System.out.println(difference);
				 long diffSeconds=TimeUnit.MILLISECONDS.toSeconds(difference);
				 System.out.println(diffSeconds);
				 if(diffSeconds<2000)
				 	{ log.info("otp verified for mobile no",mobile_no);
				 		return true;}
				 	else {
					 log.info("otp expired",mobile_no);
					 return false;
				 }
			 }
		 }
		 catch(Exception e){
				log.error("error in otp verification"+e);
			    		return false;
			    	}
		 
	 }
	 @PostMapping(
	            value = "/changeMpin",
	            consumes = {MediaType.APPLICATION_JSON_VALUE},
	            produces = {MediaType.APPLICATION_JSON_VALUE})
	 
	    public ResponseEntity<SignInResponse> changeMpin(@RequestBody  ChangeMpinPayload mpinPayload){
		  
	    	try {
	    		Long mobile_no=Long.parseLong(enc.decryptnew(mpinPayload.getMobile_no()));
	    		//String email=borrowerRepo.email4mMobile(mobile_no);
	    		//System.out.println(email);
	    		if (null==mpinPayload.getOtp()) {
	    		Boolean generateOtp=generateOTP(mobile_no,"ramgopalrai566@gmail.com");
	    			if(generateOtp.equals(true)) {
	    			System.out.println("OTP generated");
	    			return ResponseEntity.status(HttpStatus.OK)
    						.body(new SignInResponse(enc.encryptnew("OTP is generated successfully"),Boolean.TRUE));
	    			}
	    			else {
	    			System.out.println("OTP not generated");
	    			return ResponseEntity.status(HttpStatus.OK)
    						.body(new SignInResponse(enc.encryptnew(OtpgenerationError),Boolean.FALSE));
	    			}
	    			}
	    		else {
	    			System.out.println("OTP  is null");
	    			if(null==mpinPayload.getMpin()){
	    			System.out.println("OTP  and mpin are null");
	    			int otp=Integer.parseInt(enc.decryptnew(mpinPayload.getOtp()));
	    			Boolean verifyOtp=verifyOTP(otp,mobile_no);
	    			if (verifyOtp.equals(true)) {
	    				System.out.println("OTP verified");
	    			//	borrowerRepo.changeMpin4mMobile(Integer.parseInt(enc.decryptnew(mpinPayload.getMpin())),mobile_no);
	    		//	log.info("Mpin changed successfully");
	    			return ResponseEntity.status(HttpStatus.OK)
	    					.body(new SignInResponse(enc.encryptnew("OTP verified"),Boolean.TRUE));
	    			}
	    			else {
	    				return ResponseEntity.status(HttpStatus.OK)
	    						.body(new SignInResponse(enc.encryptnew(IncorrectOTP),Boolean.FALSE));
	    				
	    			}}
	    			else {
	    				System.out.println("OTP  and mpin are not null");
	    				borrowerRepo.changeMpin4mMobile(Integer.parseInt(enc.decryptnew(mpinPayload.getMpin())),mobile_no);
	    	    			log.info("Mpin changed successfully");
	    	    			return ResponseEntity.status(HttpStatus.OK)
	    	    					.body(new SignInResponse(enc.encryptnew(Mpinchanged),Boolean.TRUE));
	    			}
	    		}
	    			
	    		
	   }
	    	catch(Exception e) {
		    	  System.out.println("error in changing mpin api"+e);
		    	 log.error("error in mpin change of mobile_no"+e);
		    
		    	  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body(new SignInResponse(("error in mpin change of mobile_no"+e),Boolean.FALSE));
		      }
	    }
	    /*	 @PostMapping(
	            value = "/SignUp1",
	            consumes = {MediaType.APPLICATION_JSON_VALUE},
	            produces = {MediaType.APPLICATION_JSON_VALUE})
	 
	    public ResponseEntity<SignInResponse> SignUp1(@RequestBody  SignUpPayload signupPayload){
		  
	    	try {
	    		Long mobile_no=(Long.parseLong(enc.decryptnew(signupPayload.getMobile_no())));
	    		System.out.println(mobile_no);
	    		System.out.println(signupPayload.getName());
	    		System.out.println(signupPayload.getDate_of_birth());
	    		
	   	String applicant_id=masterRepo.signUpcheck(mobile_no,enc.decryptnew(signupPayload.getName()),enc.decryptnew(signupPayload.getDate_of_birth()));
	    	System.out.println(applicant_id);

	    	if(null==applicant_id) {
	    		return ResponseEntity.status(HttpStatus.OK)
						.body(new SignInResponse(enc.encryptnew(SignUpBorrowerWrongCredential),Boolean.FALSE));
	    	}
	    	else {
	    		return ResponseEntity.status(HttpStatus.OK)
						.body(new SignInResponse(enc.encryptnew(SignUpBorrowerExist),Boolean.TRUE));
	    	}
	    	}
	    	 catch(Exception e) {
		    	  System.out.println("error in sihn up api"+e);
		    	 log.error("error in signup of mobile_no"+e);
		    
		    	  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body(new SignInResponse((SignInError+e.getClass().getSimpleName()),Boolean.FALSE));
	    	 }
	    
}*/
	    	 public boolean borrowerCheck(String name,Date DOB, long mobile_no) {
	    		 try {
	    			 BorrowerMgmntEntity br=brwRepo.signUpcheck(mobile_no, name, DOB);
	    			 if(null==br) {
	    				 System.out.println("Borrower doen not exist");
	    				 return false;}
	    			 else {
	    				 System.out.println("Borrower  exist");
	    				 return true; 
	    			 }
	    			 
	    		 }
	    		 catch(Exception e) {
			    	  System.out.println("error in checking borrower existence"+e);
			    	 log.error("error in checking borrower existence"+e);
			    
			    	  return false;
		    	 } 
	    	 }
	    	 @PostMapping(
	 	            value = "/metaOpenURL",
	 	            consumes = {MediaType.APPLICATION_JSON_VALUE},
	 	            produces = {MediaType.APPLICATION_JSON_VALUE})
	 	 public ResponseEntity<metaOpenURLResponse> metaOpenURL (@RequestBody  MetaOpenURLPayload metaOpenURL){
	 		 LoanDetails lnD=new LoanDetails();
	 		 System.out.println(metaOpenURL.getName());
	 		System.out.println(metaOpenURL.getClanguage());
	 		System.out.println(metaOpenURL.getAccnum());
	 		System.out.println("loanamount is"+metaOpenURL.getLoanamout());
	 		System.out.println("emi amount is"+metaOpenURL.getEMIamount());
	 		System.out.println("pending amount"+metaOpenURL.getPendingamount());
	 		System.out.println(metaOpenURL.getPemi());
	 		System.out.println(metaOpenURL.getDuedate());
	 	    	try {
	 	    		if(metaOpenURL.getName()!=null&& metaOpenURL.getClanguage()!=null
	 	    				&& metaOpenURL.getAccnum()!=null && metaOpenURL.getLoanamout()!=null &&
	 	    				metaOpenURL.getPendingamount()!=null && metaOpenURL.getEMIamount()==null &&
	 	    				metaOpenURL.getDuedate()!=null && metaOpenURL.getPemi()!=null) {
	 	    			
	 	    			Runtime rt = Runtime.getRuntime();
	 	    			String url = "https://metaverse.easiofy.com/ArthaVedika/index.html?"+metaOpenURL.getName();
	 	    			rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
	 	    	     return ResponseEntity.status(HttpStatus.OK)
		 						.body(new metaOpenURLResponse("url opened successfully",("True")));
	 	    		}
	 	    		else {
	 	    			return ResponseEntity.status(HttpStatus.OK)
		 						.body(new metaOpenURLResponse("Data is incomplete. Please fill it.",("True")));
	 	    		}
	 	    		}
	     	
	 	   catch(Exception e) {
	 	    	  System.out.println("error in  api"+e);
	 	    	 log.error("error in opening url details"+e);
	 	    
	 	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
 						.body(new metaOpenURLResponse("error in opening url"+e,("False")));
	 	      }
	 	    	}
}
	    	 