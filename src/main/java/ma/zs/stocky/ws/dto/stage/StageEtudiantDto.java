package  ma.zs.stocky.ws.dto.stage;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.stocky.ws.dto.etudiant.EtudiantDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class StageEtudiantDto  extends AuditBaseDto {


    private StageDto stage ;
    private EtudiantDto etudiant ;



    public StageEtudiantDto(){
        super();
    }




    public StageDto getStage(){
        return this.stage;
    }

    public void setStage(StageDto stage){
        this.stage = stage;
    }
    public EtudiantDto getEtudiant(){
        return this.etudiant;
    }

    public void setEtudiant(EtudiantDto etudiant){
        this.etudiant = etudiant;
    }






}
