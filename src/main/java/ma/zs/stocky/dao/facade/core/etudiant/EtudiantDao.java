package ma.zs.stocky.dao.facade.core.etudiant;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.etudiant.Etudiant;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EtudiantDao extends AbstractRepository<Etudiant,Long>  {

    List<Etudiant> findByGenreId(Long id);
    int deleteByGenreId(Long id);
    long countByGenreCode(String code);
    List<Etudiant> findByNationaliteId(Long id);
    int deleteByNationaliteId(Long id);
    long countByNationaliteCode(String code);
    List<Etudiant> findByFiliereId(Long id);
    int deleteByFiliereId(Long id);
    long countByFiliereCode(String code);
    Etudiant findByUsername(String username);


}
