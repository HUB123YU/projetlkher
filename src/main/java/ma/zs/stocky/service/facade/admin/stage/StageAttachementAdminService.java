package ma.zs.stocky.service.facade.admin.stage;

import java.util.List;
import ma.zs.stocky.bean.core.stage.StageAttachement;
import ma.zs.stocky.dao.criteria.core.stage.StageAttachementCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface StageAttachementAdminService {



    List<StageAttachement> findByStageId(Long id);
    int deleteByStageId(Long id);
    long countByStageId(Long id);
    List<StageAttachement> findByAttachementId(Long id);
    int deleteByAttachementId(Long id);
    long countByAttachementId(Long id);




	StageAttachement create(StageAttachement t);

    StageAttachement update(StageAttachement t);

    List<StageAttachement> update(List<StageAttachement> ts,boolean createIfNotExist);

    StageAttachement findById(Long id);

    StageAttachement findOrSave(StageAttachement t);

    StageAttachement findByReferenceEntity(StageAttachement t);

    StageAttachement findWithAssociatedLists(Long id);

    List<StageAttachement> findAllOptimized();

    List<StageAttachement> findAll();

    List<StageAttachement> findByCriteria(StageAttachementCriteria criteria);

    List<StageAttachement> findPaginatedByCriteria(StageAttachementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StageAttachementCriteria criteria);

    List<StageAttachement> delete(List<StageAttachement> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<StageAttachement>> getToBeSavedAndToBeDeleted(List<StageAttachement> oldList, List<StageAttachement> newList);

    List<StageAttachement> importData(List<StageAttachement> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<StageAttachement> importExcel(MultipartFile file);

}
