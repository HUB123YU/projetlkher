package ma.zs.stocky.dao.facade.core.stage;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.stage.StageAttachement;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StageAttachementDao extends AbstractRepository<StageAttachement,Long>  {

    List<StageAttachement> findByStageId(Long id);
    int deleteByStageId(Long id);
    long countByStageId(Long id);
    List<StageAttachement> findByAttachementId(Long id);
    int deleteByAttachementId(Long id);
    long countByAttachementId(Long id);


}
