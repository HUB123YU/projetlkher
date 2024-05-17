package  ma.zs.stocky.dao.specification.core.departement;

import ma.zs.stocky.dao.criteria.core.departement.SecteurActiviteCriteria;
import ma.zs.stocky.bean.core.departement.SecteurActivite;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class SecteurActiviteSpecification extends  AbstractSpecification<SecteurActiviteCriteria, SecteurActivite>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public SecteurActiviteSpecification(SecteurActiviteCriteria criteria) {
        super(criteria);
    }

    public SecteurActiviteSpecification(SecteurActiviteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
