package  ma.zs.stocky.dao.specification.core.jury;

import ma.zs.stocky.dao.criteria.core.jury.JuryCriteria;
import ma.zs.stocky.bean.core.jury.Jury;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class JurySpecification extends  AbstractSpecification<JuryCriteria, Jury>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicateInt("nombreMembres", criteria.getNombreMembres(), criteria.getNombreMembresMin(), criteria.getNombreMembresMax());
    }

    public JurySpecification(JuryCriteria criteria) {
        super(criteria);
    }

    public JurySpecification(JuryCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
