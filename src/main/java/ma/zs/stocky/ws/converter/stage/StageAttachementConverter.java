package  ma.zs.stocky.ws.converter.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.stage.StageConverter;
import ma.zs.stocky.ws.converter.piecejointe.AttachementConverter;

import ma.zs.stocky.bean.core.stage.Stage;


import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.stage.StageAttachement;
import ma.zs.stocky.ws.dto.stage.StageAttachementDto;

@Component
public class StageAttachementConverter {

    @Autowired
    private StageConverter stageConverter ;
    @Autowired
    private AttachementConverter attachementConverter ;
    private boolean stage;
    private boolean attachement;

    public  StageAttachementConverter() {
        initObject(true);
    }


    public StageAttachement toItem(StageAttachementDto dto) {
        if (dto == null) {
            return null;
        } else {
        StageAttachement item = new StageAttachement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getSize()))
                item.setSize(dto.getSize());
            if(dto.getStage() != null && dto.getStage().getId() != null){
                item.setStage(new Stage());
                item.getStage().setId(dto.getStage().getId());
                item.getStage().setId(dto.getStage().getId());
            }

            if(this.attachement && dto.getAttachement()!=null)
                item.setAttachement(attachementConverter.toItem(dto.getAttachement())) ;




        return item;
        }
    }


    public StageAttachementDto toDto(StageAttachement item) {
        if (item == null) {
            return null;
        } else {
            StageAttachementDto dto = new StageAttachementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getSize()))
                dto.setSize(item.getSize());
            if(this.stage && item.getStage()!=null) {
                dto.setStage(stageConverter.toDto(item.getStage())) ;

            }
            if(this.attachement && item.getAttachement()!=null) {
                dto.setAttachement(attachementConverter.toDto(item.getAttachement())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.stage = value;
        this.attachement = value;
    }
	
    public List<StageAttachement> toItem(List<StageAttachementDto> dtos) {
        List<StageAttachement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StageAttachementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StageAttachementDto> toDto(List<StageAttachement> items) {
        List<StageAttachementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (StageAttachement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StageAttachementDto dto, StageAttachement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getStage() != null)
        stageConverter.copy(dto.getStage(), t.getStage());
        if (dto.getAttachement() != null)
        attachementConverter.copy(dto.getAttachement(), t.getAttachement());
    }

    public List<StageAttachement> copy(List<StageAttachementDto> dtos) {
        List<StageAttachement> result = new ArrayList<>();
        if (dtos != null) {
            for (StageAttachementDto dto : dtos) {
                StageAttachement instance = new StageAttachement();
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
    public AttachementConverter getAttachementConverter(){
        return this.attachementConverter;
    }
    public void setAttachementConverter(AttachementConverter attachementConverter ){
        this.attachementConverter = attachementConverter;
    }
    public boolean  isStage(){
        return this.stage;
    }
    public void  setStage(boolean stage){
        this.stage = stage;
    }
    public boolean  isAttachement(){
        return this.attachement;
    }
    public void  setAttachement(boolean attachement){
        this.attachement = attachement;
    }
}
