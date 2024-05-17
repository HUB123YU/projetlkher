package ma.zs.stocky.service.facade.etudiant.piecejointe;

import java.util.List;
import ma.zs.stocky.bean.core.piecejointe.TypeAttachement;
import ma.zs.stocky.dao.criteria.core.piecejointe.TypeAttachementCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TypeAttachementEtudiantService {







	TypeAttachement create(TypeAttachement t);

    TypeAttachement update(TypeAttachement t);

    List<TypeAttachement> update(List<TypeAttachement> ts,boolean createIfNotExist);

    TypeAttachement findById(Long id);

    TypeAttachement findOrSave(TypeAttachement t);

    TypeAttachement findByReferenceEntity(TypeAttachement t);

    TypeAttachement findWithAssociatedLists(Long id);

    List<TypeAttachement> findAllOptimized();

    List<TypeAttachement> findAll();

    List<TypeAttachement> findByCriteria(TypeAttachementCriteria criteria);

    List<TypeAttachement> findPaginatedByCriteria(TypeAttachementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeAttachementCriteria criteria);

    List<TypeAttachement> delete(List<TypeAttachement> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TypeAttachement>> getToBeSavedAndToBeDeleted(List<TypeAttachement> oldList, List<TypeAttachement> newList);

    List<TypeAttachement> importData(List<TypeAttachement> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TypeAttachement> importExcel(MultipartFile file);

}
