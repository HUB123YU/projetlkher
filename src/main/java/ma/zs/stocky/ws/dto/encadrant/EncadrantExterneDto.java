package  ma.zs.stocky.ws.dto.encadrant;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.stocky.ws.dto.societe.SocieteDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EncadrantExterneDto  extends AuditBaseDto {

    private String code  ;
    private String nom  ;
    private String prenom  ;
    private String email  ;
    private String telephone  ;
    private String biographie  ;

    private SocieteDto societe ;



    public EncadrantExterneDto(){
        super();
    }



    @Log
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Log
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    @Log
    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
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
    public String getBiographie(){
        return this.biographie;
    }
    public void setBiographie(String biographie){
        this.biographie = biographie;
    }


    public SocieteDto getSociete(){
        return this.societe;
    }

    public void setSociete(SocieteDto societe){
        this.societe = societe;
    }






}
