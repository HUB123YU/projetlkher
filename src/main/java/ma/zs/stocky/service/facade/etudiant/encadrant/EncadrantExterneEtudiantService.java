package ma.zs.stocky.service.facade.etudiant.encadrant;

import java.util.List;
import ma.zs.stocky.bean.core.encadrant.EncadrantExterne;
import ma.zs.stocky.dao.criteria.core.encadrant.EncadrantExterneCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EncadrantExterneEtudiantService {



    List<EncadrantExterne> findBySocieteId(Long id);
    int deleteBySocieteId(Long id);
    long countBySocieteIce(String ice);




	EncadrantExterne create(EncadrantExterne t);

    EncadrantExterne update(EncadrantExterne t);

    List<EncadrantExterne> update(List<EncadrantExterne> ts,boolean createIfNotExist);

    EncadrantExterne findById(Long id);

    EncadrantExterne findOrSave(EncadrantExterne t);

    EncadrantExterne findByReferenceEntity(EncadrantExterne t);

    EncadrantExterne findWithAssociatedLists(Long id);

    List<EncadrantExterne> findAllOptimized();

    List<EncadrantExterne> findAll();

    List<EncadrantExterne> findByCriteria(EncadrantExterneCriteria criteria);

    List<EncadrantExterne> findPaginatedByCriteria(EncadrantExterneCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EncadrantExterneCriteria criteria);

    List<EncadrantExterne> delete(List<EncadrantExterne> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<EncadrantExterne>> getToBeSavedAndToBeDeleted(List<EncadrantExterne> oldList, List<EncadrantExterne> newList);

    List<EncadrantExterne> importData(List<EncadrantExterne> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<EncadrantExterne> importExcel(MultipartFile file);

}
