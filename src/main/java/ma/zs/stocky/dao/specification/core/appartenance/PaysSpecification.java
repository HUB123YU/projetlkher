package  ma.zs.stocky.dao.specification.core.appartenance;

import ma.zs.stocky.dao.criteria.core.appartenance.PaysCriteria;
import ma.zs.stocky.bean.core.appartenance.Pays;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class PaysSpecification extends  AbstractSpecification<PaysCriteria, Pays>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public PaysSpecification(PaysCriteria criteria) {
        super(criteria);
    }

    public PaysSpecification(PaysCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
