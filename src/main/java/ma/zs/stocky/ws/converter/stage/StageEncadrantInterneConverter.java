package  ma.zs.stocky.ws.converter.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.encadrant.EncadrantInterneConverter;
import ma.zs.stocky.ws.converter.stage.StageConverter;

import ma.zs.stocky.bean.core.stage.Stage;


import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.stage.StageEncadrantInterne;
import ma.zs.stocky.ws.dto.stage.StageEncadrantInterneDto;

@Component
public class StageEncadrantInterneConverter {

    @Autowired
    private EncadrantInterneConverter encadrantInterneConverter ;
    @Autowired
    private StageConverter stageConverter ;
    private boolean stage;
    private boolean encadrantInterne;

    public  StageEncadrantInterneConverter() {
        initObject(true);
    }


    public StageEncadrantInterne toItem(StageEncadrantInterneDto dto) {
        if (dto == null) {
            return null;
        } else {
        StageEncadrantInterne item = new StageEncadrantInterne();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getStage() != null && dto.getStage().getId() != null){
                item.setStage(new Stage());
                item.getStage().setId(dto.getStage().getId());
                item.getStage().setId(dto.getStage().getId());
            }

            if(this.encadrantInterne && dto.getEncadrantInterne()!=null)
                item.setEncadrantInterne(encadrantInterneConverter.toItem(dto.getEncadrantInterne())) ;




        return item;
        }
    }


    public StageEncadrantInterneDto toDto(StageEncadrantInterne item) {
        if (item == null) {
            return null;
        } else {
            StageEncadrantInterneDto dto = new StageEncadrantInterneDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.stage && item.getStage()!=null) {
                dto.setStage(stageConverter.toDto(item.getStage())) ;

            }
            if(this.encadrantInterne && item.getEncadrantInterne()!=null) {
                dto.setEncadrantInterne(encadrantInterneConverter.toDto(item.getEncadrantInterne())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.stage = value;
        this.encadrantInterne = value;
    }
	
    public List<StageEncadrantInterne> toItem(List<StageEncadrantInterneDto> dtos) {
        List<StageEncadrantInterne> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StageEncadrantInterneDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StageEncadrantInterneDto> toDto(List<StageEncadrantInterne> items) {
        List<StageEncadrantInterneDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (StageEncadrantInterne item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StageEncadrantInterneDto dto, StageEncadrantInterne t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getStage() != null)
        stageConverter.copy(dto.getStage(), t.getStage());
        if (dto.getEncadrantInterne() != null)
        encadrantInterneConverter.copy(dto.getEncadrantInterne(), t.getEncadrantInterne());
    }

    public List<StageEncadrantInterne> copy(List<StageEncadrantInterneDto> dtos) {
        List<StageEncadrantInterne> result = new ArrayList<>();
        if (dtos != null) {
            for (StageEncadrantInterneDto dto : dtos) {
                StageEncadrantInterne instance = new StageEncadrantInterne();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EncadrantInterneConverter getEncadrantInterneConverter(){
        return this.encadrantInterneConverter;
    }
    public void setEncadrantInterneConverter(EncadrantInterneConverter encadrantInterneConverter ){
        this.encadrantInterneConverter = encadrantInterneConverter;
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
    public boolean  isEncadrantInterne(){
        return this.encadrantInterne;
    }
    public void  setEncadrantInterne(boolean encadrantInterne){
        this.encadrantInterne = encadrantInterne;
    }
}
