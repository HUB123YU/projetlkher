package ma.zs.stocky.service.facade.etudiant.departement;

import java.util.List;
import ma.zs.stocky.bean.core.departement.Filiere;
import ma.zs.stocky.dao.criteria.core.departement.FiliereCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface FiliereEtudiantService {



    List<Filiere> findByDepartementId(Long id);
    int deleteByDepartementId(Long id);
    long countByDepartementCode(String code);




	Filiere create(Filiere t);

    Filiere update(Filiere t);

    List<Filiere> update(List<Filiere> ts,boolean createIfNotExist);

    Filiere findById(Long id);

    Filiere findOrSave(Filiere t);

    Filiere findByReferenceEntity(Filiere t);

    Filiere findWithAssociatedLists(Long id);

    List<Filiere> findAllOptimized();

    List<Filiere> findAll();

    List<Filiere> findByCriteria(FiliereCriteria criteria);

    List<Filiere> findPaginatedByCriteria(FiliereCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(FiliereCriteria criteria);

    List<Filiere> delete(List<Filiere> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Filiere>> getToBeSavedAndToBeDeleted(List<Filiere> oldList, List<Filiere> newList);

    List<Filiere> importData(List<Filiere> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Filiere> importExcel(MultipartFile file);

}
