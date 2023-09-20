package borrowerApp.api.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import borrowerApp.api.entity.*;
@Repository
public interface OTPRepo extends JpaRepository<OTPEntity,Long> {
	@Transactional
	@Modifying
	@Query("delete from OTPEntity a where a.mobile_no=?1")
	public void deleteOTP(Long mobile_no);
	@Query("SELECT a.sendDateTime FROM OTPEntity a WHERE a.mobile_no =?1 and otp=?2 ORDER BY a.sendDateTime DESC ")
	public ArrayList<Date> verifyOTP(Long mobile_no,int otp);
}
