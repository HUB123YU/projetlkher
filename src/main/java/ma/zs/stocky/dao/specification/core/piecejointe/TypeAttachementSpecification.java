package  ma.zs.stocky.dao.specification.core.piecejointe;

import ma.zs.stocky.dao.criteria.core.piecejointe.TypeAttachementCriteria;
import ma.zs.stocky.bean.core.piecejointe.TypeAttachement;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class TypeAttachementSpecification extends  AbstractSpecification<TypeAttachementCriteria, TypeAttachement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public TypeAttachementSpecification(TypeAttachementCriteria criteria) {
        super(criteria);
    }

    public TypeAttachementSpecification(TypeAttachementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
