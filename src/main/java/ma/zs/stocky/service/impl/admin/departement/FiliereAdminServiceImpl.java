package ma.zs.stocky.service.impl.admin.departement;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.departement.Filiere;
import ma.zs.stocky.dao.criteria.core.departement.FiliereCriteria;
import ma.zs.stocky.dao.facade.core.departement.FiliereDao;
import ma.zs.stocky.dao.specification.core.departement.FiliereSpecification;
import ma.zs.stocky.service.facade.admin.departement.FiliereAdminService;
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

import ma.zs.stocky.service.facade.admin.departement.DepartementAdminService ;
import ma.zs.stocky.bean.core.departement.Departement ;

import java.util.List;
@Service
public class FiliereAdminServiceImpl implements FiliereAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Filiere update(Filiere t) {
        Filiere loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Filiere.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Filiere findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Filiere findOrSave(Filiere t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Filiere result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Filiere> importData(List<Filiere> items) {
        List<Filiere> list = new ArrayList<>();
        for (Filiere t : items) {
            Filiere founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Filiere> findAll() {
        return dao.findAll();
    }

    public List<Filiere> findByCriteria(FiliereCriteria criteria) {
        List<Filiere> content = null;
        if (criteria != null) {
            FiliereSpecification mySpecification = constructSpecification(criteria);
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


    private FiliereSpecification constructSpecification(FiliereCriteria criteria) {
        FiliereSpecification mySpecification =  (FiliereSpecification) RefelexivityUtil.constructObjectUsingOneParam(FiliereSpecification.class, criteria);
        return mySpecification;
    }

    public List<Filiere> findPaginatedByCriteria(FiliereCriteria criteria, int page, int pageSize, String order, String sortField) {
        FiliereSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(FiliereCriteria criteria) {
        FiliereSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Filiere> findByDepartementId(Long id){
        return dao.findByDepartementId(id);
    }
    public int deleteByDepartementId(Long id){
        return dao.deleteByDepartementId(id);
    }
    public long countByDepartementCode(String code){
        return dao.countByDepartementCode(code);
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
    public int delete(Filiere t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Filiere> delete(List<Filiere> list) {
		List<Filiere> result = new ArrayList();
        if (list != null) {
            for (Filiere t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Filiere create(Filiere t) {
        Filiere loaded = findByReferenceEntity(t);
        Filiere saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Filiere> create(List<Filiere> ts) {
        List<Filiere> result = new ArrayList<>();
        if (ts != null) {
            for (Filiere t : ts) {
				Filiere created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Filiere findWithAssociatedLists(Long id){
        Filiere result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Filiere> update(List<Filiere> ts, boolean createIfNotExist) {
        List<Filiere> result = new ArrayList<>();
        if (ts != null) {
            for (Filiere t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Filiere loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Filiere findByReferenceEntity(Filiere t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Filiere t){
        if( t != null) {
            t.setDepartement(departementService.findOrSave(t.getDepartement()));
        }
    }



    public List<Filiere> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Filiere>> getToBeSavedAndToBeDeleted(List<Filiere> oldList, List<Filiere> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Filiere> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private DepartementAdminService departementService ;

    private @Autowired FiliereDao dao;


}
