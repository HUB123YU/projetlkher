package  ma.zs.stocky.dao.criteria.core.stage;


import ma.zs.stocky.dao.criteria.core.etudiant.EtudiantCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class StageEtudiantCriteria extends  BaseCriteria  {


    private StageCriteria stage ;
    private List<StageCriteria> stages ;
    private EtudiantCriteria etudiant ;
    private List<EtudiantCriteria> etudiants ;


    public StageEtudiantCriteria(){}


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
    public EtudiantCriteria getEtudiant(){
        return this.etudiant;
    }

    public void setEtudiant(EtudiantCriteria etudiant){
        this.etudiant = etudiant;
    }
    public List<EtudiantCriteria> getEtudiants(){
        return this.etudiants;
    }

    public void setEtudiants(List<EtudiantCriteria> etudiants){
        this.etudiants = etudiants;
    }
}
