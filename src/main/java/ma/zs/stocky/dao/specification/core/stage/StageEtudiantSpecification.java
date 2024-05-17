package  ma.zs.stocky.dao.specification.core.stage;

import ma.zs.stocky.dao.criteria.core.stage.StageEtudiantCriteria;
import ma.zs.stocky.bean.core.stage.StageEtudiant;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class StageEtudiantSpecification extends  AbstractSpecification<StageEtudiantCriteria, StageEtudiant>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("stage","id", criteria.getStage()==null?null:criteria.getStage().getId());
        addPredicateFk("stage","id", criteria.getStages());
        addPredicateFk("etudiant","id", criteria.getEtudiant()==null?null:criteria.getEtudiant().getId());
        addPredicateFk("etudiant","id", criteria.getEtudiants());
    }

    public StageEtudiantSpecification(StageEtudiantCriteria criteria) {
        super(criteria);
    }

    public StageEtudiantSpecification(StageEtudiantCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
