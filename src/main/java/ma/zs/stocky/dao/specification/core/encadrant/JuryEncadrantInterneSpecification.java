package  ma.zs.stocky.dao.specification.core.encadrant;

import ma.zs.stocky.dao.criteria.core.encadrant.JuryEncadrantInterneCriteria;
import ma.zs.stocky.bean.core.encadrant.JuryEncadrantInterne;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class JuryEncadrantInterneSpecification extends  AbstractSpecification<JuryEncadrantInterneCriteria, JuryEncadrantInterne>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("encadrantInterne","id", criteria.getEncadrantInterne()==null?null:criteria.getEncadrantInterne().getId());
        addPredicateFk("encadrantInterne","id", criteria.getEncadrantInternes());
        addPredicateFk("encadrantInterne","code", criteria.getEncadrantInterne()==null?null:criteria.getEncadrantInterne().getCode());
        addPredicateFk("jury","id", criteria.getJury()==null?null:criteria.getJury().getId());
        addPredicateFk("jury","id", criteria.getJurys());
        addPredicateFk("jury","ref", criteria.getJury()==null?null:criteria.getJury().getRef());
    }

    public JuryEncadrantInterneSpecification(JuryEncadrantInterneCriteria criteria) {
        super(criteria);
    }

    public JuryEncadrantInterneSpecification(JuryEncadrantInterneCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
