package ma.zs.stocky.service.impl.admin.appartenance;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.appartenance.Pays;
import ma.zs.stocky.dao.criteria.core.appartenance.PaysCriteria;
import ma.zs.stocky.dao.facade.core.appartenance.PaysDao;
import ma.zs.stocky.dao.specification.core.appartenance.PaysSpecification;
import ma.zs.stocky.service.facade.admin.appartenance.PaysAdminService;
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
public class PaysAdminServiceImpl implements PaysAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Pays update(Pays t) {
        Pays loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Pays.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Pays findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Pays findOrSave(Pays t) {
        if (t != null) {
            Pays result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Pays> importData(List<Pays> items) {
        List<Pays> list = new ArrayList<>();
        for (Pays t : items) {
            Pays founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Pays> findAll() {
        return dao.findAll();
    }

    public List<Pays> findByCriteria(PaysCriteria criteria) {
        List<Pays> content = null;
        if (criteria != null) {
            PaysSpecification mySpecification = constructSpecification(criteria);
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


    private PaysSpecification constructSpecification(PaysCriteria criteria) {
        PaysSpecification mySpecification =  (PaysSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaysSpecification.class, criteria);
        return mySpecification;
    }

    public List<Pays> findPaginatedByCriteria(PaysCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaysSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaysCriteria criteria) {
        PaysSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Pays t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Pays> delete(List<Pays> list) {
		List<Pays> result = new ArrayList();
        if (list != null) {
            for (Pays t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Pays create(Pays t) {
        Pays loaded = findByReferenceEntity(t);
        Pays saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Pays> create(List<Pays> ts) {
        List<Pays> result = new ArrayList<>();
        if (ts != null) {
            for (Pays t : ts) {
				Pays created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Pays findWithAssociatedLists(Long id){
        Pays result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Pays> update(List<Pays> ts, boolean createIfNotExist) {
        List<Pays> result = new ArrayList<>();
        if (ts != null) {
            for (Pays t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Pays loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Pays findByReferenceEntity(Pays t){
        return t==null? null : dao.findByReference(t.getReference());
    }



    public List<Pays> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Pays>> getToBeSavedAndToBeDeleted(List<Pays> oldList, List<Pays> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Pays> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired PaysDao dao;


}
