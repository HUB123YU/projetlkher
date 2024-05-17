package  ma.zs.stocky.dao.criteria.core.stage;


import ma.zs.stocky.dao.criteria.core.encadrant.EncadrantInterneCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class StageEncadrantInterneCriteria extends  BaseCriteria  {


    private StageCriteria stage ;
    private List<StageCriteria> stages ;
    private EncadrantInterneCriteria encadrantInterne ;
    private List<EncadrantInterneCriteria> encadrantInternes ;


    public StageEncadrantInterneCriteria(){}


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
    public EncadrantInterneCriteria getEncadrantInterne(){
        return this.encadrantInterne;
    }

    public void setEncadrantInterne(EncadrantInterneCriteria encadrantInterne){
        this.encadrantInterne = encadrantInterne;
    }
    public List<EncadrantInterneCriteria> getEncadrantInternes(){
        return this.encadrantInternes;
    }

    public void setEncadrantInternes(List<EncadrantInterneCriteria> encadrantInternes){
        this.encadrantInternes = encadrantInternes;
    }
}
