package ma.zs.stocky.dao.facade.core.piecejointe;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.piecejointe.TypeAttachement;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.piecejointe.TypeAttachement;
import java.util.List;


@Repository
public interface TypeAttachementDao extends AbstractRepository<TypeAttachement,Long>  {
    TypeAttachement findByReference(String reference);
    int deleteByReference(String reference);


    @Query("SELECT NEW TypeAttachement(item.id,item.libelle) FROM TypeAttachement item")
    List<TypeAttachement> findAllOptimized();

}
