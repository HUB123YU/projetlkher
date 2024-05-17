package ma.zs.stocky.dao.facade.core.stage;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.stage.TypeStage;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.stage.TypeStage;
import java.util.List;


@Repository
public interface TypeStageDao extends AbstractRepository<TypeStage,Long>  {
    TypeStage findByReference(String reference);
    int deleteByReference(String reference);


    @Query("SELECT NEW TypeStage(item.id,item.libelle) FROM TypeStage item")
    List<TypeStage> findAllOptimized();

}
