package borrowerApp.api.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import borrowerApp.api.entity.BorrowerEntity;
import borrowerApp.api.entity.BorrowerMgmntEntity;
@Repository
public interface BorrowerMgmntRepo extends JpaRepository<BorrowerMgmntEntity,Long> {
	@Query("SELECT a FROM BorrowerMgmntEntity a WHERE a.mobile_no=?1 and a.First_Name=?2 and a.date_of_birth=?3")
	public BorrowerMgmntEntity signUpcheck(Long mobile,String member_name, Date date_of_birth);
	@Query("SELECT a FROM BorrowerMgmntEntity a WHERE a.mobile_no=?1")
	public BorrowerMgmntEntity fetchpersonalDtls(long mobile_no);
	@Query("SELECT a.user_id FROM BorrowerMgmntEntity a WHERE a.mobile_no=?1")
	public Integer fetchUser_id(long mobile_no);

}
