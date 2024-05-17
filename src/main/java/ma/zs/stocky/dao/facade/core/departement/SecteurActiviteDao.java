package ma.zs.stocky.dao.facade.core.departement;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.departement.SecteurActivite;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.departement.SecteurActivite;
import java.util.List;


@Repository
public interface SecteurActiviteDao extends AbstractRepository<SecteurActivite,Long>  {
    SecteurActivite findByReference(String reference);
    int deleteByReference(String reference);


    @Query("SELECT NEW SecteurActivite(item.id,item.libelle) FROM SecteurActivite item")
    List<SecteurActivite> findAllOptimized();

}
