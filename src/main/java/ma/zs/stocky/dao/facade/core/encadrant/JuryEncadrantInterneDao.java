package ma.zs.stocky.dao.facade.core.encadrant;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.encadrant.JuryEncadrantInterne;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface JuryEncadrantInterneDao extends AbstractRepository<JuryEncadrantInterne,Long>  {

    List<JuryEncadrantInterne> findByEncadrantInterneId(Long id);
    int deleteByEncadrantInterneId(Long id);
    long countByEncadrantInterneCode(String code);
    List<JuryEncadrantInterne> findByJuryId(Long id);
    int deleteByJuryId(Long id);
    long countByJuryRef(String ref);


}
