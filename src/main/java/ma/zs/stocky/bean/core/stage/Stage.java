package ma.zs.stocky.bean.core.stage;

import java.util.Objects;
import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.stocky.bean.core.societe.Societe;
import ma.zs.stocky.bean.core.etudiant.Etudiant;
import ma.zs.stocky.bean.core.piecejointe.Attachement;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.bean.core.jury.Jury;
import ma.zs.stocky.bean.core.departement.Filiere;
import ma.zs.stocky.bean.core.encadrant.EncadrantExterne;
import ma.zs.stocky.bean.core.departement.Domaine;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "stage")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="stage_seq",sequenceName="stage_seq",allocationSize=1, initialValue = 1)
public class Stage  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String sujet;

    @Column(length = 500)
    private String description;

    private LocalDateTime dateDebut ;

    private LocalDateTime dateFin ;

    @Column(length = 500)
    private String lieu;

    private Integer dureeSemaines = 0;

    private BigDecimal note = BigDecimal.ZERO;

    private LocalDateTime dateSoutenance ;

    private Domaine domaine ;
    private Societe societe ;
    private Jury jury ;
    private Filiere filiere ;
    private TypeStage typeStage ;

    private List<StageEtudiant> stageEtudiants ;
    private List<StageEncadrantInterne> stageEncadrantInternes ;
    private List<StageEncadrantExterne> stageEncadrantExternes ;
    private List<StageAttachement> stageAttachements ;

    public Stage(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="stage_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getSujet(){
        return this.sujet;
    }
    public void setSujet(String sujet){
        this.sujet = sujet;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domaine")
    public Domaine getDomaine(){
        return this.domaine;
    }
    public void setDomaine(Domaine domaine){
        this.domaine = domaine;
    }
    public String getLieu(){
        return this.lieu;
    }
    public void setLieu(String lieu){
        this.lieu = lieu;
    }
    public Integer getDureeSemaines(){
        return this.dureeSemaines;
    }
    public void setDureeSemaines(Integer dureeSemaines){
        this.dureeSemaines = dureeSemaines;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "societe")
    public Societe getSociete(){
        return this.societe;
    }
    public void setSociete(Societe societe){
        this.societe = societe;
    }
    @OneToMany(mappedBy = "stage")
    public List<StageEtudiant> getStageEtudiants(){
        return this.stageEtudiants;
    }

    public void setStageEtudiants(List<StageEtudiant> stageEtudiants){
        this.stageEtudiants = stageEtudiants;
    }
    @OneToMany(mappedBy = "stage")
    public List<StageEncadrantInterne> getStageEncadrantInternes(){
        return this.stageEncadrantInternes;
    }

    public void setStageEncadrantInternes(List<StageEncadrantInterne> stageEncadrantInternes){
        this.stageEncadrantInternes = stageEncadrantInternes;
    }
    @OneToMany(mappedBy = "stage")
    public List<StageEncadrantExterne> getStageEncadrantExternes(){
        return this.stageEncadrantExternes;
    }

    public void setStageEncadrantExternes(List<StageEncadrantExterne> stageEncadrantExternes){
        this.stageEncadrantExternes = stageEncadrantExternes;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jury")
    public Jury getJury(){
        return this.jury;
    }
    public void setJury(Jury jury){
        this.jury = jury;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filiere")
    public Filiere getFiliere(){
        return this.filiere;
    }
    public void setFiliere(Filiere filiere){
        this.filiere = filiere;
    }
    public BigDecimal getNote(){
        return this.note;
    }
    public void setNote(BigDecimal note){
        this.note = note;
    }
    public LocalDateTime getDateSoutenance(){
        return this.dateSoutenance;
    }
    public void setDateSoutenance(LocalDateTime dateSoutenance){
        this.dateSoutenance = dateSoutenance;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_stage")
    public TypeStage getTypeStage(){
        return this.typeStage;
    }
    public void setTypeStage(TypeStage typeStage){
        this.typeStage = typeStage;
    }
    @OneToMany(mappedBy = "stage")
    public List<StageAttachement> getStageAttachements(){
        return this.stageAttachements;
    }

    public void setStageAttachements(List<StageAttachement> stageAttachements){
        this.stageAttachements = stageAttachements;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stage stage = (Stage) o;
        return id != null && id.equals(stage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

