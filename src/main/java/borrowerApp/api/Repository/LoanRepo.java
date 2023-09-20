package borrowerApp.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import borrowerApp.api.entity.BorrowerEntity;
import borrowerApp.api.entity.Loan;

public interface LoanRepo extends JpaRepository<Loan,Long> {
	@Query("SELECT a FROM Loan a WHERE a.Loan_no=?1")
	public Loan Loan_dtls(int Loan_no);
}
