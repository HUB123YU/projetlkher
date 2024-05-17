package  ma.zs.stocky.dao.specification.core.appartenance;

import ma.zs.stocky.dao.criteria.core.appartenance.NationaliteCriteria;
import ma.zs.stocky.bean.core.appartenance.Nationalite;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class NationaliteSpecification extends  AbstractSpecification<NationaliteCriteria, Nationalite>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
    }

    public NationaliteSpecification(NationaliteCriteria criteria) {
        super(criteria);
    }

    public NationaliteSpecification(NationaliteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
