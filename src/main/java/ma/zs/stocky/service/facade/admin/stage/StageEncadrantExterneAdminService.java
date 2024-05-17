package ma.zs.stocky.service.facade.admin.stage;

import java.util.List;
import ma.zs.stocky.bean.core.stage.StageEncadrantExterne;
import ma.zs.stocky.dao.criteria.core.stage.StageEncadrantExterneCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface StageEncadrantExterneAdminService {



    List<StageEncadrantExterne> findByStageId(Long id);
    int deleteByStageId(Long id);
    long countByStageId(Long id);
    List<StageEncadrantExterne> findByEncadrantExterneId(Long id);
    int deleteByEncadrantExterneId(Long id);
    long countByEncadrantExterneCode(String code);




	StageEncadrantExterne create(StageEncadrantExterne t);

    StageEncadrantExterne update(StageEncadrantExterne t);

    List<StageEncadrantExterne> update(List<StageEncadrantExterne> ts,boolean createIfNotExist);

    StageEncadrantExterne findById(Long id);

    StageEncadrantExterne findOrSave(StageEncadrantExterne t);

    StageEncadrantExterne findByReferenceEntity(StageEncadrantExterne t);

    StageEncadrantExterne findWithAssociatedLists(Long id);

    List<StageEncadrantExterne> findAllOptimized();

    List<StageEncadrantExterne> findAll();

    List<StageEncadrantExterne> findByCriteria(StageEncadrantExterneCriteria criteria);

    List<StageEncadrantExterne> findPaginatedByCriteria(StageEncadrantExterneCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StageEncadrantExterneCriteria criteria);

    List<StageEncadrantExterne> delete(List<StageEncadrantExterne> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<StageEncadrantExterne>> getToBeSavedAndToBeDeleted(List<StageEncadrantExterne> oldList, List<StageEncadrantExterne> newList);

    List<StageEncadrantExterne> importData(List<StageEncadrantExterne> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<StageEncadrantExterne> importExcel(MultipartFile file);

}
