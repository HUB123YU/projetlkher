package ma.zs.stocky.service.facade.admin.etudiant;

import java.util.List;
import ma.zs.stocky.bean.core.etudiant.Etudiant;
import ma.zs.stocky.dao.criteria.core.etudiant.EtudiantCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EtudiantAdminService {


    Etudiant findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Etudiant> findByGenreId(Long id);
    int deleteByGenreId(Long id);
    long countByGenreCode(String code);
    List<Etudiant> findByNationaliteId(Long id);
    int deleteByNationaliteId(Long id);
    long countByNationaliteCode(String code);
    List<Etudiant> findByFiliereId(Long id);
    int deleteByFiliereId(Long id);
    long countByFiliereCode(String code);




	Etudiant create(Etudiant t);

    Etudiant update(Etudiant t);

    List<Etudiant> update(List<Etudiant> ts,boolean createIfNotExist);

    Etudiant findById(Long id);

    Etudiant findOrSave(Etudiant t);

    Etudiant findByReferenceEntity(Etudiant t);

    Etudiant findWithAssociatedLists(Long id);

    List<Etudiant> findAllOptimized();

    List<Etudiant> findAll();

    List<Etudiant> findByCriteria(EtudiantCriteria criteria);

    List<Etudiant> findPaginatedByCriteria(EtudiantCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EtudiantCriteria criteria);

    List<Etudiant> delete(List<Etudiant> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Etudiant>> getToBeSavedAndToBeDeleted(List<Etudiant> oldList, List<Etudiant> newList);

    List<Etudiant> importData(List<Etudiant> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Etudiant> importExcel(MultipartFile file);

}
