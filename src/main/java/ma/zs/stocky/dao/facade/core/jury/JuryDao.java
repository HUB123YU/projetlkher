package ma.zs.stocky.dao.facade.core.jury;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.jury.Jury;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.jury.Jury;
import java.util.List;


@Repository
public interface JuryDao extends AbstractRepository<Jury,Long>  {
    Jury findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW Jury(item.id,item.ref) FROM Jury item")
    List<Jury> findAllOptimized();

}
