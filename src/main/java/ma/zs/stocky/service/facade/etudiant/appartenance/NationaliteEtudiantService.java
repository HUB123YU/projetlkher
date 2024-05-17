package ma.zs.stocky.service.facade.etudiant.appartenance;

import java.util.List;
import ma.zs.stocky.bean.core.appartenance.Nationalite;
import ma.zs.stocky.dao.criteria.core.appartenance.NationaliteCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface NationaliteEtudiantService {







	Nationalite create(Nationalite t);

    Nationalite update(Nationalite t);

    List<Nationalite> update(List<Nationalite> ts,boolean createIfNotExist);

    Nationalite findById(Long id);

    Nationalite findOrSave(Nationalite t);

    Nationalite findByReferenceEntity(Nationalite t);

    Nationalite findWithAssociatedLists(Long id);

    List<Nationalite> findAllOptimized();

    List<Nationalite> findAll();

    List<Nationalite> findByCriteria(NationaliteCriteria criteria);

    List<Nationalite> findPaginatedByCriteria(NationaliteCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(NationaliteCriteria criteria);

    List<Nationalite> delete(List<Nationalite> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Nationalite>> getToBeSavedAndToBeDeleted(List<Nationalite> oldList, List<Nationalite> newList);

    List<Nationalite> importData(List<Nationalite> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Nationalite> importExcel(MultipartFile file);

}
