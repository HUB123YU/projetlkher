package ma.zs.stocky.service.impl.admin.stage;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.stage.StageAttachement;
import ma.zs.stocky.dao.criteria.core.stage.StageAttachementCriteria;
import ma.zs.stocky.dao.facade.core.stage.StageAttachementDao;
import ma.zs.stocky.dao.specification.core.stage.StageAttachementSpecification;
import ma.zs.stocky.service.facade.admin.stage.StageAttachementAdminService;
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

import ma.zs.stocky.service.facade.admin.stage.StageAdminService ;
import ma.zs.stocky.bean.core.stage.Stage ;
import ma.zs.stocky.service.facade.admin.piecejointe.AttachementAdminService ;
import ma.zs.stocky.bean.core.piecejointe.Attachement ;

import java.util.List;
@Service
public class StageAttachementAdminServiceImpl implements StageAttachementAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StageAttachement update(StageAttachement t) {
        StageAttachement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{StageAttachement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public StageAttachement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public StageAttachement findOrSave(StageAttachement t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            StageAttachement result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<StageAttachement> importData(List<StageAttachement> items) {
        List<StageAttachement> list = new ArrayList<>();
        for (StageAttachement t : items) {
            StageAttachement founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<StageAttachement> findAll() {
        return dao.findAll();
    }

    public List<StageAttachement> findByCriteria(StageAttachementCriteria criteria) {
        List<StageAttachement> content = null;
        if (criteria != null) {
            StageAttachementSpecification mySpecification = constructSpecification(criteria);
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


    private StageAttachementSpecification constructSpecification(StageAttachementCriteria criteria) {
        StageAttachementSpecification mySpecification =  (StageAttachementSpecification) RefelexivityUtil.constructObjectUsingOneParam(StageAttachementSpecification.class, criteria);
        return mySpecification;
    }

    public List<StageAttachement> findPaginatedByCriteria(StageAttachementCriteria criteria, int page, int pageSize, String order, String sortField) {
        StageAttachementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(StageAttachementCriteria criteria) {
        StageAttachementSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<StageAttachement> findByStageId(Long id){
        return dao.findByStageId(id);
    }
    public int deleteByStageId(Long id){
        return dao.deleteByStageId(id);
    }
    public long countByStageId(Long id){
        return dao.countByStageId(id);
    }
    public List<StageAttachement> findByAttachementId(Long id){
        return dao.findByAttachementId(id);
    }
    public int deleteByAttachementId(Long id){
        return dao.deleteByAttachementId(id);
    }
    public long countByAttachementId(Long id){
        return dao.countByAttachementId(id);
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
    public int delete(StageAttachement t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageAttachement> delete(List<StageAttachement> list) {
		List<StageAttachement> result = new ArrayList();
        if (list != null) {
            for (StageAttachement t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StageAttachement create(StageAttachement t) {
        StageAttachement loaded = findByReferenceEntity(t);
        StageAttachement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageAttachement> create(List<StageAttachement> ts) {
        List<StageAttachement> result = new ArrayList<>();
        if (ts != null) {
            for (StageAttachement t : ts) {
				StageAttachement created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public StageAttachement findWithAssociatedLists(Long id){
        StageAttachement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageAttachement> update(List<StageAttachement> ts, boolean createIfNotExist) {
        List<StageAttachement> result = new ArrayList<>();
        if (ts != null) {
            for (StageAttachement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    StageAttachement loadedItem = dao.findById(t.getId()).orElse(null);
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





    public StageAttachement findByReferenceEntity(StageAttachement t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(StageAttachement t){
        if( t != null) {
            t.setStage(stageService.findOrSave(t.getStage()));
            t.setAttachement(attachementService.findOrSave(t.getAttachement()));
        }
    }



    public List<StageAttachement> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<StageAttachement>> getToBeSavedAndToBeDeleted(List<StageAttachement> oldList, List<StageAttachement> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<StageAttachement> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private StageAdminService stageService ;
    @Autowired
    private AttachementAdminService attachementService ;

    private @Autowired StageAttachementDao dao;


}
