package ma.zs.stocky.service.impl.etudiant.appartenance;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.appartenance.Genre;
import ma.zs.stocky.dao.criteria.core.appartenance.GenreCriteria;
import ma.zs.stocky.dao.facade.core.appartenance.GenreDao;
import ma.zs.stocky.dao.specification.core.appartenance.GenreSpecification;
import ma.zs.stocky.service.facade.etudiant.appartenance.GenreEtudiantService;
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
public class GenreEtudiantServiceImpl implements GenreEtudiantService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Genre update(Genre t) {
        Genre loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Genre.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Genre findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Genre findOrSave(Genre t) {
        if (t != null) {
            Genre result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Genre> importData(List<Genre> items) {
        List<Genre> list = new ArrayList<>();
        for (Genre t : items) {
            Genre founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Genre> findAll() {
        return dao.findAll();
    }

    public List<Genre> findByCriteria(GenreCriteria criteria) {
        List<Genre> content = null;
        if (criteria != null) {
            GenreSpecification mySpecification = constructSpecification(criteria);
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


    private GenreSpecification constructSpecification(GenreCriteria criteria) {
        GenreSpecification mySpecification =  (GenreSpecification) RefelexivityUtil.constructObjectUsingOneParam(GenreSpecification.class, criteria);
        return mySpecification;
    }

    public List<Genre> findPaginatedByCriteria(GenreCriteria criteria, int page, int pageSize, String order, String sortField) {
        GenreSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(GenreCriteria criteria) {
        GenreSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Genre t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Genre> delete(List<Genre> list) {
		List<Genre> result = new ArrayList();
        if (list != null) {
            for (Genre t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Genre create(Genre t) {
        Genre loaded = findByReferenceEntity(t);
        Genre saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Genre> create(List<Genre> ts) {
        List<Genre> result = new ArrayList<>();
        if (ts != null) {
            for (Genre t : ts) {
				Genre created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Genre findWithAssociatedLists(Long id){
        Genre result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Genre> update(List<Genre> ts, boolean createIfNotExist) {
        List<Genre> result = new ArrayList<>();
        if (ts != null) {
            for (Genre t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Genre loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Genre findByReferenceEntity(Genre t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Genre> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Genre>> getToBeSavedAndToBeDeleted(List<Genre> oldList, List<Genre> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Genre> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired GenreDao dao;


}
