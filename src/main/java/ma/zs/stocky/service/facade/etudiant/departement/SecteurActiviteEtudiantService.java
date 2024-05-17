package ma.zs.stocky.service.facade.etudiant.departement;

import java.util.List;
import ma.zs.stocky.bean.core.departement.SecteurActivite;
import ma.zs.stocky.dao.criteria.core.departement.SecteurActiviteCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface SecteurActiviteEtudiantService {







	SecteurActivite create(SecteurActivite t);

    SecteurActivite update(SecteurActivite t);

    List<SecteurActivite> update(List<SecteurActivite> ts,boolean createIfNotExist);

    SecteurActivite findById(Long id);

    SecteurActivite findOrSave(SecteurActivite t);

    SecteurActivite findByReferenceEntity(SecteurActivite t);

    SecteurActivite findWithAssociatedLists(Long id);

    List<SecteurActivite> findAllOptimized();

    List<SecteurActivite> findAll();

    List<SecteurActivite> findByCriteria(SecteurActiviteCriteria criteria);

    List<SecteurActivite> findPaginatedByCriteria(SecteurActiviteCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SecteurActiviteCriteria criteria);

    List<SecteurActivite> delete(List<SecteurActivite> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<SecteurActivite>> getToBeSavedAndToBeDeleted(List<SecteurActivite> oldList, List<SecteurActivite> newList);

    List<SecteurActivite> importData(List<SecteurActivite> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<SecteurActivite> importExcel(MultipartFile file);

}
