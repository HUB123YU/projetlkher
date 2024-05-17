package ma.zs.stocky.service.impl.etudiant.departement;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.departement.Domaine;
import ma.zs.stocky.dao.criteria.core.departement.DomaineCriteria;
import ma.zs.stocky.dao.facade.core.departement.DomaineDao;
import ma.zs.stocky.dao.specification.core.departement.DomaineSpecification;
import ma.zs.stocky.service.facade.etudiant.departement.DomaineEtudiantService;
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
public class DomaineEtudiantServiceImpl implements DomaineEtudiantService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Domaine update(Domaine t) {
        Domaine loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Domaine.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Domaine findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Domaine findOrSave(Domaine t) {
        if (t != null) {
            Domaine result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Domaine> importData(List<Domaine> items) {
        List<Domaine> list = new ArrayList<>();
        for (Domaine t : items) {
            Domaine founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Domaine> findAll() {
        return dao.findAll();
    }

    public List<Domaine> findByCriteria(DomaineCriteria criteria) {
        List<Domaine> content = null;
        if (criteria != null) {
            DomaineSpecification mySpecification = constructSpecification(criteria);
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


    private DomaineSpecification constructSpecification(DomaineCriteria criteria) {
        DomaineSpecification mySpecification =  (DomaineSpecification) RefelexivityUtil.constructObjectUsingOneParam(DomaineSpecification.class, criteria);
        return mySpecification;
    }

    public List<Domaine> findPaginatedByCriteria(DomaineCriteria criteria, int page, int pageSize, String order, String sortField) {
        DomaineSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DomaineCriteria criteria) {
        DomaineSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Domaine t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Domaine> delete(List<Domaine> list) {
		List<Domaine> result = new ArrayList();
        if (list != null) {
            for (Domaine t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Domaine create(Domaine t) {
        Domaine loaded = findByReferenceEntity(t);
        Domaine saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Domaine> create(List<Domaine> ts) {
        List<Domaine> result = new ArrayList<>();
        if (ts != null) {
            for (Domaine t : ts) {
				Domaine created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Domaine findWithAssociatedLists(Long id){
        Domaine result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Domaine> update(List<Domaine> ts, boolean createIfNotExist) {
        List<Domaine> result = new ArrayList<>();
        if (ts != null) {
            for (Domaine t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Domaine loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Domaine findByReferenceEntity(Domaine t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Domaine> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Domaine>> getToBeSavedAndToBeDeleted(List<Domaine> oldList, List<Domaine> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Domaine> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired DomaineDao dao;


}
