package  ma.zs.stocky.dao.specification.core.departement;

import ma.zs.stocky.dao.criteria.core.departement.DomaineCriteria;
import ma.zs.stocky.bean.core.departement.Domaine;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class DomaineSpecification extends  AbstractSpecification<DomaineCriteria, Domaine>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
    }

    public DomaineSpecification(DomaineCriteria criteria) {
        super(criteria);
    }

    public DomaineSpecification(DomaineCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
