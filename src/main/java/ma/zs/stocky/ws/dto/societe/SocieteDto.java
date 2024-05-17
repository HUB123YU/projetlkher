package  ma.zs.stocky.ws.dto.societe;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.stocky.ws.dto.departement.SecteurActiviteDto;
import ma.zs.stocky.ws.dto.appartenance.VilleDto;
import ma.zs.stocky.ws.dto.appartenance.PaysDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocieteDto  extends AuditBaseDto {

    private String ice  ;
    private String nom  ;
    private String adresse  ;
    private String fax  ;
    private String domaine  ;
    private String email  ;
    private String telephone  ;
    private String codePostal  ;

    private VilleDto ville ;
    private SecteurActiviteDto secteurActivite ;
    private PaysDto pays ;



    public SocieteDto(){
        super();
    }



    @Log
    public String getIce(){
        return this.ice;
    }
    public void setIce(String ice){
        this.ice = ice;
    }

    @Log
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    @Log
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    @Log
    public String getFax(){
        return this.fax;
    }
    public void setFax(String fax){
        this.fax = fax;
    }

    @Log
    public String getDomaine(){
        return this.domaine;
    }
    public void setDomaine(String domaine){
        this.domaine = domaine;
    }

    @Log
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Log
    public String getTelephone(){
        return this.telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    @Log
    public String getCodePostal(){
        return this.codePostal;
    }
    public void setCodePostal(String codePostal){
        this.codePostal = codePostal;
    }


    public VilleDto getVille(){
        return this.ville;
    }

    public void setVille(VilleDto ville){
        this.ville = ville;
    }
    public SecteurActiviteDto getSecteurActivite(){
        return this.secteurActivite;
    }

    public void setSecteurActivite(SecteurActiviteDto secteurActivite){
        this.secteurActivite = secteurActivite;
    }
    public PaysDto getPays(){
        return this.pays;
    }

    public void setPays(PaysDto pays){
        this.pays = pays;
    }






}
