package ma.zs.stocky.service.facade.admin.piecejointe;

import java.util.List;
import ma.zs.stocky.bean.core.piecejointe.Attachement;
import ma.zs.stocky.dao.criteria.core.piecejointe.AttachementCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AttachementAdminService {



    List<Attachement> findByTypeAttachementId(Long id);
    int deleteByTypeAttachementId(Long id);
    long countByTypeAttachementReference(String reference);
    List<Attachement> findByStageId(Long id);
    int deleteByStageId(Long id);
    long countByStageId(Long id);




	Attachement create(Attachement t);

    Attachement update(Attachement t);

    List<Attachement> update(List<Attachement> ts,boolean createIfNotExist);

    Attachement findById(Long id);

    Attachement findOrSave(Attachement t);

    Attachement findByReferenceEntity(Attachement t);

    Attachement findWithAssociatedLists(Long id);

    List<Attachement> findAllOptimized();

    List<Attachement> findAll();

    List<Attachement> findByCriteria(AttachementCriteria criteria);

    List<Attachement> findPaginatedByCriteria(AttachementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AttachementCriteria criteria);

    List<Attachement> delete(List<Attachement> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Attachement>> getToBeSavedAndToBeDeleted(List<Attachement> oldList, List<Attachement> newList);

    List<Attachement> importData(List<Attachement> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Attachement> importExcel(MultipartFile file);

}
