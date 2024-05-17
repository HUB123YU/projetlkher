package ma.zs.stocky.dao.facade.core.departement;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.departement.Filiere;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.departement.Filiere;
import java.util.List;


@Repository
public interface FiliereDao extends AbstractRepository<Filiere,Long>  {
    Filiere findByCode(String code);
    int deleteByCode(String code);

    List<Filiere> findByDepartementId(Long id);
    int deleteByDepartementId(Long id);
    long countByDepartementCode(String code);

    @Query("SELECT NEW Filiere(item.id,item.libelle) FROM Filiere item")
    List<Filiere> findAllOptimized();

}
