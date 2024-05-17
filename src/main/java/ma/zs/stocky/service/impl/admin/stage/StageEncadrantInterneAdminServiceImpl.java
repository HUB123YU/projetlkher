package ma.zs.stocky.service.impl.admin.stage;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.stage.StageEncadrantInterne;
import ma.zs.stocky.dao.criteria.core.stage.StageEncadrantInterneCriteria;
import ma.zs.stocky.dao.facade.core.stage.StageEncadrantInterneDao;
import ma.zs.stocky.dao.specification.core.stage.StageEncadrantInterneSpecification;
import ma.zs.stocky.service.facade.admin.stage.StageEncadrantInterneAdminService;
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

import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService ;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne ;
import ma.zs.stocky.service.facade.admin.stage.StageAdminService ;
import ma.zs.stocky.bean.core.stage.Stage ;

import java.util.List;
@Service
public class StageEncadrantInterneAdminServiceImpl implements StageEncadrantInterneAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StageEncadrantInterne update(StageEncadrantInterne t) {
        StageEncadrantInterne loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{StageEncadrantInterne.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public StageEncadrantInterne findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public StageEncadrantInterne findOrSave(StageEncadrantInterne t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            StageEncadrantInterne result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<StageEncadrantInterne> importData(List<StageEncadrantInterne> items) {
        List<StageEncadrantInterne> list = new ArrayList<>();
        for (StageEncadrantInterne t : items) {
            StageEncadrantInterne founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<StageEncadrantInterne> findAll() {
        return dao.findAll();
    }

    public List<StageEncadrantInterne> findByCriteria(StageEncadrantInterneCriteria criteria) {
        List<StageEncadrantInterne> content = null;
        if (criteria != null) {
            StageEncadrantInterneSpecification mySpecification = constructSpecification(criteria);
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


    private StageEncadrantInterneSpecification constructSpecification(StageEncadrantInterneCriteria criteria) {
        StageEncadrantInterneSpecification mySpecification =  (StageEncadrantInterneSpecification) RefelexivityUtil.constructObjectUsingOneParam(StageEncadrantInterneSpecification.class, criteria);
        return mySpecification;
    }

    public List<StageEncadrantInterne> findPaginatedByCriteria(StageEncadrantInterneCriteria criteria, int page, int pageSize, String order, String sortField) {
        StageEncadrantInterneSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(StageEncadrantInterneCriteria criteria) {
        StageEncadrantInterneSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<StageEncadrantInterne> findByStageId(Long id){
        return dao.findByStageId(id);
    }
    public int deleteByStageId(Long id){
        return dao.deleteByStageId(id);
    }
    public long countByStageId(Long id){
        return dao.countByStageId(id);
    }
    public List<StageEncadrantInterne> findByEncadrantInterneId(Long id){
        return dao.findByEncadrantInterneId(id);
    }
    public int deleteByEncadrantInterneId(Long id){
        return dao.deleteByEncadrantInterneId(id);
    }
    public long countByEncadrantInterneCode(String code){
        return dao.countByEncadrantInterneCode(code);
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
    public int delete(StageEncadrantInterne t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageEncadrantInterne> delete(List<StageEncadrantInterne> list) {
		List<StageEncadrantInterne> result = new ArrayList();
        if (list != null) {
            for (StageEncadrantInterne t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StageEncadrantInterne create(StageEncadrantInterne t) {
        StageEncadrantInterne loaded = findByReferenceEntity(t);
        StageEncadrantInterne saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageEncadrantInterne> create(List<StageEncadrantInterne> ts) {
        List<StageEncadrantInterne> result = new ArrayList<>();
        if (ts != null) {
            for (StageEncadrantInterne t : ts) {
				StageEncadrantInterne created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public StageEncadrantInterne findWithAssociatedLists(Long id){
        StageEncadrantInterne result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageEncadrantInterne> update(List<StageEncadrantInterne> ts, boolean createIfNotExist) {
        List<StageEncadrantInterne> result = new ArrayList<>();
        if (ts != null) {
            for (StageEncadrantInterne t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    StageEncadrantInterne loadedItem = dao.findById(t.getId()).orElse(null);
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





    public StageEncadrantInterne findByReferenceEntity(StageEncadrantInterne t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(StageEncadrantInterne t){
        if( t != null) {
            t.setStage(stageService.findOrSave(t.getStage()));
            t.setEncadrantInterne(encadrantInterneService.findOrSave(t.getEncadrantInterne()));
        }
    }



    public List<StageEncadrantInterne> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<StageEncadrantInterne>> getToBeSavedAndToBeDeleted(List<StageEncadrantInterne> oldList, List<StageEncadrantInterne> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<StageEncadrantInterne> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EncadrantInterneAdminService encadrantInterneService ;
    @Autowired
    private StageAdminService stageService ;

    private @Autowired StageEncadrantInterneDao dao;


}
