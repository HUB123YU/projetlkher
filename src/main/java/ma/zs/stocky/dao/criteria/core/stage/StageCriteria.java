package  ma.zs.stocky.dao.criteria.core.stage;


import ma.zs.stocky.dao.criteria.core.societe.SocieteCriteria;
import ma.zs.stocky.dao.criteria.core.jury.JuryCriteria;
import ma.zs.stocky.dao.criteria.core.departement.FiliereCriteria;
import ma.zs.stocky.dao.criteria.core.departement.DomaineCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class StageCriteria extends  BaseCriteria  {

    private String sujet;
    private String sujetLike;
    private String description;
    private String descriptionLike;
    private LocalDateTime dateDebut;
    private LocalDateTime dateDebutFrom;
    private LocalDateTime dateDebutTo;
    private LocalDateTime dateFin;
    private LocalDateTime dateFinFrom;
    private LocalDateTime dateFinTo;
    private String lieu;
    private String lieuLike;
    private String dureeSemaines;
    private String dureeSemainesMin;
    private String dureeSemainesMax;
    private String note;
    private String noteMin;
    private String noteMax;
    private LocalDateTime dateSoutenance;
    private LocalDateTime dateSoutenanceFrom;
    private LocalDateTime dateSoutenanceTo;

    private DomaineCriteria domaine ;
    private List<DomaineCriteria> domaines ;
    private SocieteCriteria societe ;
    private List<SocieteCriteria> societes ;
    private JuryCriteria jury ;
    private List<JuryCriteria> jurys ;
    private FiliereCriteria filiere ;
    private List<FiliereCriteria> filieres ;
    private TypeStageCriteria typeStage ;
    private List<TypeStageCriteria> typeStages ;


    public StageCriteria(){}

    public String getSujet(){
        return this.sujet;
    }
    public void setSujet(String sujet){
        this.sujet = sujet;
    }
    public String getSujetLike(){
        return this.sujetLike;
    }
    public void setSujetLike(String sujetLike){
        this.sujetLike = sujetLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateDebutFrom(){
        return this.dateDebutFrom;
    }
    public void setDateDebutFrom(LocalDateTime dateDebutFrom){
        this.dateDebutFrom = dateDebutFrom;
    }
    public LocalDateTime getDateDebutTo(){
        return this.dateDebutTo;
    }
    public void setDateDebutTo(LocalDateTime dateDebutTo){
        this.dateDebutTo = dateDebutTo;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    public LocalDateTime getDateFinFrom(){
        return this.dateFinFrom;
    }
    public void setDateFinFrom(LocalDateTime dateFinFrom){
        this.dateFinFrom = dateFinFrom;
    }
    public LocalDateTime getDateFinTo(){
        return this.dateFinTo;
    }
    public void setDateFinTo(LocalDateTime dateFinTo){
        this.dateFinTo = dateFinTo;
    }
    public String getLieu(){
        return this.lieu;
    }
    public void setLieu(String lieu){
        this.lieu = lieu;
    }
    public String getLieuLike(){
        return this.lieuLike;
    }
    public void setLieuLike(String lieuLike){
        this.lieuLike = lieuLike;
    }

    public String getDureeSemaines(){
        return this.dureeSemaines;
    }
    public void setDureeSemaines(String dureeSemaines){
        this.dureeSemaines = dureeSemaines;
    }   
    public String getDureeSemainesMin(){
        return this.dureeSemainesMin;
    }
    public void setDureeSemainesMin(String dureeSemainesMin){
        this.dureeSemainesMin = dureeSemainesMin;
    }
    public String getDureeSemainesMax(){
        return this.dureeSemainesMax;
    }
    public void setDureeSemainesMax(String dureeSemainesMax){
        this.dureeSemainesMax = dureeSemainesMax;
    }
      
    public String getNote(){
        return this.note;
    }
    public void setNote(String note){
        this.note = note;
    }   
    public String getNoteMin(){
        return this.noteMin;
    }
    public void setNoteMin(String noteMin){
        this.noteMin = noteMin;
    }
    public String getNoteMax(){
        return this.noteMax;
    }
    public void setNoteMax(String noteMax){
        this.noteMax = noteMax;
    }
      
    public LocalDateTime getDateSoutenance(){
        return this.dateSoutenance;
    }
    public void setDateSoutenance(LocalDateTime dateSoutenance){
        this.dateSoutenance = dateSoutenance;
    }
    public LocalDateTime getDateSoutenanceFrom(){
        return this.dateSoutenanceFrom;
    }
    public void setDateSoutenanceFrom(LocalDateTime dateSoutenanceFrom){
        this.dateSoutenanceFrom = dateSoutenanceFrom;
    }
    public LocalDateTime getDateSoutenanceTo(){
        return this.dateSoutenanceTo;
    }
    public void setDateSoutenanceTo(LocalDateTime dateSoutenanceTo){
        this.dateSoutenanceTo = dateSoutenanceTo;
    }

    public DomaineCriteria getDomaine(){
        return this.domaine;
    }

    public void setDomaine(DomaineCriteria domaine){
        this.domaine = domaine;
    }
    public List<DomaineCriteria> getDomaines(){
        return this.domaines;
    }

    public void setDomaines(List<DomaineCriteria> domaines){
        this.domaines = domaines;
    }
    public SocieteCriteria getSociete(){
        return this.societe;
    }

    public void setSociete(SocieteCriteria societe){
        this.societe = societe;
    }
    public List<SocieteCriteria> getSocietes(){
        return this.societes;
    }

    public void setSocietes(List<SocieteCriteria> societes){
        this.societes = societes;
    }
    public JuryCriteria getJury(){
        return this.jury;
    }

    public void setJury(JuryCriteria jury){
        this.jury = jury;
    }
    public List<JuryCriteria> getJurys(){
        return this.jurys;
    }

    public void setJurys(List<JuryCriteria> jurys){
        this.jurys = jurys;
    }
    public FiliereCriteria getFiliere(){
        return this.filiere;
    }

    public void setFiliere(FiliereCriteria filiere){
        this.filiere = filiere;
    }
    public List<FiliereCriteria> getFilieres(){
        return this.filieres;
    }

    public void setFilieres(List<FiliereCriteria> filieres){
        this.filieres = filieres;
    }
    public TypeStageCriteria getTypeStage(){
        return this.typeStage;
    }

    public void setTypeStage(TypeStageCriteria typeStage){
        this.typeStage = typeStage;
    }
    public List<TypeStageCriteria> getTypeStages(){
        return this.typeStages;
    }

    public void setTypeStages(List<TypeStageCriteria> typeStages){
        this.typeStages = typeStages;
    }
}
