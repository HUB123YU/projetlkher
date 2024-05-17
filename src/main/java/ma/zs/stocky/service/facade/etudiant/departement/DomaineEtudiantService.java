package ma.zs.stocky.service.facade.etudiant.departement;

import java.util.List;
import ma.zs.stocky.bean.core.departement.Domaine;
import ma.zs.stocky.dao.criteria.core.departement.DomaineCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DomaineEtudiantService {







	Domaine create(Domaine t);

    Domaine update(Domaine t);

    List<Domaine> update(List<Domaine> ts,boolean createIfNotExist);

    Domaine findById(Long id);

    Domaine findOrSave(Domaine t);

    Domaine findByReferenceEntity(Domaine t);

    Domaine findWithAssociatedLists(Long id);

    List<Domaine> findAllOptimized();

    List<Domaine> findAll();

    List<Domaine> findByCriteria(DomaineCriteria criteria);

    List<Domaine> findPaginatedByCriteria(DomaineCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DomaineCriteria criteria);

    List<Domaine> delete(List<Domaine> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Domaine>> getToBeSavedAndToBeDeleted(List<Domaine> oldList, List<Domaine> newList);

    List<Domaine> importData(List<Domaine> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Domaine> importExcel(MultipartFile file);

}
