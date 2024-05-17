package  ma.zs.stocky.ws.dto.stage;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.stocky.ws.dto.societe.SocieteDto;
import ma.zs.stocky.ws.dto.etudiant.EtudiantDto;
import ma.zs.stocky.ws.dto.piecejointe.AttachementDto;
import ma.zs.stocky.ws.dto.encadrant.EncadrantInterneDto;
import ma.zs.stocky.ws.dto.jury.JuryDto;
import ma.zs.stocky.ws.dto.departement.FiliereDto;
import ma.zs.stocky.ws.dto.encadrant.EncadrantExterneDto;
import ma.zs.stocky.ws.dto.departement.DomaineDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class StageDto  extends AuditBaseDto {

    private String sujet  ;
    private String description  ;
    private String dateDebut ;
    private String dateFin ;
    private String lieu  ;
    private Integer dureeSemaines  = 0 ;
    private BigDecimal note  ;
    private String dateSoutenance ;

    private DomaineDto domaine ;
    private SocieteDto societe ;
    private JuryDto jury ;
    private FiliereDto filiere ;
    private TypeStageDto typeStage ;

    private List<StageEtudiantDto> stageEtudiants ;
    private List<StageEncadrantInterneDto> stageEncadrantInternes ;
    private List<StageEncadrantExterneDto> stageEncadrantExternes ;
    private List<StageAttachementDto> stageAttachements ;


    public StageDto(){
        super();
    }



    @Log
    public String getSujet(){
        return this.sujet;
    }
    public void setSujet(String sujet){
        this.sujet = sujet;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(String dateDebut){
        this.dateDebut = dateDebut;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(String dateFin){
        this.dateFin = dateFin;
    }

    @Log
    public String getLieu(){
        return this.lieu;
    }
    public void setLieu(String lieu){
        this.lieu = lieu;
    }

    @Log
    public Integer getDureeSemaines(){
        return this.dureeSemaines;
    }
    public void setDureeSemaines(Integer dureeSemaines){
        this.dureeSemaines = dureeSemaines;
    }

    @Log
    public BigDecimal getNote(){
        return this.note;
    }
    public void setNote(BigDecimal note){
        this.note = note;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateSoutenance(){
        return this.dateSoutenance;
    }
    public void setDateSoutenance(String dateSoutenance){
        this.dateSoutenance = dateSoutenance;
    }


    public DomaineDto getDomaine(){
        return this.domaine;
    }

    public void setDomaine(DomaineDto domaine){
        this.domaine = domaine;
    }
    public SocieteDto getSociete(){
        return this.societe;
    }

    public void setSociete(SocieteDto societe){
        this.societe = societe;
    }
    public JuryDto getJury(){
        return this.jury;
    }

    public void setJury(JuryDto jury){
        this.jury = jury;
    }
    public FiliereDto getFiliere(){
        return this.filiere;
    }

    public void setFiliere(FiliereDto filiere){
        this.filiere = filiere;
    }
    public TypeStageDto getTypeStage(){
        return this.typeStage;
    }

    public void setTypeStage(TypeStageDto typeStage){
        this.typeStage = typeStage;
    }



    public List<StageEtudiantDto> getStageEtudiants(){
        return this.stageEtudiants;
    }

    public void setStageEtudiants(List<StageEtudiantDto> stageEtudiants){
        this.stageEtudiants = stageEtudiants;
    }
    public List<StageEncadrantInterneDto> getStageEncadrantInternes(){
        return this.stageEncadrantInternes;
    }

    public void setStageEncadrantInternes(List<StageEncadrantInterneDto> stageEncadrantInternes){
        this.stageEncadrantInternes = stageEncadrantInternes;
    }
    public List<StageEncadrantExterneDto> getStageEncadrantExternes(){
        return this.stageEncadrantExternes;
    }

    public void setStageEncadrantExternes(List<StageEncadrantExterneDto> stageEncadrantExternes){
        this.stageEncadrantExternes = stageEncadrantExternes;
    }
    public List<StageAttachementDto> getStageAttachements(){
        return this.stageAttachements;
    }

    public void setStageAttachements(List<StageAttachementDto> stageAttachements){
        this.stageAttachements = stageAttachements;
    }



}
