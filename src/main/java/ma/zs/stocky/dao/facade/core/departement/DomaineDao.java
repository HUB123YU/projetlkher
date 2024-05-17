package ma.zs.stocky.dao.facade.core.departement;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.departement.Domaine;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.departement.Domaine;
import java.util.List;


@Repository
public interface DomaineDao extends AbstractRepository<Domaine,Long>  {
    Domaine findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Domaine(item.id,item.code) FROM Domaine item")
    List<Domaine> findAllOptimized();

}
