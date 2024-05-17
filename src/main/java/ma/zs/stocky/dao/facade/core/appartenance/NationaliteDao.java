package ma.zs.stocky.dao.facade.core.appartenance;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.appartenance.Nationalite;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.appartenance.Nationalite;
import java.util.List;


@Repository
public interface NationaliteDao extends AbstractRepository<Nationalite,Long>  {
    Nationalite findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Nationalite(item.id,item.code) FROM Nationalite item")
    List<Nationalite> findAllOptimized();

}
