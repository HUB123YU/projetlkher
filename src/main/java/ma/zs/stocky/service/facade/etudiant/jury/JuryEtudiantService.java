package ma.zs.stocky.service.facade.etudiant.jury;

import java.util.List;
import ma.zs.stocky.bean.core.jury.Jury;
import ma.zs.stocky.dao.criteria.core.jury.JuryCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface JuryEtudiantService {







	Jury create(Jury t);

    Jury update(Jury t);

    List<Jury> update(List<Jury> ts,boolean createIfNotExist);

    Jury findById(Long id);

    Jury findOrSave(Jury t);

    Jury findByReferenceEntity(Jury t);

    Jury findWithAssociatedLists(Long id);

    List<Jury> findAllOptimized();

    List<Jury> findAll();

    List<Jury> findByCriteria(JuryCriteria criteria);

    List<Jury> findPaginatedByCriteria(JuryCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(JuryCriteria criteria);

    List<Jury> delete(List<Jury> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Jury>> getToBeSavedAndToBeDeleted(List<Jury> oldList, List<Jury> newList);

    List<Jury> importData(List<Jury> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Jury> importExcel(MultipartFile file);

}
