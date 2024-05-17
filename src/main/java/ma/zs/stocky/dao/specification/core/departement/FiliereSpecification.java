package  ma.zs.stocky.dao.specification.core.departement;

import ma.zs.stocky.dao.criteria.core.departement.FiliereCriteria;
import ma.zs.stocky.bean.core.departement.Filiere;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class FiliereSpecification extends  AbstractSpecification<FiliereCriteria, Filiere>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("departement","id", criteria.getDepartement()==null?null:criteria.getDepartement().getId());
        addPredicateFk("departement","id", criteria.getDepartements());
        addPredicateFk("departement","code", criteria.getDepartement()==null?null:criteria.getDepartement().getCode());
    }

    public FiliereSpecification(FiliereCriteria criteria) {
        super(criteria);
    }

    public FiliereSpecification(FiliereCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
