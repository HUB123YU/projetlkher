package ma.zs.stocky.service.facade.etudiant.stage;

import java.util.List;
import ma.zs.stocky.bean.core.stage.TypeStage;
import ma.zs.stocky.dao.criteria.core.stage.TypeStageCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TypeStageEtudiantService {







	TypeStage create(TypeStage t);

    TypeStage update(TypeStage t);

    List<TypeStage> update(List<TypeStage> ts,boolean createIfNotExist);

    TypeStage findById(Long id);

    TypeStage findOrSave(TypeStage t);

    TypeStage findByReferenceEntity(TypeStage t);

    TypeStage findWithAssociatedLists(Long id);

    List<TypeStage> findAllOptimized();

    List<TypeStage> findAll();

    List<TypeStage> findByCriteria(TypeStageCriteria criteria);

    List<TypeStage> findPaginatedByCriteria(TypeStageCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeStageCriteria criteria);

    List<TypeStage> delete(List<TypeStage> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TypeStage>> getToBeSavedAndToBeDeleted(List<TypeStage> oldList, List<TypeStage> newList);

    List<TypeStage> importData(List<TypeStage> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TypeStage> importExcel(MultipartFile file);

}
