package  ma.zs.stocky.dao.specification.core.encadrant;

import ma.zs.stocky.dao.criteria.core.encadrant.EncadrantExterneCriteria;
import ma.zs.stocky.bean.core.encadrant.EncadrantExterne;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class EncadrantExterneSpecification extends  AbstractSpecification<EncadrantExterneCriteria, EncadrantExterne>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("prenom", criteria.getPrenom(),criteria.getPrenomLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("telephone", criteria.getTelephone(),criteria.getTelephoneLike());
        addPredicate("biographie", criteria.getBiographie(),criteria.getBiographieLike());
        addPredicateFk("societe","id", criteria.getSociete()==null?null:criteria.getSociete().getId());
        addPredicateFk("societe","id", criteria.getSocietes());
        addPredicateFk("societe","ice", criteria.getSociete()==null?null:criteria.getSociete().getIce());
    }

    public EncadrantExterneSpecification(EncadrantExterneCriteria criteria) {
        super(criteria);
    }

    public EncadrantExterneSpecification(EncadrantExterneCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
