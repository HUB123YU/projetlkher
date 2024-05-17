package ma.zs.stocky.service.facade.etudiant.appartenance;

import java.util.List;
import ma.zs.stocky.bean.core.appartenance.Genre;
import ma.zs.stocky.dao.criteria.core.appartenance.GenreCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface GenreEtudiantService {







	Genre create(Genre t);

    Genre update(Genre t);

    List<Genre> update(List<Genre> ts,boolean createIfNotExist);

    Genre findById(Long id);

    Genre findOrSave(Genre t);

    Genre findByReferenceEntity(Genre t);

    Genre findWithAssociatedLists(Long id);

    List<Genre> findAllOptimized();

    List<Genre> findAll();

    List<Genre> findByCriteria(GenreCriteria criteria);

    List<Genre> findPaginatedByCriteria(GenreCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(GenreCriteria criteria);

    List<Genre> delete(List<Genre> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Genre>> getToBeSavedAndToBeDeleted(List<Genre> oldList, List<Genre> newList);

    List<Genre> importData(List<Genre> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Genre> importExcel(MultipartFile file);

}
