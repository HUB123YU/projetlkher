package ma.zs.stocky.dao.facade.core.stage;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.stage.StageEtudiant;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StageEtudiantDao extends AbstractRepository<StageEtudiant,Long>  {

    List<StageEtudiant> findByStageId(Long id);
    int deleteByStageId(Long id);
    long countByStageId(Long id);
    List<StageEtudiant> findByEtudiantId(Long id);
    int deleteByEtudiantId(Long id);
    long countByEtudiantId(Long id);


}
