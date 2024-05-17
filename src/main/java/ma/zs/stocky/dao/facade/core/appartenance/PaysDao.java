package ma.zs.stocky.dao.facade.core.appartenance;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.appartenance.Pays;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.appartenance.Pays;
import java.util.List;


@Repository
public interface PaysDao extends AbstractRepository<Pays,Long>  {
    Pays findByReference(String reference);
    int deleteByReference(String reference);


    @Query("SELECT NEW Pays(item.id,item.libelle) FROM Pays item")
    List<Pays> findAllOptimized();

}
