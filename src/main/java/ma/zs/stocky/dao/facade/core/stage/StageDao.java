package ma.zs.stocky.dao.facade.core.stage;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.stage.Stage;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StageDao extends AbstractRepository<Stage,Long>  {

    List<Stage> findByDomaineId(Long id);
    int deleteByDomaineId(Long id);
    long countByDomaineCode(String code);
    List<Stage> findBySocieteId(Long id);
    int deleteBySocieteId(Long id);
    long countBySocieteIce(String ice);
    List<Stage> findByJuryId(Long id);
    int deleteByJuryId(Long id);
    long countByJuryRef(String ref);
    List<Stage> findByFiliereId(Long id);
    int deleteByFiliereId(Long id);
    long countByFiliereCode(String code);
    List<Stage> findByTypeStageId(Long id);
    int deleteByTypeStageId(Long id);
    long countByTypeStageReference(String reference);


}
