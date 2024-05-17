package ma.zs.stocky.service.impl.admin.stage;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.stage.Stage;
import ma.zs.stocky.dao.criteria.core.stage.StageCriteria;
import ma.zs.stocky.dao.facade.core.stage.StageDao;
import ma.zs.stocky.dao.specification.core.stage.StageSpecification;
import ma.zs.stocky.service.facade.admin.stage.StageAdminService;
import ma.zs.stocky.zynerator.service.AbstractServiceImpl;
import ma.zs.stocky.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import ma.zs.stocky.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.stocky.service.facade.admin.societe.SocieteAdminService ;
import ma.zs.stocky.bean.core.societe.Societe ;
import ma.zs.stocky.service.facade.admin.stage.StageAttachementAdminService ;
import ma.zs.stocky.bean.core.stage.StageAttachement ;
import ma.zs.stocky.service.facade.admin.stage.StageEtudiantAdminService ;
import ma.zs.stocky.bean.core.stage.StageEtudiant ;
import ma.zs.stocky.service.facade.admin.jury.JuryAdminService ;
import ma.zs.stocky.bean.core.jury.Jury ;
import ma.zs.stocky.service.facade.admin.departement.FiliereAdminService ;
import ma.zs.stocky.bean.core.departement.Filiere ;
import ma.zs.stocky.service.facade.admin.stage.StageEncadrantExterneAdminService ;
import ma.zs.stocky.bean.core.stage.StageEncadrantExterne ;
import ma.zs.stocky.service.facade.admin.stage.StageEncadrantInterneAdminService ;
import ma.zs.stocky.bean.core.stage.StageEncadrantInterne ;
import ma.zs.stocky.service.facade.admin.stage.TypeStageAdminService ;
import ma.zs.stocky.bean.core.stage.TypeStage ;
import ma.zs.stocky.service.facade.admin.departement.DomaineAdminService ;
import ma.zs.stocky.bean.core.departement.Domaine ;

import java.util.List;
@Service
public class StageAdminServiceImpl implements StageAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Stage update(Stage t) {
        Stage loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Stage.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Stage findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Stage findOrSave(Stage t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Stage result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Stage> importData(List<Stage> items) {
        List<Stage> list = new ArrayList<>();
        for (Stage t : items) {
            Stage founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Stage> findAll() {
        return dao.findAll();
    }

    public List<Stage> findByCriteria(StageCriteria criteria) {
        List<Stage> content = null;
        if (criteria != null) {
            StageSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private StageSpecification constructSpecification(StageCriteria criteria) {
        StageSpecification mySpecification =  (StageSpecification) RefelexivityUtil.constructObjectUsingOneParam(StageSpecification.class, criteria);
        return mySpecification;
    }

    public List<Stage> findPaginatedByCriteria(StageCriteria criteria, int page, int pageSize, String order, String sortField) {
        StageSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(StageCriteria criteria) {
        StageSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Stage> findByDomaineId(Long id){
        return dao.findByDomaineId(id);
    }
    public int deleteByDomaineId(Long id){
        return dao.deleteByDomaineId(id);
    }
    public long countByDomaineCode(String code){
        return dao.countByDomaineCode(code);
    }
    public List<Stage> findBySocieteId(Long id){
        return dao.findBySocieteId(id);
    }
    public int deleteBySocieteId(Long id){
        return dao.deleteBySocieteId(id);
    }
    public long countBySocieteIce(String ice){
        return dao.countBySocieteIce(ice);
    }
    public List<Stage> findByJuryId(Long id){
        return dao.findByJuryId(id);
    }
    public int deleteByJuryId(Long id){
        return dao.deleteByJuryId(id);
    }
    public long countByJuryRef(String ref){
        return dao.countByJuryRef(ref);
    }
    public List<Stage> findByFiliereId(Long id){
        return dao.findByFiliereId(id);
    }
    public int deleteByFiliereId(Long id){
        return dao.deleteByFiliereId(id);
    }
    public long countByFiliereCode(String code){
        return dao.countByFiliereCode(code);
    }
    public List<Stage> findByTypeStageId(Long id){
        return dao.findByTypeStageId(id);
    }
    public int deleteByTypeStageId(Long id){
        return dao.deleteByTypeStageId(id);
    }
    public long countByTypeStageReference(String reference){
        return dao.countByTypeStageReference(reference);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int delete(Stage t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        stageEtudiantService.deleteByStageId(id);
        stageEncadrantInterneService.deleteByStageId(id);
        stageEncadrantExterneService.deleteByStageId(id);
        stageAttachementService.deleteByStageId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Stage> delete(List<Stage> list) {
		List<Stage> result = new ArrayList();
        if (list != null) {
            for (Stage t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Stage create(Stage t) {
        Stage loaded = findByReferenceEntity(t);
        Stage saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getStageEtudiants() != null) {
                t.getStageEtudiants().forEach(element-> {
                    element.setStage(saved);
                    stageEtudiantService.create(element);
                });
            }
            if (t.getStageEncadrantInternes() != null) {
                t.getStageEncadrantInternes().forEach(element-> {
                    element.setStage(saved);
                    stageEncadrantInterneService.create(element);
                });
            }
            if (t.getStageEncadrantExternes() != null) {
                t.getStageEncadrantExternes().forEach(element-> {
                    element.setStage(saved);
                    stageEncadrantExterneService.create(element);
                });
            }
            if (t.getStageAttachements() != null) {
                t.getStageAttachements().forEach(element-> {
                    element.setStage(saved);
                    stageAttachementService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Stage> create(List<Stage> ts) {
        List<Stage> result = new ArrayList<>();
        if (ts != null) {
            for (Stage t : ts) {
				Stage created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Stage findWithAssociatedLists(Long id){
        Stage result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setStageEtudiants(stageEtudiantService.findByStageId(id));
            result.setStageEncadrantInternes(stageEncadrantInterneService.findByStageId(id));
            result.setStageEncadrantExternes(stageEncadrantExterneService.findByStageId(id));
            result.setStageAttachements(stageAttachementService.findByStageId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Stage> update(List<Stage> ts, boolean createIfNotExist) {
        List<Stage> result = new ArrayList<>();
        if (ts != null) {
            for (Stage t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Stage loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }

    public void updateWithAssociatedLists(Stage stage){
    if(stage !=null && stage.getId() != null){
        List<List<StageEtudiant>> resultStageEtudiants= stageEtudiantService.getToBeSavedAndToBeDeleted(stageEtudiantService.findByStageId(stage.getId()),stage.getStageEtudiants());
            stageEtudiantService.delete(resultStageEtudiants.get(1));
        ListUtil.emptyIfNull(resultStageEtudiants.get(0)).forEach(e -> e.setStage(stage));
        stageEtudiantService.update(resultStageEtudiants.get(0),true);
        List<List<StageEncadrantInterne>> resultStageEncadrantInternes= stageEncadrantInterneService.getToBeSavedAndToBeDeleted(stageEncadrantInterneService.findByStageId(stage.getId()),stage.getStageEncadrantInternes());
            stageEncadrantInterneService.delete(resultStageEncadrantInternes.get(1));
        ListUtil.emptyIfNull(resultStageEncadrantInternes.get(0)).forEach(e -> e.setStage(stage));
        stageEncadrantInterneService.update(resultStageEncadrantInternes.get(0),true);
        List<List<StageEncadrantExterne>> resultStageEncadrantExternes= stageEncadrantExterneService.getToBeSavedAndToBeDeleted(stageEncadrantExterneService.findByStageId(stage.getId()),stage.getStageEncadrantExternes());
            stageEncadrantExterneService.delete(resultStageEncadrantExternes.get(1));
        ListUtil.emptyIfNull(resultStageEncadrantExternes.get(0)).forEach(e -> e.setStage(stage));
        stageEncadrantExterneService.update(resultStageEncadrantExternes.get(0),true);
        List<List<StageAttachement>> resultStageAttachements= stageAttachementService.getToBeSavedAndToBeDeleted(stageAttachementService.findByStageId(stage.getId()),stage.getStageAttachements());
            stageAttachementService.delete(resultStageAttachements.get(1));
        ListUtil.emptyIfNull(resultStageAttachements.get(0)).forEach(e -> e.setStage(stage));
        stageAttachementService.update(resultStageAttachements.get(0),true);
        }
    }




    public Stage findByReferenceEntity(Stage t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Stage t){
        if( t != null) {
            t.setDomaine(domaineService.findOrSave(t.getDomaine()));
            t.setSociete(societeService.findOrSave(t.getSociete()));
            t.setJury(juryService.findOrSave(t.getJury()));
            t.setFiliere(filiereService.findOrSave(t.getFiliere()));
            t.setTypeStage(typeStageService.findOrSave(t.getTypeStage()));
        }
    }



    public List<Stage> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Stage>> getToBeSavedAndToBeDeleted(List<Stage> oldList, List<Stage> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Stage> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private SocieteAdminService societeService ;
    @Autowired
    private StageAttachementAdminService stageAttachementService ;
    @Autowired
    private StageEtudiantAdminService stageEtudiantService ;
    @Autowired
    private JuryAdminService juryService ;
    @Autowired
    private FiliereAdminService filiereService ;
    @Autowired
    private StageEncadrantExterneAdminService stageEncadrantExterneService ;
    @Autowired
    private StageEncadrantInterneAdminService stageEncadrantInterneService ;
    @Autowired
    private TypeStageAdminService typeStageService ;
    @Autowired
    private DomaineAdminService domaineService ;

    private @Autowired StageDao dao;


}
