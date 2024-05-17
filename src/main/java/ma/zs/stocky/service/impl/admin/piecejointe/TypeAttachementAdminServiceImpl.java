package ma.zs.stocky.service.impl.admin.piecejointe;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.piecejointe.TypeAttachement;
import ma.zs.stocky.dao.criteria.core.piecejointe.TypeAttachementCriteria;
import ma.zs.stocky.dao.facade.core.piecejointe.TypeAttachementDao;
import ma.zs.stocky.dao.specification.core.piecejointe.TypeAttachementSpecification;
import ma.zs.stocky.service.facade.admin.piecejointe.TypeAttachementAdminService;
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
public class TypeAttachementAdminServiceImpl implements TypeAttachementAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeAttachement update(TypeAttachement t) {
        TypeAttachement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeAttachement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeAttachement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeAttachement findOrSave(TypeAttachement t) {
        if (t != null) {
            TypeAttachement result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TypeAttachement> importData(List<TypeAttachement> items) {
        List<TypeAttachement> list = new ArrayList<>();
        for (TypeAttachement t : items) {
            TypeAttachement founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TypeAttachement> findAll() {
        return dao.findAll();
    }

    public List<TypeAttachement> findByCriteria(TypeAttachementCriteria criteria) {
        List<TypeAttachement> content = null;
        if (criteria != null) {
            TypeAttachementSpecification mySpecification = constructSpecification(criteria);
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


    private TypeAttachementSpecification constructSpecification(TypeAttachementCriteria criteria) {
        TypeAttachementSpecification mySpecification =  (TypeAttachementSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeAttachementSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeAttachement> findPaginatedByCriteria(TypeAttachementCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeAttachementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeAttachementCriteria criteria) {
        TypeAttachementSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(TypeAttachement t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeAttachement> delete(List<TypeAttachement> list) {
		List<TypeAttachement> result = new ArrayList();
        if (list != null) {
            for (TypeAttachement t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeAttachement create(TypeAttachement t) {
        TypeAttachement loaded = findByReferenceEntity(t);
        TypeAttachement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeAttachement> create(List<TypeAttachement> ts) {
        List<TypeAttachement> result = new ArrayList<>();
        if (ts != null) {
            for (TypeAttachement t : ts) {
				TypeAttachement created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TypeAttachement findWithAssociatedLists(Long id){
        TypeAttachement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeAttachement> update(List<TypeAttachement> ts, boolean createIfNotExist) {
        List<TypeAttachement> result = new ArrayList<>();
        if (ts != null) {
            for (TypeAttachement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeAttachement loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TypeAttachement findByReferenceEntity(TypeAttachement t){
        return t==null? null : dao.findByReference(t.getReference());
    }



    public List<TypeAttachement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeAttachement>> getToBeSavedAndToBeDeleted(List<TypeAttachement> oldList, List<TypeAttachement> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TypeAttachement> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired TypeAttachementDao dao;


}
