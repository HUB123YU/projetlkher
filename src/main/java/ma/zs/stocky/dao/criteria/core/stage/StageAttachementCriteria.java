package  ma.zs.stocky.dao.criteria.core.stage;


import ma.zs.stocky.dao.criteria.core.piecejointe.AttachementCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class StageAttachementCriteria extends  BaseCriteria  {

    private String size;
    private String sizeMin;
    private String sizeMax;

    private StageCriteria stage ;
    private List<StageCriteria> stages ;
    private AttachementCriteria attachement ;
    private List<AttachementCriteria> attachements ;


    public StageAttachementCriteria(){}

    public String getSize(){
        return this.size;
    }
    public void setSize(String size){
        this.size = size;
    }   
    public String getSizeMin(){
        return this.sizeMin;
    }
    public void setSizeMin(String sizeMin){
        this.sizeMin = sizeMin;
    }
    public String getSizeMax(){
        return this.sizeMax;
    }
    public void setSizeMax(String sizeMax){
        this.sizeMax = sizeMax;
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
    public AttachementCriteria getAttachement(){
        return this.attachement;
    }

    public void setAttachement(AttachementCriteria attachement){
        this.attachement = attachement;
    }
    public List<AttachementCriteria> getAttachements(){
        return this.attachements;
    }

    public void setAttachements(List<AttachementCriteria> attachements){
        this.attachements = attachements;
    }
}
