package  ma.zs.stocky.ws.dto.piecejointe;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zs.stocky.ws.dto.stage.StageDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachementDto  extends AuditBaseDto {

    private String nom  ;
    private String contenu  ;
    private BigDecimal taille  ;

    private TypeAttachementDto typeAttachement ;
    private StageDto stage ;



    public AttachementDto(){
        super();
    }



    @Log
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    @Log
    public String getContenu(){
        return this.contenu;
    }
    public void setContenu(String contenu){
        this.contenu = contenu;
    }

    @Log
    public BigDecimal getTaille(){
        return this.taille;
    }
    public void setTaille(BigDecimal taille){
        this.taille = taille;
    }


    public TypeAttachementDto getTypeAttachement(){
        return this.typeAttachement;
    }

    public void setTypeAttachement(TypeAttachementDto typeAttachement){
        this.typeAttachement = typeAttachement;
    }
    public StageDto getStage(){
        return this.stage;
    }

    public void setStage(StageDto stage){
        this.stage = stage;
    }






}
