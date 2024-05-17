package ma.zs.stocky.dao.facade.core.departement;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.departement.Departement;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.departement.Departement;
import java.util.List;


@Repository
public interface DepartementDao extends AbstractRepository<Departement,Long>  {
    Departement findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Departement(item.id,item.code) FROM Departement item")
    List<Departement> findAllOptimized();

}
