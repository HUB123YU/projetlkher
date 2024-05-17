package  ma.zs.stocky.dao.specification.core.stage;

import ma.zs.stocky.dao.criteria.core.stage.StageAttachementCriteria;
import ma.zs.stocky.bean.core.stage.StageAttachement;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class StageAttachementSpecification extends  AbstractSpecification<StageAttachementCriteria, StageAttachement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("size", criteria.getSize(), criteria.getSizeMin(), criteria.getSizeMax());
        addPredicateFk("stage","id", criteria.getStage()==null?null:criteria.getStage().getId());
        addPredicateFk("stage","id", criteria.getStages());
        addPredicateFk("attachement","id", criteria.getAttachement()==null?null:criteria.getAttachement().getId());
        addPredicateFk("attachement","id", criteria.getAttachements());
    }

    public StageAttachementSpecification(StageAttachementCriteria criteria) {
        super(criteria);
    }

    public StageAttachementSpecification(StageAttachementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
