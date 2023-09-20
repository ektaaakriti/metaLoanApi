package borrowerApp.api.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import borrowerApp.api.entity.BorrowerLoanEntity;
import borrowerApp.api.entity.BorrowerMgmntEntity;
@Repository
public interface BorrowerLoanrepo extends JpaRepository<BorrowerLoanEntity,Long> {
	@Query("SELECT a.loan_no FROM BorrowerLoanEntity a WHERE a.Borrower_user_id=?1")
	public List<Integer> loan_no(int Borrower_user_id);
}
