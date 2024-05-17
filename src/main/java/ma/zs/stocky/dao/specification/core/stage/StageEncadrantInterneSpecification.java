package  ma.zs.stocky.dao.specification.core.stage;

import ma.zs.stocky.dao.criteria.core.stage.StageEncadrantInterneCriteria;
import ma.zs.stocky.bean.core.stage.StageEncadrantInterne;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class StageEncadrantInterneSpecification extends  AbstractSpecification<StageEncadrantInterneCriteria, StageEncadrantInterne>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("stage","id", criteria.getStage()==null?null:criteria.getStage().getId());
        addPredicateFk("stage","id", criteria.getStages());
        addPredicateFk("encadrantInterne","id", criteria.getEncadrantInterne()==null?null:criteria.getEncadrantInterne().getId());
        addPredicateFk("encadrantInterne","id", criteria.getEncadrantInternes());
        addPredicateFk("encadrantInterne","code", criteria.getEncadrantInterne()==null?null:criteria.getEncadrantInterne().getCode());
    }

    public StageEncadrantInterneSpecification(StageEncadrantInterneCriteria criteria) {
        super(criteria);
    }

    public StageEncadrantInterneSpecification(StageEncadrantInterneCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
