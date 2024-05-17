package ma.zs.stocky.dao.facade.core.encadrant;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.encadrant.EncadrantExterne;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.encadrant.EncadrantExterne;
import java.util.List;


@Repository
public interface EncadrantExterneDao extends AbstractRepository<EncadrantExterne,Long>  {
    EncadrantExterne findByCode(String code);
    int deleteByCode(String code);

    List<EncadrantExterne> findBySocieteId(Long id);
    int deleteBySocieteId(Long id);
    long countBySocieteIce(String ice);

    @Query("SELECT NEW EncadrantExterne(item.id,item.code) FROM EncadrantExterne item")
    List<EncadrantExterne> findAllOptimized();

}
