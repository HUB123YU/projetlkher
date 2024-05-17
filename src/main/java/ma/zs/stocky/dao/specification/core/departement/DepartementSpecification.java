package  ma.zs.stocky.dao.specification.core.departement;

import ma.zs.stocky.dao.criteria.core.departement.DepartementCriteria;
import ma.zs.stocky.bean.core.departement.Departement;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class DepartementSpecification extends  AbstractSpecification<DepartementCriteria, Departement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
    }

    public DepartementSpecification(DepartementCriteria criteria) {
        super(criteria);
    }

    public DepartementSpecification(DepartementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
