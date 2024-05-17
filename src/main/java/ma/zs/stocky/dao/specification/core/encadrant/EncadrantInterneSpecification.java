package  ma.zs.stocky.dao.specification.core.encadrant;

import ma.zs.stocky.dao.criteria.core.encadrant.EncadrantInterneCriteria;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class EncadrantInterneSpecification extends  AbstractSpecification<EncadrantInterneCriteria, EncadrantInterne>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("biographie", criteria.getBiographie(),criteria.getBiographieLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
    }

    public EncadrantInterneSpecification(EncadrantInterneCriteria criteria) {
        super(criteria);
    }

    public EncadrantInterneSpecification(EncadrantInterneCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
