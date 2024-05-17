package  ma.zs.stocky.ws.dto.jury;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.zs.stocky.ws.dto.encadrant.EncadrantInterneDto;
import ma.zs.stocky.ws.dto.encadrant.JuryEncadrantInterneDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class JuryDto  extends AuditBaseDto {

    private String ref  ;
    private Integer nombreMembres  = 0 ;


    private List<JuryEncadrantInterneDto> juryEncadrantInternes ;


    public JuryDto(){
        super();
    }



    @Log
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }

    @Log
    public Integer getNombreMembres(){
        return this.nombreMembres;
    }
    public void setNombreMembres(Integer nombreMembres){
        this.nombreMembres = nombreMembres;
    }





    public List<JuryEncadrantInterneDto> getJuryEncadrantInternes(){
        return this.juryEncadrantInternes;
    }

    public void setJuryEncadrantInternes(List<JuryEncadrantInterneDto> juryEncadrantInternes){
        this.juryEncadrantInternes = juryEncadrantInternes;
    }



}
