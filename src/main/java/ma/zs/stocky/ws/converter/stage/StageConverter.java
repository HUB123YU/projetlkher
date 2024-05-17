package  ma.zs.stocky.ws.converter.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.stocky.zynerator.util.ListUtil;

import ma.zs.stocky.ws.converter.societe.SocieteConverter;
import ma.zs.stocky.ws.converter.stage.StageAttachementConverter;
import ma.zs.stocky.ws.converter.etudiant.EtudiantConverter;
import ma.zs.stocky.ws.converter.stage.StageEncadrantExterneConverter;
import ma.zs.stocky.ws.converter.piecejointe.AttachementConverter;
import ma.zs.stocky.ws.converter.encadrant.EncadrantInterneConverter;
import ma.zs.stocky.ws.converter.stage.StageEtudiantConverter;
import ma.zs.stocky.ws.converter.jury.JuryConverter;
import ma.zs.stocky.ws.converter.departement.FiliereConverter;
import ma.zs.stocky.ws.converter.encadrant.EncadrantExterneConverter;
import ma.zs.stocky.ws.converter.stage.StageEncadrantInterneConverter;
import ma.zs.stocky.ws.converter.stage.TypeStageConverter;
import ma.zs.stocky.ws.converter.departement.DomaineConverter;

import ma.zs.stocky.bean.core.jury.Jury;


import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.stage.Stage;
import ma.zs.stocky.ws.dto.stage.StageDto;

@Component
public class StageConverter {

    @Autowired
    private SocieteConverter societeConverter ;
    @Autowired
    private StageAttachementConverter stageAttachementConverter ;
    @Autowired
    private EtudiantConverter etudiantConverter ;
    @Autowired
    private StageEncadrantExterneConverter stageEncadrantExterneConverter ;
    @Autowired
    private AttachementConverter attachementConverter ;
    @Autowired
    private EncadrantInterneConverter encadrantInterneConverter ;
    @Autowired
    private StageEtudiantConverter stageEtudiantConverter ;
    @Autowired
    private JuryConverter juryConverter ;
    @Autowired
    private FiliereConverter filiereConverter ;
    @Autowired
    private EncadrantExterneConverter encadrantExterneConverter ;
    @Autowired
    private StageEncadrantInterneConverter stageEncadrantInterneConverter ;
    @Autowired
    private TypeStageConverter typeStageConverter ;
    @Autowired
    private DomaineConverter domaineConverter ;
    private boolean domaine;
    private boolean societe;
    private boolean jury;
    private boolean filiere;
    private boolean typeStage;
    private boolean stageEtudiants;
    private boolean stageEncadrantInternes;
    private boolean stageEncadrantExternes;
    private boolean stageAttachements;

    public  StageConverter() {
        init(true);
    }


    public Stage toItem(StageDto dto) {
        if (dto == null) {
            return null;
        } else {
        Stage item = new Stage();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getSujet()))
                item.setSujet(dto.getSujet());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getDateDebut()))
                item.setDateDebut(DateUtil.stringEnToDate(dto.getDateDebut()));
            if(StringUtil.isNotEmpty(dto.getDateFin()))
                item.setDateFin(DateUtil.stringEnToDate(dto.getDateFin()));
            if(StringUtil.isNotEmpty(dto.getLieu()))
                item.setLieu(dto.getLieu());
            if(StringUtil.isNotEmpty(dto.getDureeSemaines()))
                item.setDureeSemaines(dto.getDureeSemaines());
            if(StringUtil.isNotEmpty(dto.getNote()))
                item.setNote(dto.getNote());
            if(StringUtil.isNotEmpty(dto.getDateSoutenance()))
                item.setDateSoutenance(DateUtil.stringEnToDate(dto.getDateSoutenance()));
            if(this.domaine && dto.getDomaine()!=null)
                item.setDomaine(domaineConverter.toItem(dto.getDomaine())) ;

            if(this.societe && dto.getSociete()!=null)
                item.setSociete(societeConverter.toItem(dto.getSociete())) ;

            if(dto.getJury() != null && dto.getJury().getId() != null){
                item.setJury(new Jury());
                item.getJury().setId(dto.getJury().getId());
                item.getJury().setRef(dto.getJury().getRef());
            }

            if(this.filiere && dto.getFiliere()!=null)
                item.setFiliere(filiereConverter.toItem(dto.getFiliere())) ;

            if(this.typeStage && dto.getTypeStage()!=null)
                item.setTypeStage(typeStageConverter.toItem(dto.getTypeStage())) ;


            if(this.stageEtudiants && ListUtil.isNotEmpty(dto.getStageEtudiants()))
                item.setStageEtudiants(stageEtudiantConverter.toItem(dto.getStageEtudiants()));
            if(this.stageEncadrantInternes && ListUtil.isNotEmpty(dto.getStageEncadrantInternes()))
                item.setStageEncadrantInternes(stageEncadrantInterneConverter.toItem(dto.getStageEncadrantInternes()));
            if(this.stageEncadrantExternes && ListUtil.isNotEmpty(dto.getStageEncadrantExternes()))
                item.setStageEncadrantExternes(stageEncadrantExterneConverter.toItem(dto.getStageEncadrantExternes()));
            if(this.stageAttachements && ListUtil.isNotEmpty(dto.getStageAttachements()))
                item.setStageAttachements(stageAttachementConverter.toItem(dto.getStageAttachements()));


        return item;
        }
    }


    public StageDto toDto(Stage item) {
        if (item == null) {
            return null;
        } else {
            StageDto dto = new StageDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getSujet()))
                dto.setSujet(item.getSujet());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getDateDebut()!=null)
                dto.setDateDebut(DateUtil.dateTimeToString(item.getDateDebut()));
            if(item.getDateFin()!=null)
                dto.setDateFin(DateUtil.dateTimeToString(item.getDateFin()));
            if(StringUtil.isNotEmpty(item.getLieu()))
                dto.setLieu(item.getLieu());
            if(StringUtil.isNotEmpty(item.getDureeSemaines()))
                dto.setDureeSemaines(item.getDureeSemaines());
            if(StringUtil.isNotEmpty(item.getNote()))
                dto.setNote(item.getNote());
            if(item.getDateSoutenance()!=null)
                dto.setDateSoutenance(DateUtil.dateTimeToString(item.getDateSoutenance()));
            if(this.domaine && item.getDomaine()!=null) {
                dto.setDomaine(domaineConverter.toDto(item.getDomaine())) ;

            }
            if(this.societe && item.getSociete()!=null) {
                dto.setSociete(societeConverter.toDto(item.getSociete())) ;

            }
            if(this.jury && item.getJury()!=null) {
                dto.setJury(juryConverter.toDto(item.getJury())) ;

            }
            if(this.filiere && item.getFiliere()!=null) {
                dto.setFiliere(filiereConverter.toDto(item.getFiliere())) ;

            }
            if(this.typeStage && item.getTypeStage()!=null) {
                dto.setTypeStage(typeStageConverter.toDto(item.getTypeStage())) ;

            }
        if(this.stageEtudiants && ListUtil.isNotEmpty(item.getStageEtudiants())){
            stageEtudiantConverter.init(true);
            stageEtudiantConverter.setStage(false);
            dto.setStageEtudiants(stageEtudiantConverter.toDto(item.getStageEtudiants()));
            stageEtudiantConverter.setStage(true);

        }
        if(this.stageEncadrantInternes && ListUtil.isNotEmpty(item.getStageEncadrantInternes())){
            stageEncadrantInterneConverter.init(true);
            stageEncadrantInterneConverter.setStage(false);
            dto.setStageEncadrantInternes(stageEncadrantInterneConverter.toDto(item.getStageEncadrantInternes()));
            stageEncadrantInterneConverter.setStage(true);

        }
        if(this.stageEncadrantExternes && ListUtil.isNotEmpty(item.getStageEncadrantExternes())){
            stageEncadrantExterneConverter.init(true);
            stageEncadrantExterneConverter.setStage(false);
            dto.setStageEncadrantExternes(stageEncadrantExterneConverter.toDto(item.getStageEncadrantExternes()));
            stageEncadrantExterneConverter.setStage(true);

        }
        if(this.stageAttachements && ListUtil.isNotEmpty(item.getStageAttachements())){
            stageAttachementConverter.init(true);
            stageAttachementConverter.setStage(false);
            dto.setStageAttachements(stageAttachementConverter.toDto(item.getStageAttachements()));
            stageAttachementConverter.setStage(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.stageEtudiants = value;
        this.stageEncadrantInternes = value;
        this.stageEncadrantExternes = value;
        this.stageAttachements = value;
    }
    public void initObject(boolean value) {
        this.domaine = value;
        this.societe = value;
        this.jury = value;
        this.filiere = value;
        this.typeStage = value;
    }
	
    public List<Stage> toItem(List<StageDto> dtos) {
        List<Stage> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StageDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StageDto> toDto(List<Stage> items) {
        List<StageDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Stage item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StageDto dto, Stage t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getDomaine() != null)
        domaineConverter.copy(dto.getDomaine(), t.getDomaine());
        if (dto.getSociete() != null)
        societeConverter.copy(dto.getSociete(), t.getSociete());
        if (dto.getStageEtudiants() != null)
            t.setStageEtudiants(stageEtudiantConverter.copy(dto.getStageEtudiants()));
        if (dto.getStageEncadrantInternes() != null)
            t.setStageEncadrantInternes(stageEncadrantInterneConverter.copy(dto.getStageEncadrantInternes()));
        if (dto.getStageEncadrantExternes() != null)
            t.setStageEncadrantExternes(stageEncadrantExterneConverter.copy(dto.getStageEncadrantExternes()));
        if (dto.getJury() != null)
        juryConverter.copy(dto.getJury(), t.getJury());
        if (dto.getFiliere() != null)
        filiereConverter.copy(dto.getFiliere(), t.getFiliere());
        if (dto.getTypeStage() != null)
        typeStageConverter.copy(dto.getTypeStage(), t.getTypeStage());
        if (dto.getStageAttachements() != null)
            t.setStageAttachements(stageAttachementConverter.copy(dto.getStageAttachements()));
    }

    public List<Stage> copy(List<StageDto> dtos) {
        List<Stage> result = new ArrayList<>();
        if (dtos != null) {
            for (StageDto dto : dtos) {
                Stage instance = new Stage();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public SocieteConverter getSocieteConverter(){
        return this.societeConverter;
    }
    public void setSocieteConverter(SocieteConverter societeConverter ){
        this.societeConverter = societeConverter;
    }
    public StageAttachementConverter getStageAttachementConverter(){
        return this.stageAttachementConverter;
    }
    public void setStageAttachementConverter(StageAttachementConverter stageAttachementConverter ){
        this.stageAttachementConverter = stageAttachementConverter;
    }
    public EtudiantConverter getEtudiantConverter(){
        return this.etudiantConverter;
    }
    public void setEtudiantConverter(EtudiantConverter etudiantConverter ){
        this.etudiantConverter = etudiantConverter;
    }
    public StageEncadrantExterneConverter getStageEncadrantExterneConverter(){
        return this.stageEncadrantExterneConverter;
    }
    public void setStageEncadrantExterneConverter(StageEncadrantExterneConverter stageEncadrantExterneConverter ){
        this.stageEncadrantExterneConverter = stageEncadrantExterneConverter;
    }
    public AttachementConverter getAttachementConverter(){
        return this.attachementConverter;
    }
    public void setAttachementConverter(AttachementConverter attachementConverter ){
        this.attachementConverter = attachementConverter;
    }
    public EncadrantInterneConverter getEncadrantInterneConverter(){
        return this.encadrantInterneConverter;
    }
    public void setEncadrantInterneConverter(EncadrantInterneConverter encadrantInterneConverter ){
        this.encadrantInterneConverter = encadrantInterneConverter;
    }
    public StageEtudiantConverter getStageEtudiantConverter(){
        return this.stageEtudiantConverter;
    }
    public void setStageEtudiantConverter(StageEtudiantConverter stageEtudiantConverter ){
        this.stageEtudiantConverter = stageEtudiantConverter;
    }
    public JuryConverter getJuryConverter(){
        return this.juryConverter;
    }
    public void setJuryConverter(JuryConverter juryConverter ){
        this.juryConverter = juryConverter;
    }
    public FiliereConverter getFiliereConverter(){
        return this.filiereConverter;
    }
    public void setFiliereConverter(FiliereConverter filiereConverter ){
        this.filiereConverter = filiereConverter;
    }
    public EncadrantExterneConverter getEncadrantExterneConverter(){
        return this.encadrantExterneConverter;
    }
    public void setEncadrantExterneConverter(EncadrantExterneConverter encadrantExterneConverter ){
        this.encadrantExterneConverter = encadrantExterneConverter;
    }
    public StageEncadrantInterneConverter getStageEncadrantInterneConverter(){
        return this.stageEncadrantInterneConverter;
    }
    public void setStageEncadrantInterneConverter(StageEncadrantInterneConverter stageEncadrantInterneConverter ){
        this.stageEncadrantInterneConverter = stageEncadrantInterneConverter;
    }
    public TypeStageConverter getTypeStageConverter(){
        return this.typeStageConverter;
    }
    public void setTypeStageConverter(TypeStageConverter typeStageConverter ){
        this.typeStageConverter = typeStageConverter;
    }
    public DomaineConverter getDomaineConverter(){
        return this.domaineConverter;
    }
    public void setDomaineConverter(DomaineConverter domaineConverter ){
        this.domaineConverter = domaineConverter;
    }
    public boolean  isDomaine(){
        return this.domaine;
    }
    public void  setDomaine(boolean domaine){
        this.domaine = domaine;
    }
    public boolean  isSociete(){
        return this.societe;
    }
    public void  setSociete(boolean societe){
        this.societe = societe;
    }
    public boolean  isJury(){
        return this.jury;
    }
    public void  setJury(boolean jury){
        this.jury = jury;
    }
    public boolean  isFiliere(){
        return this.filiere;
    }
    public void  setFiliere(boolean filiere){
        this.filiere = filiere;
    }
    public boolean  isTypeStage(){
        return this.typeStage;
    }
    public void  setTypeStage(boolean typeStage){
        this.typeStage = typeStage;
    }
    public boolean  isStageEtudiants(){
        return this.stageEtudiants ;
    }
    public void  setStageEtudiants(boolean stageEtudiants ){
        this.stageEtudiants  = stageEtudiants ;
    }
    public boolean  isStageEncadrantInternes(){
        return this.stageEncadrantInternes ;
    }
    public void  setStageEncadrantInternes(boolean stageEncadrantInternes ){
        this.stageEncadrantInternes  = stageEncadrantInternes ;
    }
    public boolean  isStageEncadrantExternes(){
        return this.stageEncadrantExternes ;
    }
    public void  setStageEncadrantExternes(boolean stageEncadrantExternes ){
        this.stageEncadrantExternes  = stageEncadrantExternes ;
    }
    public boolean  isStageAttachements(){
        return this.stageAttachements ;
    }
    public void  setStageAttachements(boolean stageAttachements ){
        this.stageAttachements  = stageAttachements ;
    }
}
