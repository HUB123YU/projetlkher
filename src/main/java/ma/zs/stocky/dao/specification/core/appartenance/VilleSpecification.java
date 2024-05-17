package  ma.zs.stocky.dao.specification.core.appartenance;

import ma.zs.stocky.dao.criteria.core.appartenance.VilleCriteria;
import ma.zs.stocky.bean.core.appartenance.Ville;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class VilleSpecification extends  AbstractSpecification<VilleCriteria, Ville>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public VilleSpecification(VilleCriteria criteria) {
        super(criteria);
    }

    public VilleSpecification(VilleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
