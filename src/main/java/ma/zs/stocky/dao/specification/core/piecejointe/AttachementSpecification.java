package  ma.zs.stocky.dao.specification.core.piecejointe;

import ma.zs.stocky.dao.criteria.core.piecejointe.AttachementCriteria;
import ma.zs.stocky.bean.core.piecejointe.Attachement;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class AttachementSpecification extends  AbstractSpecification<AttachementCriteria, Attachement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("contenu", criteria.getContenu(),criteria.getContenuLike());
        addPredicateBigDecimal("taille", criteria.getTaille(), criteria.getTailleMin(), criteria.getTailleMax());
        addPredicateFk("typeAttachement","id", criteria.getTypeAttachement()==null?null:criteria.getTypeAttachement().getId());
        addPredicateFk("typeAttachement","id", criteria.getTypeAttachements());
        addPredicateFk("typeAttachement","reference", criteria.getTypeAttachement()==null?null:criteria.getTypeAttachement().getReference());
        addPredicateFk("stage","id", criteria.getStage()==null?null:criteria.getStage().getId());
        addPredicateFk("stage","id", criteria.getStages());
    }

    public AttachementSpecification(AttachementCriteria criteria) {
        super(criteria);
    }

    public AttachementSpecification(AttachementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
