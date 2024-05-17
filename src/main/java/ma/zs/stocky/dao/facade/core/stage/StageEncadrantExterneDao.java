package ma.zs.stocky.dao.facade.core.stage;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.stage.StageEncadrantExterne;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StageEncadrantExterneDao extends AbstractRepository<StageEncadrantExterne,Long>  {

    List<StageEncadrantExterne> findByStageId(Long id);
    int deleteByStageId(Long id);
    long countByStageId(Long id);
    List<StageEncadrantExterne> findByEncadrantExterneId(Long id);
    int deleteByEncadrantExterneId(Long id);
    long countByEncadrantExterneCode(String code);


}
