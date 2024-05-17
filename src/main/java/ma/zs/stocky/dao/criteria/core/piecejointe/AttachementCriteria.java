package  ma.zs.stocky.dao.criteria.core.piecejointe;


import ma.zs.stocky.dao.criteria.core.stage.StageCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class AttachementCriteria extends  BaseCriteria  {

    private String nom;
    private String nomLike;
    private String contenu;
    private String contenuLike;
    private String taille;
    private String tailleMin;
    private String tailleMax;

    private TypeAttachementCriteria typeAttachement ;
    private List<TypeAttachementCriteria> typeAttachements ;
    private StageCriteria stage ;
    private List<StageCriteria> stages ;


    public AttachementCriteria(){}

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

    public String getContenu(){
        return this.contenu;
    }
    public void setContenu(String contenu){
        this.contenu = contenu;
    }
    public String getContenuLike(){
        return this.contenuLike;
    }
    public void setContenuLike(String contenuLike){
        this.contenuLike = contenuLike;
    }

    public String getTaille(){
        return this.taille;
    }
    public void setTaille(String taille){
        this.taille = taille;
    }   
    public String getTailleMin(){
        return this.tailleMin;
    }
    public void setTailleMin(String tailleMin){
        this.tailleMin = tailleMin;
    }
    public String getTailleMax(){
        return this.tailleMax;
    }
    public void setTailleMax(String tailleMax){
        this.tailleMax = tailleMax;
    }
      

    public TypeAttachementCriteria getTypeAttachement(){
        return this.typeAttachement;
    }

    public void setTypeAttachement(TypeAttachementCriteria typeAttachement){
        this.typeAttachement = typeAttachement;
    }
    public List<TypeAttachementCriteria> getTypeAttachements(){
        return this.typeAttachements;
    }

    public void setTypeAttachements(List<TypeAttachementCriteria> typeAttachements){
        this.typeAttachements = typeAttachements;
    }
    public StageCriteria getStage(){
        return this.stage;
    }

    public void setStage(StageCriteria stage){
        this.stage = stage;
    }
    public List<StageCriteria> getStages(){
        return this.stages;
    }

    public void setStages(List<StageCriteria> stages){
        this.stages = stages;
    }
}
