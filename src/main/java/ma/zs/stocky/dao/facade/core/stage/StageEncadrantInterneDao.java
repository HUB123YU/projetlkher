package ma.zs.stocky.dao.facade.core.stage;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.stage.StageEncadrantInterne;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StageEncadrantInterneDao extends AbstractRepository<StageEncadrantInterne,Long>  {

    List<StageEncadrantInterne> findByStageId(Long id);
    int deleteByStageId(Long id);
    long countByStageId(Long id);
    List<StageEncadrantInterne> findByEncadrantInterneId(Long id);
    int deleteByEncadrantInterneId(Long id);
    long countByEncadrantInterneCode(String code);


}
