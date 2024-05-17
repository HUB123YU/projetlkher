package  ma.zs.stocky.dao.criteria.core.stage;


import ma.zs.stocky.dao.criteria.core.encadrant.EncadrantExterneCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class StageEncadrantExterneCriteria extends  BaseCriteria  {


    private StageCriteria stage ;
    private List<StageCriteria> stages ;
    private EncadrantExterneCriteria encadrantExterne ;
    private List<EncadrantExterneCriteria> encadrantExternes ;


    public StageEncadrantExterneCriteria(){}


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
    public EncadrantExterneCriteria getEncadrantExterne(){
        return this.encadrantExterne;
    }

    public void setEncadrantExterne(EncadrantExterneCriteria encadrantExterne){
        this.encadrantExterne = encadrantExterne;
    }
    public List<EncadrantExterneCriteria> getEncadrantExternes(){
        return this.encadrantExternes;
    }

    public void setEncadrantExternes(List<EncadrantExterneCriteria> encadrantExternes){
        this.encadrantExternes = encadrantExternes;
    }
}
