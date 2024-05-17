package ma.zs.stocky.service.impl.etudiant.etudiant;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.etudiant.Etudiant;
import ma.zs.stocky.dao.criteria.core.etudiant.EtudiantCriteria;
import ma.zs.stocky.dao.facade.core.etudiant.EtudiantDao;
import ma.zs.stocky.dao.specification.core.etudiant.EtudiantSpecification;
import ma.zs.stocky.service.facade.etudiant.etudiant.EtudiantEtudiantService;
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

import ma.zs.stocky.service.facade.etudiant.departement.FiliereEtudiantService ;
import ma.zs.stocky.bean.core.departement.Filiere ;
import ma.zs.stocky.service.facade.etudiant.appartenance.NationaliteEtudiantService ;
import ma.zs.stocky.bean.core.appartenance.Nationalite ;
import ma.zs.stocky.service.facade.etudiant.appartenance.GenreEtudiantService ;
import ma.zs.stocky.bean.core.appartenance.Genre ;

import java.time.LocalDateTime;
import ma.zs.stocky.zynerator.security.service.facade.UserService;
import ma.zs.stocky.zynerator.security.service.facade.RoleService;
import ma.zs.stocky.zynerator.security.service.facade.RoleUserService;
import ma.zs.stocky.zynerator.security.bean.Role;
import ma.zs.stocky.zynerator.security.bean.RoleUser;
import ma.zs.stocky.zynerator.security.common.AuthoritiesConstants;
import ma.zs.stocky.zynerator.security.service.facade.ModelPermissionUserService;
import java.util.Collection;
import java.util.List;
@Service
public class EtudiantEtudiantServiceImpl implements EtudiantEtudiantService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Etudiant update(Etudiant t) {
        Etudiant loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Etudiant.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Etudiant findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Etudiant findOrSave(Etudiant t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Etudiant result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Etudiant> importData(List<Etudiant> items) {
        List<Etudiant> list = new ArrayList<>();
        for (Etudiant t : items) {
            Etudiant founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Etudiant> findAll() {
        return dao.findAll();
    }

    public List<Etudiant> findByCriteria(EtudiantCriteria criteria) {
        List<Etudiant> content = null;
        if (criteria != null) {
            EtudiantSpecification mySpecification = constructSpecification(criteria);
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


    private EtudiantSpecification constructSpecification(EtudiantCriteria criteria) {
        EtudiantSpecification mySpecification =  (EtudiantSpecification) RefelexivityUtil.constructObjectUsingOneParam(EtudiantSpecification.class, criteria);
        return mySpecification;
    }

    public List<Etudiant> findPaginatedByCriteria(EtudiantCriteria criteria, int page, int pageSize, String order, String sortField) {
        EtudiantSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EtudiantCriteria criteria) {
        EtudiantSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Etudiant> findByGenreId(Long id){
        return dao.findByGenreId(id);
    }
    public int deleteByGenreId(Long id){
        return dao.deleteByGenreId(id);
    }
    public long countByGenreCode(String code){
        return dao.countByGenreCode(code);
    }
    public List<Etudiant> findByNationaliteId(Long id){
        return dao.findByNationaliteId(id);
    }
    public int deleteByNationaliteId(Long id){
        return dao.deleteByNationaliteId(id);
    }
    public long countByNationaliteCode(String code){
        return dao.countByNationaliteCode(code);
    }
    public List<Etudiant> findByFiliereId(Long id){
        return dao.findByFiliereId(id);
    }
    public int deleteByFiliereId(Long id){
        return dao.deleteByFiliereId(id);
    }
    public long countByFiliereCode(String code){
        return dao.countByFiliereCode(code);
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
    public int delete(Etudiant t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Etudiant> delete(List<Etudiant> list) {
		List<Etudiant> result = new ArrayList();
        if (list != null) {
            for (Etudiant t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Etudiant> create(List<Etudiant> ts) {
        List<Etudiant> result = new ArrayList<>();
        if (ts != null) {
            for (Etudiant t : ts) {
				Etudiant created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Etudiant findWithAssociatedLists(Long id){
        Etudiant result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Etudiant> update(List<Etudiant> ts, boolean createIfNotExist) {
        List<Etudiant> result = new ArrayList<>();
        if (ts != null) {
            for (Etudiant t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Etudiant loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Etudiant findByReferenceEntity(Etudiant t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Etudiant t){
        if( t != null) {
            t.setGenre(genreService.findOrSave(t.getGenre()));
            t.setNationalite(nationaliteService.findOrSave(t.getNationalite()));
            t.setFiliere(filiereService.findOrSave(t.getFiliere()));
        }
    }



    public List<Etudiant> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Etudiant>> getToBeSavedAndToBeDeleted(List<Etudiant> oldList, List<Etudiant> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Etudiant> importExcel(MultipartFile file) {
        return null;
    }


    @Override
    public Etudiant create(Etudiant t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.ETUDIANT);
        role.setCreatedAt(LocalDateTime.now());
        Role myRole = roleService.create(role);
        RoleUser roleUser = new RoleUser();
        roleUser.setRole(myRole);
        if (t.getRoleUsers() == null)
            t.setRoleUsers(new ArrayList<>());

        t.getRoleUsers().add(roleUser);
        if (t.getModelPermissionUsers() == null)
            t.setModelPermissionUsers(new ArrayList<>());

        t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        Etudiant mySaved = dao.save(t);

        if (t.getModelPermissionUsers() != null) {
            t.getModelPermissionUsers().forEach(e -> {
                e.setUser(mySaved);
                modelPermissionUserService.create(e);
            });
        }
        if (t.getRoleUsers() != null) {
            t.getRoleUsers().forEach(element-> {
                element.setUser(mySaved);
                roleUserService.create(element);
            });
        }

        return mySaved;
     }

    public Etudiant findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }





    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;

    @Autowired
    private FiliereEtudiantService filiereService ;
    @Autowired
    private NationaliteEtudiantService nationaliteService ;
    @Autowired
    private GenreEtudiantService genreService ;

    private @Autowired EtudiantDao dao;


}
