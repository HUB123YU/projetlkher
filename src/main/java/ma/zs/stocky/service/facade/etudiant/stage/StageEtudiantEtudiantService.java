package ma.zs.stocky.service.facade.etudiant.stage;

import java.util.List;
import ma.zs.stocky.bean.core.stage.StageEtudiant;
import ma.zs.stocky.dao.criteria.core.stage.StageEtudiantCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface StageEtudiantEtudiantService {



    List<StageEtudiant> findByStageId(Long id);
    int deleteByStageId(Long id);
    long countByStageId(Long id);
    List<StageEtudiant> findByEtudiantId(Long id);
    int deleteByEtudiantId(Long id);
    long countByEtudiantId(Long id);




	StageEtudiant create(StageEtudiant t);

    StageEtudiant update(StageEtudiant t);

    List<StageEtudiant> update(List<StageEtudiant> ts,boolean createIfNotExist);

    StageEtudiant findById(Long id);

    StageEtudiant findOrSave(StageEtudiant t);

    StageEtudiant findByReferenceEntity(StageEtudiant t);

    StageEtudiant findWithAssociatedLists(Long id);

    List<StageEtudiant> findAllOptimized();

    List<StageEtudiant> findAll();

    List<StageEtudiant> findByCriteria(StageEtudiantCriteria criteria);

    List<StageEtudiant> findPaginatedByCriteria(StageEtudiantCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StageEtudiantCriteria criteria);

    List<StageEtudiant> delete(List<StageEtudiant> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<StageEtudiant>> getToBeSavedAndToBeDeleted(List<StageEtudiant> oldList, List<StageEtudiant> newList);

    List<StageEtudiant> importData(List<StageEtudiant> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<StageEtudiant> importExcel(MultipartFile file);

}
