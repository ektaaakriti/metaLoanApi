package borrowerApp.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import borrowerApp.api.entity.EMIEntity;
import borrowerApp.api.entity.EMI_Id;



public interface EMIRepo  extends JpaRepository<EMIEntity,EMI_Id>{
	@Query("select a from EMIEntity a where a.loan_no=?1 order by a.emi_date DESC")
	public List<EMIEntity> getEmiDtls(int loan_no);
}
