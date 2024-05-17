package ma.zs.stocky.dao.facade.core.piecejointe;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.piecejointe.Attachement;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AttachementDao extends AbstractRepository<Attachement,Long>  {

    List<Attachement> findByTypeAttachementId(Long id);
    int deleteByTypeAttachementId(Long id);
    long countByTypeAttachementReference(String reference);
    List<Attachement> findByStageId(Long id);
    int deleteByStageId(Long id);
    long countByStageId(Long id);


}
