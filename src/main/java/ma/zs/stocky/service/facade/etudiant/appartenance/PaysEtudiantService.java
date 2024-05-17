package ma.zs.stocky.service.facade.etudiant.appartenance;

import java.util.List;
import ma.zs.stocky.bean.core.appartenance.Pays;
import ma.zs.stocky.dao.criteria.core.appartenance.PaysCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PaysEtudiantService {







	Pays create(Pays t);

    Pays update(Pays t);

    List<Pays> update(List<Pays> ts,boolean createIfNotExist);

    Pays findById(Long id);

    Pays findOrSave(Pays t);

    Pays findByReferenceEntity(Pays t);

    Pays findWithAssociatedLists(Long id);

    List<Pays> findAllOptimized();

    List<Pays> findAll();

    List<Pays> findByCriteria(PaysCriteria criteria);

    List<Pays> findPaginatedByCriteria(PaysCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaysCriteria criteria);

    List<Pays> delete(List<Pays> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Pays>> getToBeSavedAndToBeDeleted(List<Pays> oldList, List<Pays> newList);

    List<Pays> importData(List<Pays> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Pays> importExcel(MultipartFile file);

}
