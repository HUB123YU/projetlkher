package  ma.zs.stocky.ws.converter.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.stage.StageConverter;
import ma.zs.stocky.ws.converter.encadrant.EncadrantExterneConverter;

import ma.zs.stocky.bean.core.stage.Stage;


import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.stage.StageEncadrantExterne;
import ma.zs.stocky.ws.dto.stage.StageEncadrantExterneDto;

@Component
public class StageEncadrantExterneConverter {

    @Autowired
    private StageConverter stageConverter ;
    @Autowired
    private EncadrantExterneConverter encadrantExterneConverter ;
    private boolean stage;
    private boolean encadrantExterne;

    public  StageEncadrantExterneConverter() {
        initObject(true);
    }


    public StageEncadrantExterne toItem(StageEncadrantExterneDto dto) {
        if (dto == null) {
            return null;
        } else {
        StageEncadrantExterne item = new StageEncadrantExterne();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getStage() != null && dto.getStage().getId() != null){
                item.setStage(new Stage());
                item.getStage().setId(dto.getStage().getId());
                item.getStage().setId(dto.getStage().getId());
            }

            if(this.encadrantExterne && dto.getEncadrantExterne()!=null)
                item.setEncadrantExterne(encadrantExterneConverter.toItem(dto.getEncadrantExterne())) ;




        return item;
        }
    }


    public StageEncadrantExterneDto toDto(StageEncadrantExterne item) {
        if (item == null) {
            return null;
        } else {
            StageEncadrantExterneDto dto = new StageEncadrantExterneDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.stage && item.getStage()!=null) {
                dto.setStage(stageConverter.toDto(item.getStage())) ;

            }
            if(this.encadrantExterne && item.getEncadrantExterne()!=null) {
                dto.setEncadrantExterne(encadrantExterneConverter.toDto(item.getEncadrantExterne())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.stage = value;
        this.encadrantExterne = value;
    }
	
    public List<StageEncadrantExterne> toItem(List<StageEncadrantExterneDto> dtos) {
        List<StageEncadrantExterne> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StageEncadrantExterneDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StageEncadrantExterneDto> toDto(List<StageEncadrantExterne> items) {
        List<StageEncadrantExterneDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (StageEncadrantExterne item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StageEncadrantExterneDto dto, StageEncadrantExterne t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getStage() != null)
        stageConverter.copy(dto.getStage(), t.getStage());
        if (dto.getEncadrantExterne() != null)
        encadrantExterneConverter.copy(dto.getEncadrantExterne(), t.getEncadrantExterne());
    }

    public List<StageEncadrantExterne> copy(List<StageEncadrantExterneDto> dtos) {
        List<StageEncadrantExterne> result = new ArrayList<>();
        if (dtos != null) {
            for (StageEncadrantExterneDto dto : dtos) {
                StageEncadrantExterne instance = new StageEncadrantExterne();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public StageConverter getStageConverter(){
        return this.stageConverter;
    }
    public void setStageConverter(StageConverter stageConverter ){
        this.stageConverter = stageConverter;
    }
    public EncadrantExterneConverter getEncadrantExterneConverter(){
        return this.encadrantExterneConverter;
    }
    public void setEncadrantExterneConverter(EncadrantExterneConverter encadrantExterneConverter ){
        this.encadrantExterneConverter = encadrantExterneConverter;
    }
    public boolean  isStage(){
        return this.stage;
    }
    public void  setStage(boolean stage){
        this.stage = stage;
    }
    public boolean  isEncadrantExterne(){
        return this.encadrantExterne;
    }
    public void  setEncadrantExterne(boolean encadrantExterne){
        this.encadrantExterne = encadrantExterne;
    }
}
