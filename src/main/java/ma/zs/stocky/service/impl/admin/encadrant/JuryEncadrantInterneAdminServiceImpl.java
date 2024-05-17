package ma.zs.stocky.service.impl.admin.encadrant;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.encadrant.JuryEncadrantInterne;
import ma.zs.stocky.dao.criteria.core.encadrant.JuryEncadrantInterneCriteria;
import ma.zs.stocky.dao.facade.core.encadrant.JuryEncadrantInterneDao;
import ma.zs.stocky.dao.specification.core.encadrant.JuryEncadrantInterneSpecification;
import ma.zs.stocky.service.facade.admin.encadrant.JuryEncadrantInterneAdminService;
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

import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService ;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne ;
import ma.zs.stocky.service.facade.admin.jury.JuryAdminService ;
import ma.zs.stocky.bean.core.jury.Jury ;

import java.util.List;
@Service
public class JuryEncadrantInterneAdminServiceImpl implements JuryEncadrantInterneAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public JuryEncadrantInterne update(JuryEncadrantInterne t) {
        JuryEncadrantInterne loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{JuryEncadrantInterne.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public JuryEncadrantInterne findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public JuryEncadrantInterne findOrSave(JuryEncadrantInterne t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            JuryEncadrantInterne result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<JuryEncadrantInterne> importData(List<JuryEncadrantInterne> items) {
        List<JuryEncadrantInterne> list = new ArrayList<>();
        for (JuryEncadrantInterne t : items) {
            JuryEncadrantInterne founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<JuryEncadrantInterne> findAll() {
        return dao.findAll();
    }

    public List<JuryEncadrantInterne> findByCriteria(JuryEncadrantInterneCriteria criteria) {
        List<JuryEncadrantInterne> content = null;
        if (criteria != null) {
            JuryEncadrantInterneSpecification mySpecification = constructSpecification(criteria);
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


    private JuryEncadrantInterneSpecification constructSpecification(JuryEncadrantInterneCriteria criteria) {
        JuryEncadrantInterneSpecification mySpecification =  (JuryEncadrantInterneSpecification) RefelexivityUtil.constructObjectUsingOneParam(JuryEncadrantInterneSpecification.class, criteria);
        return mySpecification;
    }

    public List<JuryEncadrantInterne> findPaginatedByCriteria(JuryEncadrantInterneCriteria criteria, int page, int pageSize, String order, String sortField) {
        JuryEncadrantInterneSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(JuryEncadrantInterneCriteria criteria) {
        JuryEncadrantInterneSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<JuryEncadrantInterne> findByEncadrantInterneId(Long id){
        return dao.findByEncadrantInterneId(id);
    }
    public int deleteByEncadrantInterneId(Long id){
        return dao.deleteByEncadrantInterneId(id);
    }
    public long countByEncadrantInterneCode(String code){
        return dao.countByEncadrantInterneCode(code);
    }
    public List<JuryEncadrantInterne> findByJuryId(Long id){
        return dao.findByJuryId(id);
    }
    public int deleteByJuryId(Long id){
        return dao.deleteByJuryId(id);
    }
    public long countByJuryRef(String ref){
        return dao.countByJuryRef(ref);
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
    public int delete(JuryEncadrantInterne t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<JuryEncadrantInterne> delete(List<JuryEncadrantInterne> list) {
		List<JuryEncadrantInterne> result = new ArrayList();
        if (list != null) {
            for (JuryEncadrantInterne t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public JuryEncadrantInterne create(JuryEncadrantInterne t) {
        JuryEncadrantInterne loaded = findByReferenceEntity(t);
        JuryEncadrantInterne saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<JuryEncadrantInterne> create(List<JuryEncadrantInterne> ts) {
        List<JuryEncadrantInterne> result = new ArrayList<>();
        if (ts != null) {
            for (JuryEncadrantInterne t : ts) {
				JuryEncadrantInterne created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public JuryEncadrantInterne findWithAssociatedLists(Long id){
        JuryEncadrantInterne result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<JuryEncadrantInterne> update(List<JuryEncadrantInterne> ts, boolean createIfNotExist) {
        List<JuryEncadrantInterne> result = new ArrayList<>();
        if (ts != null) {
            for (JuryEncadrantInterne t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    JuryEncadrantInterne loadedItem = dao.findById(t.getId()).orElse(null);
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





    public JuryEncadrantInterne findByReferenceEntity(JuryEncadrantInterne t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(JuryEncadrantInterne t){
        if( t != null) {
            t.setEncadrantInterne(encadrantInterneService.findOrSave(t.getEncadrantInterne()));
            t.setJury(juryService.findOrSave(t.getJury()));
        }
    }



    public List<JuryEncadrantInterne> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<JuryEncadrantInterne>> getToBeSavedAndToBeDeleted(List<JuryEncadrantInterne> oldList, List<JuryEncadrantInterne> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<JuryEncadrantInterne> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EncadrantInterneAdminService encadrantInterneService ;
    @Autowired
    private JuryAdminService juryService ;

    private @Autowired JuryEncadrantInterneDao dao;


}
