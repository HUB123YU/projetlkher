package ma.zs.stocky.service.impl.admin.departement;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.departement.Departement;
import ma.zs.stocky.dao.criteria.core.departement.DepartementCriteria;
import ma.zs.stocky.dao.facade.core.departement.DepartementDao;
import ma.zs.stocky.dao.specification.core.departement.DepartementSpecification;
import ma.zs.stocky.service.facade.admin.departement.DepartementAdminService;
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
public class DepartementAdminServiceImpl implements DepartementAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Departement update(Departement t) {
        Departement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Departement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Departement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Departement findOrSave(Departement t) {
        if (t != null) {
            Departement result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Departement> importData(List<Departement> items) {
        List<Departement> list = new ArrayList<>();
        for (Departement t : items) {
            Departement founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Departement> findAll() {
        return dao.findAll();
    }

    public List<Departement> findByCriteria(DepartementCriteria criteria) {
        List<Departement> content = null;
        if (criteria != null) {
            DepartementSpecification mySpecification = constructSpecification(criteria);
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


    private DepartementSpecification constructSpecification(DepartementCriteria criteria) {
        DepartementSpecification mySpecification =  (DepartementSpecification) RefelexivityUtil.constructObjectUsingOneParam(DepartementSpecification.class, criteria);
        return mySpecification;
    }

    public List<Departement> findPaginatedByCriteria(DepartementCriteria criteria, int page, int pageSize, String order, String sortField) {
        DepartementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DepartementCriteria criteria) {
        DepartementSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Departement t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Departement> delete(List<Departement> list) {
		List<Departement> result = new ArrayList();
        if (list != null) {
            for (Departement t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Departement create(Departement t) {
        Departement loaded = findByReferenceEntity(t);
        Departement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Departement> create(List<Departement> ts) {
        List<Departement> result = new ArrayList<>();
        if (ts != null) {
            for (Departement t : ts) {
				Departement created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Departement findWithAssociatedLists(Long id){
        Departement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Departement> update(List<Departement> ts, boolean createIfNotExist) {
        List<Departement> result = new ArrayList<>();
        if (ts != null) {
            for (Departement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Departement loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Departement findByReferenceEntity(Departement t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Departement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Departement>> getToBeSavedAndToBeDeleted(List<Departement> oldList, List<Departement> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Departement> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired DepartementDao dao;


}
