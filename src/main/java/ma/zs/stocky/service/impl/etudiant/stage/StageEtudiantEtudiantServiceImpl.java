package ma.zs.stocky.service.impl.etudiant.stage;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.stage.StageEtudiant;
import ma.zs.stocky.dao.criteria.core.stage.StageEtudiantCriteria;
import ma.zs.stocky.dao.facade.core.stage.StageEtudiantDao;
import ma.zs.stocky.dao.specification.core.stage.StageEtudiantSpecification;
import ma.zs.stocky.service.facade.etudiant.stage.StageEtudiantEtudiantService;
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

import ma.zs.stocky.service.facade.etudiant.etudiant.EtudiantEtudiantService ;
import ma.zs.stocky.bean.core.etudiant.Etudiant ;
import ma.zs.stocky.service.facade.etudiant.stage.StageEtudiantService ;
import ma.zs.stocky.bean.core.stage.Stage ;

import java.util.List;
@Service
public class StageEtudiantEtudiantServiceImpl implements StageEtudiantEtudiantService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StageEtudiant update(StageEtudiant t) {
        StageEtudiant loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{StageEtudiant.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public StageEtudiant findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public StageEtudiant findOrSave(StageEtudiant t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            StageEtudiant result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<StageEtudiant> importData(List<StageEtudiant> items) {
        List<StageEtudiant> list = new ArrayList<>();
        for (StageEtudiant t : items) {
            StageEtudiant founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<StageEtudiant> findAll() {
        return dao.findAll();
    }

    public List<StageEtudiant> findByCriteria(StageEtudiantCriteria criteria) {
        List<StageEtudiant> content = null;
        if (criteria != null) {
            StageEtudiantSpecification mySpecification = constructSpecification(criteria);
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


    private StageEtudiantSpecification constructSpecification(StageEtudiantCriteria criteria) {
        StageEtudiantSpecification mySpecification =  (StageEtudiantSpecification) RefelexivityUtil.constructObjectUsingOneParam(StageEtudiantSpecification.class, criteria);
        return mySpecification;
    }

    public List<StageEtudiant> findPaginatedByCriteria(StageEtudiantCriteria criteria, int page, int pageSize, String order, String sortField) {
        StageEtudiantSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(StageEtudiantCriteria criteria) {
        StageEtudiantSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<StageEtudiant> findByStageId(Long id){
        return dao.findByStageId(id);
    }
    public int deleteByStageId(Long id){
        return dao.deleteByStageId(id);
    }
    public long countByStageId(Long id){
        return dao.countByStageId(id);
    }
    public List<StageEtudiant> findByEtudiantId(Long id){
        return dao.findByEtudiantId(id);
    }
    public int deleteByEtudiantId(Long id){
        return dao.deleteByEtudiantId(id);
    }
    public long countByEtudiantId(Long id){
        return dao.countByEtudiantId(id);
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
    public int delete(StageEtudiant t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageEtudiant> delete(List<StageEtudiant> list) {
		List<StageEtudiant> result = new ArrayList();
        if (list != null) {
            for (StageEtudiant t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StageEtudiant create(StageEtudiant t) {
        StageEtudiant loaded = findByReferenceEntity(t);
        StageEtudiant saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageEtudiant> create(List<StageEtudiant> ts) {
        List<StageEtudiant> result = new ArrayList<>();
        if (ts != null) {
            for (StageEtudiant t : ts) {
				StageEtudiant created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public StageEtudiant findWithAssociatedLists(Long id){
        StageEtudiant result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StageEtudiant> update(List<StageEtudiant> ts, boolean createIfNotExist) {
        List<StageEtudiant> result = new ArrayList<>();
        if (ts != null) {
            for (StageEtudiant t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    StageEtudiant loadedItem = dao.findById(t.getId()).orElse(null);
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





    public StageEtudiant findByReferenceEntity(StageEtudiant t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(StageEtudiant t){
        if( t != null) {
            t.setStage(stageService.findOrSave(t.getStage()));
            t.setEtudiant(etudiantService.findOrSave(t.getEtudiant()));
        }
    }



    public List<StageEtudiant> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<StageEtudiant>> getToBeSavedAndToBeDeleted(List<StageEtudiant> oldList, List<StageEtudiant> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<StageEtudiant> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EtudiantEtudiantService etudiantService ;
    @Autowired
    private StageEtudiantService stageService ;

    private @Autowired StageEtudiantDao dao;


}
