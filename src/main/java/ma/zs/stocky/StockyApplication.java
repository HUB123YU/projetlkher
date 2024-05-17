package ma.zs.stocky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.etudiant.Etudiant;
import ma.zs.stocky.service.facade.admin.etudiant.EtudiantAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.service.facade.admin.encadrant.EncadrantInterneAdminService;
import ma.zs.stocky.zynerator.security.bean.*;
import ma.zs.stocky.zynerator.security.common.AuthoritiesConstants;
import ma.zs.stocky.zynerator.security.service.facade.*;


import ma.zs.stocky.zynerator.security.bean.User;
import ma.zs.stocky.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class StockyApplication {
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx=SpringApplication.run(StockyApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService , EncadrantInterneAdminService encadrantInterneService, EtudiantAdminService etudiantService) {
    return (args) -> {
        if(true){


        // ModelPermissions
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        // ActionPermissions
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);
		if (userForAdmin.getModelPermissionUsers() == null)
			userForAdmin.setModelPermissionUsers(new ArrayList<>());


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

		// User Encadrantinterne
        EncadrantInterne userForEncadrantinterne = new EncadrantInterne("encadrantinterne");
		userForEncadrantinterne.setPassword("123");
		// Role Encadrantinterne
		Role roleForEncadrantinterne = new Role();
		roleForEncadrantinterne.setAuthority(AuthoritiesConstants.ENCADRANTINTERNE);
		roleForEncadrantinterne.setCreatedAt(LocalDateTime.now());
		Role roleForEncadrantinterneSaved = roleService.create(roleForEncadrantinterne);
		RoleUser roleUserForEncadrantinterne = new RoleUser();
		roleUserForEncadrantinterne.setRole(roleForEncadrantinterneSaved);
		if (userForEncadrantinterne.getRoleUsers() == null)
			userForEncadrantinterne.setRoleUsers(new ArrayList<>());

		userForEncadrantinterne.getRoleUsers().add(roleUserForEncadrantinterne);
		if (userForEncadrantinterne.getModelPermissionUsers() == null)
			userForEncadrantinterne.setModelPermissionUsers(new ArrayList<>());


        userForEncadrantinterne.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        encadrantInterneService.create(userForEncadrantinterne);

		// User Etudiant
        Etudiant userForEtudiant = new Etudiant("etudiant");
		userForEtudiant.setPassword("123");
		// Role Etudiant
		Role roleForEtudiant = new Role();
		roleForEtudiant.setAuthority(AuthoritiesConstants.ETUDIANT);
		roleForEtudiant.setCreatedAt(LocalDateTime.now());
		Role roleForEtudiantSaved = roleService.create(roleForEtudiant);
		RoleUser roleUserForEtudiant = new RoleUser();
		roleUserForEtudiant.setRole(roleForEtudiantSaved);
		if (userForEtudiant.getRoleUsers() == null)
			userForEtudiant.setRoleUsers(new ArrayList<>());

		userForEtudiant.getRoleUsers().add(roleUserForEtudiant);
		if (userForEtudiant.getModelPermissionUsers() == null)
			userForEtudiant.setModelPermissionUsers(new ArrayList<>());


        userForEtudiant.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        etudiantService.create(userForEtudiant);

		            }
        };
    }




    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("Departement"));
        modelPermissions.add(new ModelPermission("Domaine"));
        modelPermissions.add(new ModelPermission("Filiere"));
        modelPermissions.add(new ModelPermission("JuryEncadrantInterne"));
        modelPermissions.add(new ModelPermission("Attachement"));
        modelPermissions.add(new ModelPermission("Pays"));
        modelPermissions.add(new ModelPermission("StageEncadrantInterne"));
        modelPermissions.add(new ModelPermission("Genre"));
        modelPermissions.add(new ModelPermission("EncadrantExterne"));
        modelPermissions.add(new ModelPermission("Nationalite"));
        modelPermissions.add(new ModelPermission("StageAttachement"));
        modelPermissions.add(new ModelPermission("Jury"));
        modelPermissions.add(new ModelPermission("SecteurActivite"));
        modelPermissions.add(new ModelPermission("Ville"));
        modelPermissions.add(new ModelPermission("StageEtudiant"));
        modelPermissions.add(new ModelPermission("Etudiant"));
        modelPermissions.add(new ModelPermission("TypeStage"));
        modelPermissions.add(new ModelPermission("Stage"));
        modelPermissions.add(new ModelPermission("TypeAttachement"));
        modelPermissions.add(new ModelPermission("Societe"));
        modelPermissions.add(new ModelPermission("StageEncadrantExterne"));
        modelPermissions.add(new ModelPermission("EncadrantInterne"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


}


