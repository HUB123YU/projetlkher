package ma.zs.stocky.dao.facade.core.appartenance;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.appartenance.Ville;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.appartenance.Ville;
import java.util.List;


@Repository
public interface VilleDao extends AbstractRepository<Ville,Long>  {
    Ville findByReference(String reference);
    int deleteByReference(String reference);


    @Query("SELECT NEW Ville(item.id,item.libelle) FROM Ville item")
    List<Ville> findAllOptimized();

}
