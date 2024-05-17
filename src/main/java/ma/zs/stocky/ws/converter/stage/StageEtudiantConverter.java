package  ma.zs.stocky.ws.converter.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.etudiant.EtudiantConverter;
import ma.zs.stocky.ws.converter.stage.StageConverter;

import ma.zs.stocky.bean.core.stage.Stage;


import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.stage.StageEtudiant;
import ma.zs.stocky.ws.dto.stage.StageEtudiantDto;

@Component
public class StageEtudiantConverter {

    @Autowired
    private EtudiantConverter etudiantConverter ;
    @Autowired
    private StageConverter stageConverter ;
    private boolean stage;
    private boolean etudiant;

    public  StageEtudiantConverter() {
        initObject(true);
    }


    public StageEtudiant toItem(StageEtudiantDto dto) {
        if (dto == null) {
            return null;
        } else {
        StageEtudiant item = new StageEtudiant();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getStage() != null && dto.getStage().getId() != null){
                item.setStage(new Stage());
                item.getStage().setId(dto.getStage().getId());
                item.getStage().setId(dto.getStage().getId());
            }

            if(this.etudiant && dto.getEtudiant()!=null)
                item.setEtudiant(etudiantConverter.toItem(dto.getEtudiant())) ;




        return item;
        }
    }


    public StageEtudiantDto toDto(StageEtudiant item) {
        if (item == null) {
            return null;
        } else {
            StageEtudiantDto dto = new StageEtudiantDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.stage && item.getStage()!=null) {
                dto.setStage(stageConverter.toDto(item.getStage())) ;

            }
            if(this.etudiant && item.getEtudiant()!=null) {
                dto.setEtudiant(etudiantConverter.toDto(item.getEtudiant())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.stage = value;
        this.etudiant = value;
    }
	
    public List<StageEtudiant> toItem(List<StageEtudiantDto> dtos) {
        List<StageEtudiant> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StageEtudiantDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StageEtudiantDto> toDto(List<StageEtudiant> items) {
        List<StageEtudiantDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (StageEtudiant item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StageEtudiantDto dto, StageEtudiant t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getStage() != null)
        stageConverter.copy(dto.getStage(), t.getStage());
        if (dto.getEtudiant() != null)
        etudiantConverter.copy(dto.getEtudiant(), t.getEtudiant());
    }

    public List<StageEtudiant> copy(List<StageEtudiantDto> dtos) {
        List<StageEtudiant> result = new ArrayList<>();
        if (dtos != null) {
            for (StageEtudiantDto dto : dtos) {
                StageEtudiant instance = new StageEtudiant();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EtudiantConverter getEtudiantConverter(){
        return this.etudiantConverter;
    }
    public void setEtudiantConverter(EtudiantConverter etudiantConverter ){
        this.etudiantConverter = etudiantConverter;
    }
    public StageConverter getStageConverter(){
        return this.stageConverter;
    }
    public void setStageConverter(StageConverter stageConverter ){
        this.stageConverter = stageConverter;
    }
    public boolean  isStage(){
        return this.stage;
    }
    public void  setStage(boolean stage){
        this.stage = stage;
    }
    public boolean  isEtudiant(){
        return this.etudiant;
    }
    public void  setEtudiant(boolean etudiant){
        this.etudiant = etudiant;
    }
}
