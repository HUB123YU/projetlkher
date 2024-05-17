package ma.zs.stocky.service.impl.etudiant.jury;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.jury.Jury;
import ma.zs.stocky.dao.criteria.core.jury.JuryCriteria;
import ma.zs.stocky.dao.facade.core.jury.JuryDao;
import ma.zs.stocky.dao.specification.core.jury.JurySpecification;
import ma.zs.stocky.service.facade.etudiant.jury.JuryEtudiantService;
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

import ma.zs.stocky.service.facade.etudiant.encadrant.JuryEncadrantInterneEtudiantService ;
import ma.zs.stocky.bean.core.encadrant.JuryEncadrantInterne ;

import java.util.List;
@Service
public class JuryEtudiantServiceImpl implements JuryEtudiantService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Jury update(Jury t) {
        Jury loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Jury.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Jury findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Jury findOrSave(Jury t) {
        if (t != null) {
            Jury result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Jury> importData(List<Jury> items) {
        List<Jury> list = new ArrayList<>();
        for (Jury t : items) {
            Jury founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Jury> findAll() {
        return dao.findAll();
    }

    public List<Jury> findByCriteria(JuryCriteria criteria) {
        List<Jury> content = null;
        if (criteria != null) {
            JurySpecification mySpecification = constructSpecification(criteria);
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


    private JurySpecification constructSpecification(JuryCriteria criteria) {
        JurySpecification mySpecification =  (JurySpecification) RefelexivityUtil.constructObjectUsingOneParam(JurySpecification.class, criteria);
        return mySpecification;
    }

    public List<Jury> findPaginatedByCriteria(JuryCriteria criteria, int page, int pageSize, String order, String sortField) {
        JurySpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(JuryCriteria criteria) {
        JurySpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }


	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
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
    public int delete(Jury t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        juryEncadrantInterneService.deleteByJuryId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Jury> delete(List<Jury> list) {
		List<Jury> result = new ArrayList();
        if (list != null) {
            for (Jury t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Jury create(Jury t) {
        Jury loaded = findByReferenceEntity(t);
        Jury saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getJuryEncadrantInternes() != null) {
                t.getJuryEncadrantInternes().forEach(element-> {
                    element.setJury(saved);
                    juryEncadrantInterneService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Jury> create(List<Jury> ts) {
        List<Jury> result = new ArrayList<>();
        if (ts != null) {
            for (Jury t : ts) {
				Jury created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Jury findWithAssociatedLists(Long id){
        Jury result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setJuryEncadrantInternes(juryEncadrantInterneService.findByJuryId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Jury> update(List<Jury> ts, boolean createIfNotExist) {
        List<Jury> result = new ArrayList<>();
        if (ts != null) {
            for (Jury t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Jury loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Jury jury){
    if(jury !=null && jury.getId() != null){
        List<List<JuryEncadrantInterne>> resultJuryEncadrantInternes= juryEncadrantInterneService.getToBeSavedAndToBeDeleted(juryEncadrantInterneService.findByJuryId(jury.getId()),jury.getJuryEncadrantInternes());
            juryEncadrantInterneService.delete(resultJuryEncadrantInternes.get(1));
        ListUtil.emptyIfNull(resultJuryEncadrantInternes.get(0)).forEach(e -> e.setJury(jury));
        juryEncadrantInterneService.update(resultJuryEncadrantInternes.get(0),true);
        }
    }




    public Jury findByReferenceEntity(Jury t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<Jury> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Jury>> getToBeSavedAndToBeDeleted(List<Jury> oldList, List<Jury> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Jury> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private JuryEncadrantInterneEtudiantService juryEncadrantInterneService ;

    private @Autowired JuryDao dao;


}
