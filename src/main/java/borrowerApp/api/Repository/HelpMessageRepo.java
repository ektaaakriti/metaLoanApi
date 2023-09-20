package borrowerApp.api.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import borrowerApp.api.entity.HelpMessageEntity;
@Repository
public interface HelpMessageRepo extends JpaRepository<HelpMessageEntity,Long>{
@Query("select a from HelpMessageEntity a where user_id=?1 and help_message=?2")
public HelpMessageEntity getHelpNo(int user_id, String help_message);
}
