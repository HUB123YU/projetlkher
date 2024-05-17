package ma.zs.stocky.service.impl.etudiant.appartenance;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.appartenance.Ville;
import ma.zs.stocky.dao.criteria.core.appartenance.VilleCriteria;
import ma.zs.stocky.dao.facade.core.appartenance.VilleDao;
import ma.zs.stocky.dao.specification.core.appartenance.VilleSpecification;
import ma.zs.stocky.service.facade.etudiant.appartenance.VilleEtudiantService;
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
public class VilleEtudiantServiceImpl implements VilleEtudiantService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Ville update(Ville t) {
        Ville loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Ville.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Ville findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Ville findOrSave(Ville t) {
        if (t != null) {
            Ville result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Ville> importData(List<Ville> items) {
        List<Ville> list = new ArrayList<>();
        for (Ville t : items) {
            Ville founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Ville> findAll() {
        return dao.findAll();
    }

    public List<Ville> findByCriteria(VilleCriteria criteria) {
        List<Ville> content = null;
        if (criteria != null) {
            VilleSpecification mySpecification = constructSpecification(criteria);
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


    private VilleSpecification constructSpecification(VilleCriteria criteria) {
        VilleSpecification mySpecification =  (VilleSpecification) RefelexivityUtil.constructObjectUsingOneParam(VilleSpecification.class, criteria);
        return mySpecification;
    }

    public List<Ville> findPaginatedByCriteria(VilleCriteria criteria, int page, int pageSize, String order, String sortField) {
        VilleSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(VilleCriteria criteria) {
        VilleSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Ville t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Ville> delete(List<Ville> list) {
		List<Ville> result = new ArrayList();
        if (list != null) {
            for (Ville t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Ville create(Ville t) {
        Ville loaded = findByReferenceEntity(t);
        Ville saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Ville> create(List<Ville> ts) {
        List<Ville> result = new ArrayList<>();
        if (ts != null) {
            for (Ville t : ts) {
				Ville created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Ville findWithAssociatedLists(Long id){
        Ville result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Ville> update(List<Ville> ts, boolean createIfNotExist) {
        List<Ville> result = new ArrayList<>();
        if (ts != null) {
            for (Ville t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Ville loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Ville findByReferenceEntity(Ville t){
        return t==null? null : dao.findByReference(t.getReference());
    }



    public List<Ville> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Ville>> getToBeSavedAndToBeDeleted(List<Ville> oldList, List<Ville> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Ville> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired VilleDao dao;


}
