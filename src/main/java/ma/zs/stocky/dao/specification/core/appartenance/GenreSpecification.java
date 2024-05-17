package  ma.zs.stocky.dao.specification.core.appartenance;

import ma.zs.stocky.dao.criteria.core.appartenance.GenreCriteria;
import ma.zs.stocky.bean.core.appartenance.Genre;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class GenreSpecification extends  AbstractSpecification<GenreCriteria, Genre>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
    }

    public GenreSpecification(GenreCriteria criteria) {
        super(criteria);
    }

    public GenreSpecification(GenreCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
