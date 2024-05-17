package ma.zs.stocky.service.facade.admin.encadrant;

import java.util.List;
import ma.zs.stocky.bean.core.encadrant.JuryEncadrantInterne;
import ma.zs.stocky.dao.criteria.core.encadrant.JuryEncadrantInterneCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface JuryEncadrantInterneAdminService {



    List<JuryEncadrantInterne> findByEncadrantInterneId(Long id);
    int deleteByEncadrantInterneId(Long id);
    long countByEncadrantInterneCode(String code);
    List<JuryEncadrantInterne> findByJuryId(Long id);
    int deleteByJuryId(Long id);
    long countByJuryRef(String ref);




	JuryEncadrantInterne create(JuryEncadrantInterne t);

    JuryEncadrantInterne update(JuryEncadrantInterne t);

    List<JuryEncadrantInterne> update(List<JuryEncadrantInterne> ts,boolean createIfNotExist);

    JuryEncadrantInterne findById(Long id);

    JuryEncadrantInterne findOrSave(JuryEncadrantInterne t);

    JuryEncadrantInterne findByReferenceEntity(JuryEncadrantInterne t);

    JuryEncadrantInterne findWithAssociatedLists(Long id);

    List<JuryEncadrantInterne> findAllOptimized();

    List<JuryEncadrantInterne> findAll();

    List<JuryEncadrantInterne> findByCriteria(JuryEncadrantInterneCriteria criteria);

    List<JuryEncadrantInterne> findPaginatedByCriteria(JuryEncadrantInterneCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(JuryEncadrantInterneCriteria criteria);

    List<JuryEncadrantInterne> delete(List<JuryEncadrantInterne> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<JuryEncadrantInterne>> getToBeSavedAndToBeDeleted(List<JuryEncadrantInterne> oldList, List<JuryEncadrantInterne> newList);

    List<JuryEncadrantInterne> importData(List<JuryEncadrantInterne> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<JuryEncadrantInterne> importExcel(MultipartFile file);

}
