package ma.zs.stocky.service.impl.etudiant.stage;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.stage.StageEncadrantExterne;
import ma.zs.stocky.dao.criteria.core.stage.StageEncadrantExterneCriteria;
import ma.zs.stocky.dao.facade.core.stage.StageEncadrantExterneDao;
import ma.zs.stocky.dao.specification.core.stage.StageEncadrantExterneSpecification;
import ma.zs.stocky.service.facade.etudiant.stage.StageEncadrantExterneEtudiantService;
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

import ma.zs.stocky.service.facade.etudiant.stage.StageEtudiantService ;
import ma.zs.stocky.bean.core.stage.Stage ;
import ma.zs.stocky.service.facade.etudiant.encadrant.EncadrantExterneEtudiantService ;
import ma.zs.stocky.bean.core.encadrant.EncadrantExterne ;

import java.util.List;
@Service
public class StageEncadrantExterneEtudiantServiceImpl implements StageEncadrantExterneEtudiantService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StageEncadrantExterne update(StageEncadrantExterne t) {
        StageEncadrantExterne loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{StageEncadrantExterne.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public StageEncadrantExterne findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public StageEncadrantExterne findOrSave(StageEncadrantExterne t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            StageEncadrantExterne result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<StageEncadrantExterne> importData(List<StageEncadrantExterne> items) {
        List<StageEncadrantExterne> list = new ArrayList<>();
        for (StageEncadrantExterne t : items) {
            StageEncadrantExterne founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<StageEncadrantExterne> findAll() {
        return dao.findAll();
    }

    public List<StageEncadrantExterne> findByCriteria(StageEncadrantExterneCriteria criteria) {
        List<StageEncadrantExterne> content = null;
        if (criteria != null) {
            StageEncadrantExterneSpecification mySpecification = constructSpecification(criteria);
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


    private StageEncadrantExterneSpecification constructSpecification(StageEncadrantExterneCriteria criteria) {
        StageEncadrantExterneSpecification mySpecification =  (StageEncadrantExterneSpecification) RefelexivityUtil.constructObjectUsingOneParam(StageEncadrantExterneSpecification.class, criteria);
        return mySpecification;
    }

    public List<StageEncadrantExterne> findPaginatedByCriteria(StageEncadrantExterneCriteria criteria, int page, int pageSize, String order, String sortField) {
        StageEncadrantExterneSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(StageEncadrantExterneCriteria criteria) {
        StageEncadrantExterneSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<StageEncadrantExterne> findByStageId(Long id){
        return dao.findByStageId(id);
    }
    public int deleteByStageId(Long id){
        return dao.deleteByStageId(id);
    }
    public long countByStageId(Long id){
        return dao.countByStageId(id);
    }
    public List<StageEncadrantExterne> findByEncadrantExterneId(Long id){
        return dao.findByEncadrantExterneId(id);
    }
    public int deleteByEncadrantExterneId(Long id){
        return dao.deleteByEncadrantExterneId(id);
    }
    public long countByEncadrantExterneCode(String code){
        return dao.countByEncadrantExterneCode(code);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
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
    public int delete(StageEncadrantExterne t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageEncadrantExterne> delete(List<StageEncadrantExterne> list) {
		List<StageEncadrantExterne> result = new ArrayList();
        if (list != null) {
            for (StageEncadrantExterne t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StageEncadrantExterne create(StageEncadrantExterne t) {
        StageEncadrantExterne loaded = findByReferenceEntity(t);
        StageEncadrantExterne saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageEncadrantExterne> create(List<StageEncadrantExterne> ts) {
        List<StageEncadrantExterne> result = new ArrayList<>();
        if (ts != null) {
            for (StageEncadrantExterne t : ts) {
				StageEncadrantExterne created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public StageEncadrantExterne findWithAssociatedLists(Long id){
        StageEncadrantExterne result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageEncadrantExterne> update(List<StageEncadrantExterne> ts, boolean createIfNotExist) {
        List<StageEncadrantExterne> result = new ArrayList<>();
        if (ts != null) {
            for (StageEncadrantExterne t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    StageEncadrantExterne loadedItem = dao.findById(t.getId()).orElse(null);
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





    public StageEncadrantExterne findByReferenceEntity(StageEncadrantExterne t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(StageEncadrantExterne t){
        if( t != null) {
            t.setStage(stageService.findOrSave(t.getStage()));
            t.setEncadrantExterne(encadrantExterneService.findOrSave(t.getEncadrantExterne()));
        }
    }



    public List<StageEncadrantExterne> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<StageEncadrantExterne>> getToBeSavedAndToBeDeleted(List<StageEncadrantExterne> oldList, List<StageEncadrantExterne> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<StageEncadrantExterne> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private StageEtudiantService stageService ;
    @Autowired
    private EncadrantExterneEtudiantService encadrantExterneService ;

    private @Autowired StageEncadrantExterneDao dao;


}
