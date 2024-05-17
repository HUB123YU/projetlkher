package ma.zs.stocky.dao.facade.core.appartenance;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.appartenance.Genre;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.appartenance.Genre;
import java.util.List;


@Repository
public interface GenreDao extends AbstractRepository<Genre,Long>  {
    Genre findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Genre(item.id,item.code) FROM Genre item")
    List<Genre> findAllOptimized();

}
