package  ma.zs.stocky.ws.dto.stage;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zs.stocky.ws.dto.piecejointe.AttachementDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class StageAttachementDto  extends AuditBaseDto {

    private BigDecimal size  ;

    private StageDto stage ;
    private AttachementDto attachement ;



    public StageAttachementDto(){
        super();
    }



    @Log
    public BigDecimal getSize(){
        return this.size;
    }
    public void setSize(BigDecimal size){
        this.size = size;
    }


    public StageDto getStage(){
        return this.stage;
    }

    public void setStage(StageDto stage){
        this.stage = stage;
    }
    public AttachementDto getAttachement(){
        return this.attachement;
    }

    public void setAttachement(AttachementDto attachement){
        this.attachement = attachement;
    }






}
