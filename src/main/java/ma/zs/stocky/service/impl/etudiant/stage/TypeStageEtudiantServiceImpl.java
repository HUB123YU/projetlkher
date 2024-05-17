package ma.zs.stocky.service.impl.etudiant.stage;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.stage.TypeStage;
import ma.zs.stocky.dao.criteria.core.stage.TypeStageCriteria;
import ma.zs.stocky.dao.facade.core.stage.TypeStageDao;
import ma.zs.stocky.dao.specification.core.stage.TypeStageSpecification;
import ma.zs.stocky.service.facade.etudiant.stage.TypeStageEtudiantService;
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
public class TypeStageEtudiantServiceImpl implements TypeStageEtudiantService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeStage update(TypeStage t) {
        TypeStage loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeStage.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeStage findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeStage findOrSave(TypeStage t) {
        if (t != null) {
            TypeStage result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TypeStage> importData(List<TypeStage> items) {
        List<TypeStage> list = new ArrayList<>();
        for (TypeStage t : items) {
            TypeStage founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TypeStage> findAll() {
        return dao.findAll();
    }

    public List<TypeStage> findByCriteria(TypeStageCriteria criteria) {
        List<TypeStage> content = null;
        if (criteria != null) {
            TypeStageSpecification mySpecification = constructSpecification(criteria);
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


    private TypeStageSpecification constructSpecification(TypeStageCriteria criteria) {
        TypeStageSpecification mySpecification =  (TypeStageSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeStageSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeStage> findPaginatedByCriteria(TypeStageCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeStageSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeStageCriteria criteria) {
        TypeStageSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(TypeStage t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeStage> delete(List<TypeStage> list) {
		List<TypeStage> result = new ArrayList();
        if (list != null) {
            for (TypeStage t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeStage create(TypeStage t) {
        TypeStage loaded = findByReferenceEntity(t);
        TypeStage saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeStage> create(List<TypeStage> ts) {
        List<TypeStage> result = new ArrayList<>();
        if (ts != null) {
            for (TypeStage t : ts) {
				TypeStage created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TypeStage findWithAssociatedLists(Long id){
        TypeStage result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeStage> update(List<TypeStage> ts, boolean createIfNotExist) {
        List<TypeStage> result = new ArrayList<>();
        if (ts != null) {
            for (TypeStage t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeStage loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TypeStage findByReferenceEntity(TypeStage t){
        return t==null? null : dao.findByReference(t.getReference());
    }



    public List<TypeStage> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeStage>> getToBeSavedAndToBeDeleted(List<TypeStage> oldList, List<TypeStage> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TypeStage> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired TypeStageDao dao;


}
