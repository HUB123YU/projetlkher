package ma.zs.stocky.service.impl.admin.encadrant;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.dao.criteria.core.encadrant.EncadrantInterneCriteria;
import ma.zs.stocky.dao.facade.core.encadrant.EncadrantInterneDao;
import ma.zs.stocky.dao.specification.core.encadrant.EncadrantInterneSpecification;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
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
public class EncadrantInterneAdminServiceImpl implements EncadrantInterneAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EncadrantInterne update(EncadrantInterne t) {
        EncadrantInterne loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EncadrantInterne.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EncadrantInterne findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EncadrantInterne findOrSave(EncadrantInterne t) {
        if (t != null) {
            EncadrantInterne result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<EncadrantInterne> importData(List<EncadrantInterne> items) {
        List<EncadrantInterne> list = new ArrayList<>();
        for (EncadrantInterne t : items) {
            EncadrantInterne founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<EncadrantInterne> findAll() {
        return dao.findAll();
    }

    public List<EncadrantInterne> findByCriteria(EncadrantInterneCriteria criteria) {
        List<EncadrantInterne> content = null;
        if (criteria != null) {
            EncadrantInterneSpecification mySpecification = constructSpecification(criteria);
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


    private EncadrantInterneSpecification constructSpecification(EncadrantInterneCriteria criteria) {
        EncadrantInterneSpecification mySpecification =  (EncadrantInterneSpecification) RefelexivityUtil.constructObjectUsingOneParam(EncadrantInterneSpecification.class, criteria);
        return mySpecification;
    }

    public List<EncadrantInterne> findPaginatedByCriteria(EncadrantInterneCriteria criteria, int page, int pageSize, String order, String sortField) {
        EncadrantInterneSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EncadrantInterneCriteria criteria) {
        EncadrantInterneSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(EncadrantInterne t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EncadrantInterne> delete(List<EncadrantInterne> list) {
		List<EncadrantInterne> result = new ArrayList();
        if (list != null) {
            for (EncadrantInterne t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EncadrantInterne> create(List<EncadrantInterne> ts) {
        List<EncadrantInterne> result = new ArrayList<>();
        if (ts != null) {
            for (EncadrantInterne t : ts) {
				EncadrantInterne created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public EncadrantInterne findWithAssociatedLists(Long id){
        EncadrantInterne result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EncadrantInterne> update(List<EncadrantInterne> ts, boolean createIfNotExist) {
        List<EncadrantInterne> result = new ArrayList<>();
        if (ts != null) {
            for (EncadrantInterne t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EncadrantInterne loadedItem = dao.findById(t.getId()).orElse(null);
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





    public EncadrantInterne findByReferenceEntity(EncadrantInterne t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<EncadrantInterne> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EncadrantInterne>> getToBeSavedAndToBeDeleted(List<EncadrantInterne> oldList, List<EncadrantInterne> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<EncadrantInterne> importExcel(MultipartFile file) {
        return null;
    }


    @Override
    public EncadrantInterne create(EncadrantInterne t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.ENCADRANTINTERNE);
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

        EncadrantInterne mySaved = dao.save(t);

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

    public EncadrantInterne findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }





    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;


    private @Autowired EncadrantInterneDao dao;


}
