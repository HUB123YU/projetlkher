package  ma.zs.stocky.dao.specification.core.stage;

import ma.zs.stocky.dao.criteria.core.stage.StageCriteria;
import ma.zs.stocky.bean.core.stage.Stage;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class StageSpecification extends  AbstractSpecification<StageCriteria, Stage>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("sujet", criteria.getSujet(),criteria.getSujetLike());
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicate("dateDebut", criteria.getDateDebut(), criteria.getDateDebutFrom(), criteria.getDateDebutTo());
        addPredicate("dateFin", criteria.getDateFin(), criteria.getDateFinFrom(), criteria.getDateFinTo());
        addPredicate("lieu", criteria.getLieu(),criteria.getLieuLike());
        addPredicateInt("dureeSemaines", criteria.getDureeSemaines(), criteria.getDureeSemainesMin(), criteria.getDureeSemainesMax());
        addPredicateBigDecimal("note", criteria.getNote(), criteria.getNoteMin(), criteria.getNoteMax());
        addPredicate("dateSoutenance", criteria.getDateSoutenance(), criteria.getDateSoutenanceFrom(), criteria.getDateSoutenanceTo());
        addPredicateFk("domaine","id", criteria.getDomaine()==null?null:criteria.getDomaine().getId());
        addPredicateFk("domaine","id", criteria.getDomaines());
        addPredicateFk("domaine","code", criteria.getDomaine()==null?null:criteria.getDomaine().getCode());
        addPredicateFk("societe","id", criteria.getSociete()==null?null:criteria.getSociete().getId());
        addPredicateFk("societe","id", criteria.getSocietes());
        addPredicateFk("societe","ice", criteria.getSociete()==null?null:criteria.getSociete().getIce());
        addPredicateFk("jury","id", criteria.getJury()==null?null:criteria.getJury().getId());
        addPredicateFk("jury","id", criteria.getJurys());
        addPredicateFk("jury","ref", criteria.getJury()==null?null:criteria.getJury().getRef());
        addPredicateFk("filiere","id", criteria.getFiliere()==null?null:criteria.getFiliere().getId());
        addPredicateFk("filiere","id", criteria.getFilieres());
        addPredicateFk("filiere","code", criteria.getFiliere()==null?null:criteria.getFiliere().getCode());
        addPredicateFk("typeStage","id", criteria.getTypeStage()==null?null:criteria.getTypeStage().getId());
        addPredicateFk("typeStage","id", criteria.getTypeStages());
        addPredicateFk("typeStage","reference", criteria.getTypeStage()==null?null:criteria.getTypeStage().getReference());
    }

    public StageSpecification(StageCriteria criteria) {
        super(criteria);
    }

    public StageSpecification(StageCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
