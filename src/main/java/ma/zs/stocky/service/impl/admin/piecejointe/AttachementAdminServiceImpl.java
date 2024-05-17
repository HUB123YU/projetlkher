package ma.zs.stocky.service.impl.admin.piecejointe;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.piecejointe.Attachement;
import ma.zs.stocky.dao.criteria.core.piecejointe.AttachementCriteria;
import ma.zs.stocky.dao.facade.core.piecejointe.AttachementDao;
import ma.zs.stocky.dao.specification.core.piecejointe.AttachementSpecification;
import ma.zs.stocky.service.facade.admin.piecejointe.AttachementAdminService;
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
import ma.zs.stocky.service.facade.admin.piecejointe.TypeAttachementAdminService ;
import ma.zs.stocky.bean.core.piecejointe.TypeAttachement ;

import java.util.List;
@Service
public class AttachementAdminServiceImpl implements AttachementAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Attachement update(Attachement t) {
        Attachement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Attachement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Attachement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Attachement findOrSave(Attachement t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Attachement result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Attachement> importData(List<Attachement> items) {
        List<Attachement> list = new ArrayList<>();
        for (Attachement t : items) {
            Attachement founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Attachement> findAll() {
        return dao.findAll();
    }

    public List<Attachement> findByCriteria(AttachementCriteria criteria) {
        List<Attachement> content = null;
        if (criteria != null) {
            AttachementSpecification mySpecification = constructSpecification(criteria);
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


    private AttachementSpecification constructSpecification(AttachementCriteria criteria) {
        AttachementSpecification mySpecification =  (AttachementSpecification) RefelexivityUtil.constructObjectUsingOneParam(AttachementSpecification.class, criteria);
        return mySpecification;
    }

    public List<Attachement> findPaginatedByCriteria(AttachementCriteria criteria, int page, int pageSize, String order, String sortField) {
        AttachementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AttachementCriteria criteria) {
        AttachementSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Attachement> findByTypeAttachementId(Long id){
        return dao.findByTypeAttachementId(id);
    }
    public int deleteByTypeAttachementId(Long id){
        return dao.deleteByTypeAttachementId(id);
    }
    public long countByTypeAttachementReference(String reference){
        return dao.countByTypeAttachementReference(reference);
    }
    public List<Attachement> findByStageId(Long id){
        return dao.findByStageId(id);
    }
    public int deleteByStageId(Long id){
        return dao.deleteByStageId(id);
    }
    public long countByStageId(Long id){
        return dao.countByStageId(id);
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
    public int delete(Attachement t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Attachement> delete(List<Attachement> list) {
		List<Attachement> result = new ArrayList();
        if (list != null) {
            for (Attachement t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Attachement create(Attachement t) {
        Attachement loaded = findByReferenceEntity(t);
        Attachement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Attachement> create(List<Attachement> ts) {
        List<Attachement> result = new ArrayList<>();
        if (ts != null) {
            for (Attachement t : ts) {
				Attachement created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Attachement findWithAssociatedLists(Long id){
        Attachement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Attachement> update(List<Attachement> ts, boolean createIfNotExist) {
        List<Attachement> result = new ArrayList<>();
        if (ts != null) {
            for (Attachement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Attachement loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Attachement findByReferenceEntity(Attachement t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Attachement t){
        if( t != null) {
            t.setTypeAttachement(typeAttachementService.findOrSave(t.getTypeAttachement()));
            t.setStage(stageService.findOrSave(t.getStage()));
        }
    }



    public List<Attachement> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Attachement>> getToBeSavedAndToBeDeleted(List<Attachement> oldList, List<Attachement> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Attachement> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private StageAdminService stageService ;
    @Autowired
    private TypeAttachementAdminService typeAttachementService ;

    private @Autowired AttachementDao dao;


}
