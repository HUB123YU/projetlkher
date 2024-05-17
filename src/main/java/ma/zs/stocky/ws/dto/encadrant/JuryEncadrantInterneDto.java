package  ma.zs.stocky.ws.dto.encadrant;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.stocky.ws.dto.jury.JuryDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class JuryEncadrantInterneDto  extends AuditBaseDto {


    private EncadrantInterneDto encadrantInterne ;
    private JuryDto jury ;



    public JuryEncadrantInterneDto(){
        super();
    }




    public EncadrantInterneDto getEncadrantInterne(){
        return this.encadrantInterne;
    }

    public void setEncadrantInterne(EncadrantInterneDto encadrantInterne){
        this.encadrantInterne = encadrantInterne;
    }
    public JuryDto getJury(){
        return this.jury;
    }

    public void setJury(JuryDto jury){
        this.jury = jury;
    }






}
