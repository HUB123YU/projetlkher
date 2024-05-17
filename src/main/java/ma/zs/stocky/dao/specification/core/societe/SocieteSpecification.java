package  ma.zs.stocky.dao.specification.core.societe;

import ma.zs.stocky.dao.criteria.core.societe.SocieteCriteria;
import ma.zs.stocky.bean.core.societe.Societe;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class SocieteSpecification extends  AbstractSpecification<SocieteCriteria, Societe>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ice", criteria.getIce(),criteria.getIceLike());
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("adresse", criteria.getAdresse(),criteria.getAdresseLike());
        addPredicate("fax", criteria.getFax(),criteria.getFaxLike());
        addPredicate("domaine", criteria.getDomaine(),criteria.getDomaineLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("telephone", criteria.getTelephone(),criteria.getTelephoneLike());
        addPredicate("codePostal", criteria.getCodePostal(),criteria.getCodePostalLike());
        addPredicateFk("ville","id", criteria.getVille()==null?null:criteria.getVille().getId());
        addPredicateFk("ville","id", criteria.getVilles());
        addPredicateFk("ville","reference", criteria.getVille()==null?null:criteria.getVille().getReference());
        addPredicateFk("secteurActivite","id", criteria.getSecteurActivite()==null?null:criteria.getSecteurActivite().getId());
        addPredicateFk("secteurActivite","id", criteria.getSecteurActivites());
        addPredicateFk("secteurActivite","reference", criteria.getSecteurActivite()==null?null:criteria.getSecteurActivite().getReference());
        addPredicateFk("pays","id", criteria.getPays()==null?null:criteria.getPays().getId());
        addPredicateFk("pays","id", criteria.getPayss());
        addPredicateFk("pays","reference", criteria.getPays()==null?null:criteria.getPays().getReference());
    }

    public SocieteSpecification(SocieteCriteria criteria) {
        super(criteria);
    }

    public SocieteSpecification(SocieteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
