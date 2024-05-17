package ma.zs.stocky.service.impl.etudiant.encadrant;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.encadrant.EncadrantExterne;
import ma.zs.stocky.dao.criteria.core.encadrant.EncadrantExterneCriteria;
import ma.zs.stocky.dao.facade.core.encadrant.EncadrantExterneDao;
import ma.zs.stocky.dao.specification.core.encadrant.EncadrantExterneSpecification;
import ma.zs.stocky.service.facade.etudiant.encadrant.EncadrantExterneEtudiantService;
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

import ma.zs.stocky.service.facade.etudiant.societe.SocieteEtudiantService ;
import ma.zs.stocky.bean.core.societe.Societe ;

import java.util.List;
@Service
public class EncadrantExterneEtudiantServiceImpl implements EncadrantExterneEtudiantService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EncadrantExterne update(EncadrantExterne t) {
        EncadrantExterne loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EncadrantExterne.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EncadrantExterne findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EncadrantExterne findOrSave(EncadrantExterne t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            EncadrantExterne result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<EncadrantExterne> importData(List<EncadrantExterne> items) {
        List<EncadrantExterne> list = new ArrayList<>();
        for (EncadrantExterne t : items) {
            EncadrantExterne founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<EncadrantExterne> findAll() {
        return dao.findAll();
    }

    public List<EncadrantExterne> findByCriteria(EncadrantExterneCriteria criteria) {
        List<EncadrantExterne> content = null;
        if (criteria != null) {
            EncadrantExterneSpecification mySpecification = constructSpecification(criteria);
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


    private EncadrantExterneSpecification constructSpecification(EncadrantExterneCriteria criteria) {
        EncadrantExterneSpecification mySpecification =  (EncadrantExterneSpecification) RefelexivityUtil.constructObjectUsingOneParam(EncadrantExterneSpecification.class, criteria);
        return mySpecification;
    }

    public List<EncadrantExterne> findPaginatedByCriteria(EncadrantExterneCriteria criteria, int page, int pageSize, String order, String sortField) {
        EncadrantExterneSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EncadrantExterneCriteria criteria) {
        EncadrantExterneSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<EncadrantExterne> findBySocieteId(Long id){
        return dao.findBySocieteId(id);
    }
    public int deleteBySocieteId(Long id){
        return dao.deleteBySocieteId(id);
    }
    public long countBySocieteIce(String ice){
        return dao.countBySocieteIce(ice);
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
    public int delete(EncadrantExterne t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EncadrantExterne> delete(List<EncadrantExterne> list) {
		List<EncadrantExterne> result = new ArrayList();
        if (list != null) {
            for (EncadrantExterne t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EncadrantExterne create(EncadrantExterne t) {
        EncadrantExterne loaded = findByReferenceEntity(t);
        EncadrantExterne saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EncadrantExterne> create(List<EncadrantExterne> ts) {
        List<EncadrantExterne> result = new ArrayList<>();
        if (ts != null) {
            for (EncadrantExterne t : ts) {
				EncadrantExterne created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public EncadrantExterne findWithAssociatedLists(Long id){
        EncadrantExterne result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EncadrantExterne> update(List<EncadrantExterne> ts, boolean createIfNotExist) {
        List<EncadrantExterne> result = new ArrayList<>();
        if (ts != null) {
            for (EncadrantExterne t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EncadrantExterne loadedItem = dao.findById(t.getId()).orElse(null);
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





    public EncadrantExterne findByReferenceEntity(EncadrantExterne t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(EncadrantExterne t){
        if( t != null) {
            t.setSociete(societeService.findOrSave(t.getSociete()));
        }
    }



    public List<EncadrantExterne> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EncadrantExterne>> getToBeSavedAndToBeDeleted(List<EncadrantExterne> oldList, List<EncadrantExterne> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<EncadrantExterne> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private SocieteEtudiantService societeService ;

    private @Autowired EncadrantExterneDao dao;


}
