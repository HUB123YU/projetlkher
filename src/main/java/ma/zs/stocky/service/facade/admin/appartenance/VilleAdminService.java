package ma.zs.stocky.service.facade.admin.appartenance;

import java.util.List;
import ma.zs.stocky.bean.core.appartenance.Ville;
import ma.zs.stocky.dao.criteria.core.appartenance.VilleCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface VilleAdminService {







	Ville create(Ville t);

    Ville update(Ville t);

    List<Ville> update(List<Ville> ts,boolean createIfNotExist);

    Ville findById(Long id);

    Ville findOrSave(Ville t);

    Ville findByReferenceEntity(Ville t);

    Ville findWithAssociatedLists(Long id);

    List<Ville> findAllOptimized();

    List<Ville> findAll();

    List<Ville> findByCriteria(VilleCriteria criteria);

    List<Ville> findPaginatedByCriteria(VilleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(VilleCriteria criteria);

    List<Ville> delete(List<Ville> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Ville>> getToBeSavedAndToBeDeleted(List<Ville> oldList, List<Ville> newList);

    List<Ville> importData(List<Ville> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Ville> importExcel(MultipartFile file);

}
