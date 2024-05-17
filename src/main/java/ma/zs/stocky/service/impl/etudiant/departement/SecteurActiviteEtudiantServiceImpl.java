package ma.zs.stocky.service.impl.etudiant.departement;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.departement.SecteurActivite;
import ma.zs.stocky.dao.criteria.core.departement.SecteurActiviteCriteria;
import ma.zs.stocky.dao.facade.core.departement.SecteurActiviteDao;
import ma.zs.stocky.dao.specification.core.departement.SecteurActiviteSpecification;
import ma.zs.stocky.service.facade.etudiant.departement.SecteurActiviteEtudiantService;
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


import java.util.List;
@Service
public class SecteurActiviteEtudiantServiceImpl implements SecteurActiviteEtudiantService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public SecteurActivite update(SecteurActivite t) {
        SecteurActivite loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{SecteurActivite.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public SecteurActivite findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public SecteurActivite findOrSave(SecteurActivite t) {
        if (t != null) {
            SecteurActivite result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<SecteurActivite> importData(List<SecteurActivite> items) {
        List<SecteurActivite> list = new ArrayList<>();
        for (SecteurActivite t : items) {
            SecteurActivite founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<SecteurActivite> findAll() {
        return dao.findAll();
    }

    public List<SecteurActivite> findByCriteria(SecteurActiviteCriteria criteria) {
        List<SecteurActivite> content = null;
        if (criteria != null) {
            SecteurActiviteSpecification mySpecification = constructSpecification(criteria);
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


    private SecteurActiviteSpecification constructSpecification(SecteurActiviteCriteria criteria) {
        SecteurActiviteSpecification mySpecification =  (SecteurActiviteSpecification) RefelexivityUtil.constructObjectUsingOneParam(SecteurActiviteSpecification.class, criteria);
        return mySpecification;
    }

    public List<SecteurActivite> findPaginatedByCriteria(SecteurActiviteCriteria criteria, int page, int pageSize, String order, String sortField) {
        SecteurActiviteSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SecteurActiviteCriteria criteria) {
        SecteurActiviteSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public int delete(SecteurActivite t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SecteurActivite> delete(List<SecteurActivite> list) {
		List<SecteurActivite> result = new ArrayList();
        if (list != null) {
            for (SecteurActivite t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public SecteurActivite create(SecteurActivite t) {
        SecteurActivite loaded = findByReferenceEntity(t);
        SecteurActivite saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SecteurActivite> create(List<SecteurActivite> ts) {
        List<SecteurActivite> result = new ArrayList<>();
        if (ts != null) {
            for (SecteurActivite t : ts) {
				SecteurActivite created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public SecteurActivite findWithAssociatedLists(Long id){
        SecteurActivite result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SecteurActivite> update(List<SecteurActivite> ts, boolean createIfNotExist) {
        List<SecteurActivite> result = new ArrayList<>();
        if (ts != null) {
            for (SecteurActivite t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    SecteurActivite loadedItem = dao.findById(t.getId()).orElse(null);
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





    public SecteurActivite findByReferenceEntity(SecteurActivite t){
        return t==null? null : dao.findByReference(t.getReference());
    }



    public List<SecteurActivite> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<SecteurActivite>> getToBeSavedAndToBeDeleted(List<SecteurActivite> oldList, List<SecteurActivite> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<SecteurActivite> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired SecteurActiviteDao dao;


}
