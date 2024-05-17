package  ma.zs.stocky.ws.dto.stage;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.stocky.ws.dto.encadrant.EncadrantExterneDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class StageEncadrantExterneDto  extends AuditBaseDto {


    private StageDto stage ;
    private EncadrantExterneDto encadrantExterne ;



    public StageEncadrantExterneDto(){
        super();
    }




    public StageDto getStage(){
        return this.stage;
    }

    public void setStage(StageDto stage){
        this.stage = stage;
    }
    public EncadrantExterneDto getEncadrantExterne(){
        return this.encadrantExterne;
    }

    public void setEncadrantExterne(EncadrantExterneDto encadrantExterne){
        this.encadrantExterne = encadrantExterne;
    }






}
