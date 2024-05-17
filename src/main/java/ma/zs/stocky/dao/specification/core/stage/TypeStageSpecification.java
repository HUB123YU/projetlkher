package  ma.zs.stocky.dao.specification.core.stage;

import ma.zs.stocky.dao.criteria.core.stage.TypeStageCriteria;
import ma.zs.stocky.bean.core.stage.TypeStage;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class TypeStageSpecification extends  AbstractSpecification<TypeStageCriteria, TypeStage>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public TypeStageSpecification(TypeStageCriteria criteria) {
        super(criteria);
    }

    public TypeStageSpecification(TypeStageCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
