package  ma.zs.stocky.dao.specification.core.stage;

import ma.zs.stocky.dao.criteria.core.stage.StageEncadrantExterneCriteria;
import ma.zs.stocky.bean.core.stage.StageEncadrantExterne;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class StageEncadrantExterneSpecification extends  AbstractSpecification<StageEncadrantExterneCriteria, StageEncadrantExterne>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("stage","id", criteria.getStage()==null?null:criteria.getStage().getId());
        addPredicateFk("stage","id", criteria.getStages());
        addPredicateFk("encadrantExterne","id", criteria.getEncadrantExterne()==null?null:criteria.getEncadrantExterne().getId());
        addPredicateFk("encadrantExterne","id", criteria.getEncadrantExternes());
        addPredicateFk("encadrantExterne","code", criteria.getEncadrantExterne()==null?null:criteria.getEncadrantExterne().getCode());
    }

    public StageEncadrantExterneSpecification(StageEncadrantExterneCriteria criteria) {
        super(criteria);
    }

    public StageEncadrantExterneSpecification(StageEncadrantExterneCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
