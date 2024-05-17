package ma.zs.stocky.service.facade.admin.departement;

import java.util.List;
import ma.zs.stocky.bean.core.departement.Departement;
import ma.zs.stocky.dao.criteria.core.departement.DepartementCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DepartementAdminService {







	Departement create(Departement t);

    Departement update(Departement t);

    List<Departement> update(List<Departement> ts,boolean createIfNotExist);

    Departement findById(Long id);

    Departement findOrSave(Departement t);

    Departement findByReferenceEntity(Departement t);

    Departement findWithAssociatedLists(Long id);

    List<Departement> findAllOptimized();

    List<Departement> findAll();

    List<Departement> findByCriteria(DepartementCriteria criteria);

    List<Departement> findPaginatedByCriteria(DepartementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DepartementCriteria criteria);

    List<Departement> delete(List<Departement> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Departement>> getToBeSavedAndToBeDeleted(List<Departement> oldList, List<Departement> newList);

    List<Departement> importData(List<Departement> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Departement> importExcel(MultipartFile file);

}
