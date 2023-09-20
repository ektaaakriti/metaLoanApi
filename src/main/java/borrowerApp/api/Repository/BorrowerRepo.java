package borrowerApp.api.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import borrowerApp.api.entity.BorrowerEntity;

@Repository
public interface BorrowerRepo extends JpaRepository<BorrowerEntity,Long> {

//@Query("SELECT a FROM BorrowerEntity a WHERE a.User_id=?1")
//public BorrowerEntity useridcheck(int User_id);
@Query("SELECT a FROM BorrowerEntity a WHERE a.mobile_no =?1 and mpin=?2")
public BorrowerEntity signin(Long mobile_no,int mpin);
@Transactional
@Modifying
@Query("update BorrowerEntity a set is_login='Y' , login_datetime=?1 where mobile_no=?2")
public void updateSignInDetails(Date login_datetime,Long mobile_no);

@Query("SELECT a.email FROM BorrowerEntity a WHERE a.mobile_no =?1 ")
public String email4mMobile(Long mobile_no);
@Transactional
@Modifying
@Query("update BorrowerEntity a set OTP=?1 where mobile_no=?2")
public void updateOTP4mMobile(int OTP,Long mobile_no);
@Transactional
@Modifying
@Query("update BorrowerEntity a set mpin=?1 where mobile_no=?2")
public void changeMpin4mMobile(int mpin,Long mobile_no);
@Query("SELECT a.User_id FROM BorrowerEntity a WHERE a.mobile_no =?1 ")
public Integer user_Id4mMobile(Long mobile_no);
}
