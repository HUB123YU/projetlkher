package ma.zs.stocky.dao.facade.core.societe;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.societe.Societe;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.societe.Societe;
import java.util.List;


@Repository
public interface SocieteDao extends AbstractRepository<Societe,Long>  {
    Societe findByIce(String ice);
    int deleteByIce(String ice);

    List<Societe> findByVilleId(Long id);
    int deleteByVilleId(Long id);
    long countByVilleReference(String reference);
    List<Societe> findBySecteurActiviteId(Long id);
    int deleteBySecteurActiviteId(Long id);
    long countBySecteurActiviteReference(String reference);
    List<Societe> findByPaysId(Long id);
    int deleteByPaysId(Long id);
    long countByPaysReference(String reference);

    @Query("SELECT NEW Societe(item.id,item.ice) FROM Societe item")
    List<Societe> findAllOptimized();

}
