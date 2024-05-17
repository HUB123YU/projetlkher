package  ma.zs.stocky.dao.criteria.core.societe;


import ma.zs.stocky.dao.criteria.core.departement.SecteurActiviteCriteria;
import ma.zs.stocky.dao.criteria.core.appartenance.VilleCriteria;
import ma.zs.stocky.dao.criteria.core.appartenance.PaysCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class SocieteCriteria extends  BaseCriteria  {

    private String ice;
    private String iceLike;
    private String nom;
    private String nomLike;
    private String adresse;
    private String adresseLike;
    private String fax;
    private String faxLike;
    private String domaine;
    private String domaineLike;
    private String email;
    private String emailLike;
    private String telephone;
    private String telephoneLike;
    private String codePostal;
    private String codePostalLike;

    private VilleCriteria ville ;
    private List<VilleCriteria> villes ;
    private SecteurActiviteCriteria secteurActivite ;
    private List<SecteurActiviteCriteria> secteurActivites ;
    private PaysCriteria pays ;
    private List<PaysCriteria> payss ;


    public SocieteCriteria(){}

    public String getIce(){
        return this.ice;
    }
    public void setIce(String ice){
        this.ice = ice;
    }
    public String getIceLike(){
        return this.iceLike;
    }
    public void setIceLike(String iceLike){
        this.iceLike = iceLike;
    }

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNomLike(){
        return this.nomLike;
    }
    public void setNomLike(String nomLike){
        this.nomLike = nomLike;
    }

    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getAdresseLike(){
        return this.adresseLike;
    }
    public void setAdresseLike(String adresseLike){
        this.adresseLike = adresseLike;
    }

    public String getFax(){
        return this.fax;
    }
    public void setFax(String fax){
        this.fax = fax;
    }
    public String getFaxLike(){
        return this.faxLike;
    }
    public void setFaxLike(String faxLike){
        this.faxLike = faxLike;
    }

    public String getDomaine(){
        return this.domaine;
    }
    public void setDomaine(String domaine){
        this.domaine = domaine;
    }
    public String getDomaineLike(){
        return this.domaineLike;
    }
    public void setDomaineLike(String domaineLike){
        this.domaineLike = domaineLike;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }

    public String getTelephone(){
        return this.telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getTelephoneLike(){
        return this.telephoneLike;
    }
    public void setTelephoneLike(String telephoneLike){
        this.telephoneLike = telephoneLike;
    }

    public String getCodePostal(){
        return this.codePostal;
    }
    public void setCodePostal(String codePostal){
        this.codePostal = codePostal;
    }
    public String getCodePostalLike(){
        return this.codePostalLike;
    }
    public void setCodePostalLike(String codePostalLike){
        this.codePostalLike = codePostalLike;
    }


    public VilleCriteria getVille(){
        return this.ville;
    }

    public void setVille(VilleCriteria ville){
        this.ville = ville;
    }
    public List<VilleCriteria> getVilles(){
        return this.villes;
    }

    public void setVilles(List<VilleCriteria> villes){
        this.villes = villes;
    }
    public SecteurActiviteCriteria getSecteurActivite(){
        return this.secteurActivite;
    }

    public void setSecteurActivite(SecteurActiviteCriteria secteurActivite){
        this.secteurActivite = secteurActivite;
    }
    public List<SecteurActiviteCriteria> getSecteurActivites(){
        return this.secteurActivites;
    }

    public void setSecteurActivites(List<SecteurActiviteCriteria> secteurActivites){
        this.secteurActivites = secteurActivites;
    }
    public PaysCriteria getPays(){
        return this.pays;
    }

    public void setPays(PaysCriteria pays){
        this.pays = pays;
    }
    public List<PaysCriteria> getPayss(){
        return this.payss;
    }

    public void setPayss(List<PaysCriteria> payss){
        this.payss = payss;
    }
}
