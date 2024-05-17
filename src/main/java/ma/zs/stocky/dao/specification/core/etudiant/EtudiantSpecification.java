package  ma.zs.stocky.dao.specification.core.etudiant;

import ma.zs.stocky.dao.criteria.core.etudiant.EtudiantCriteria;
import ma.zs.stocky.bean.core.etudiant.Etudiant;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class EtudiantSpecification extends  AbstractSpecification<EtudiantCriteria, Etudiant>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("adresse", criteria.getAdresse(),criteria.getAdresseLike());
        addPredicate("telephone", criteria.getTelephone(),criteria.getTelephoneLike());
        addPredicate("dateNaissance", criteria.getDateNaissance(), criteria.getDateNaissanceFrom(), criteria.getDateNaissanceTo());
        addPredicate("codeAppoge", criteria.getCodeAppoge(),criteria.getCodeAppogeLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicateFk("genre","id", criteria.getGenre()==null?null:criteria.getGenre().getId());
        addPredicateFk("genre","id", criteria.getGenres());
        addPredicateFk("genre","code", criteria.getGenre()==null?null:criteria.getGenre().getCode());
        addPredicateFk("nationalite","id", criteria.getNationalite()==null?null:criteria.getNationalite().getId());
        addPredicateFk("nationalite","id", criteria.getNationalites());
        addPredicateFk("nationalite","code", criteria.getNationalite()==null?null:criteria.getNationalite().getCode());
        addPredicateFk("filiere","id", criteria.getFiliere()==null?null:criteria.getFiliere().getId());
        addPredicateFk("filiere","id", criteria.getFilieres());
        addPredicateFk("filiere","code", criteria.getFiliere()==null?null:criteria.getFiliere().getCode());
    }

    public EtudiantSpecification(EtudiantCriteria criteria) {
        super(criteria);
    }

    public EtudiantSpecification(EtudiantCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
