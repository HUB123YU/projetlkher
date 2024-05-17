package ma.zs.stocky.service.facade.admin.stage;

import java.util.List;
import ma.zs.stocky.bean.core.stage.Stage;
import ma.zs.stocky.dao.criteria.core.stage.StageCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface StageAdminService {



    List<Stage> findByDomaineId(Long id);
    int deleteByDomaineId(Long id);
    long countByDomaineCode(String code);
    List<Stage> findBySocieteId(Long id);
    int deleteBySocieteId(Long id);
    long countBySocieteIce(String ice);
    List<Stage> findByJuryId(Long id);
    int deleteByJuryId(Long id);
    long countByJuryRef(String ref);
    List<Stage> findByFiliereId(Long id);
    int deleteByFiliereId(Long id);
    long countByFiliereCode(String code);
    List<Stage> findByTypeStageId(Long id);
    int deleteByTypeStageId(Long id);
    long countByTypeStageReference(String reference);




	Stage create(Stage t);

    Stage update(Stage t);

    List<Stage> update(List<Stage> ts,boolean createIfNotExist);

    Stage findById(Long id);

    Stage findOrSave(Stage t);

    Stage findByReferenceEntity(Stage t);

    Stage findWithAssociatedLists(Long id);

    List<Stage> findAllOptimized();

    List<Stage> findAll();

    List<Stage> findByCriteria(StageCriteria criteria);

    List<Stage> findPaginatedByCriteria(StageCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StageCriteria criteria);

    List<Stage> delete(List<Stage> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Stage>> getToBeSavedAndToBeDeleted(List<Stage> oldList, List<Stage> newList);

    List<Stage> importData(List<Stage> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Stage> importExcel(MultipartFile file);

}
