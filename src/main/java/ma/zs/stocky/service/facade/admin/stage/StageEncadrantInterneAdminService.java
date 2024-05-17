package ma.zs.stocky.service.facade.admin.stage;

import java.util.List;
import ma.zs.stocky.bean.core.stage.StageEncadrantInterne;
import ma.zs.stocky.dao.criteria.core.stage.StageEncadrantInterneCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface StageEncadrantInterneAdminService {



    List<StageEncadrantInterne> findByStageId(Long id);
    int deleteByStageId(Long id);
    long countByStageId(Long id);
    List<StageEncadrantInterne> findByEncadrantInterneId(Long id);
    int deleteByEncadrantInterneId(Long id);
    long countByEncadrantInterneCode(String code);




	StageEncadrantInterne create(StageEncadrantInterne t);

    StageEncadrantInterne update(StageEncadrantInterne t);

    List<StageEncadrantInterne> update(List<StageEncadrantInterne> ts,boolean createIfNotExist);

    StageEncadrantInterne findById(Long id);

    StageEncadrantInterne findOrSave(StageEncadrantInterne t);

    StageEncadrantInterne findByReferenceEntity(StageEncadrantInterne t);

    StageEncadrantInterne findWithAssociatedLists(Long id);

    List<StageEncadrantInterne> findAllOptimized();

    List<StageEncadrantInterne> findAll();

    List<StageEncadrantInterne> findByCriteria(StageEncadrantInterneCriteria criteria);

    List<StageEncadrantInterne> findPaginatedByCriteria(StageEncadrantInterneCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StageEncadrantInterneCriteria criteria);

    List<StageEncadrantInterne> delete(List<StageEncadrantInterne> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<StageEncadrantInterne>> getToBeSavedAndToBeDeleted(List<StageEncadrantInterne> oldList, List<StageEncadrantInterne> newList);

    List<StageEncadrantInterne> importData(List<StageEncadrantInterne> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<StageEncadrantInterne> importExcel(MultipartFile file);

}
