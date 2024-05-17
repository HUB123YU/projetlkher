package  ma.zs.stocky.ws.dto.stage;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.stocky.ws.dto.encadrant.EncadrantInterneDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class StageEncadrantInterneDto  extends AuditBaseDto {


    private StageDto stage ;
    private EncadrantInterneDto encadrantInterne ;



    public StageEncadrantInterneDto(){
        super();
    }




    public StageDto getStage(){
        return this.stage;
    }

    public void setStage(StageDto stage){
        this.stage = stage;
    }
    public EncadrantInterneDto getEncadrantInterne(){
        return this.encadrantInterne;
    }

    public void setEncadrantInterne(EncadrantInterneDto encadrantInterne){
        this.encadrantInterne = encadrantInterne;
    }






}
