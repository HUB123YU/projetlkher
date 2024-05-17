package  ma.zs.stocky.ws.converter.piecejointe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.stage.StageConverter;
import ma.zs.stocky.ws.converter.piecejointe.TypeAttachementConverter;

import ma.zs.stocky.bean.core.stage.Stage;


import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.piecejointe.Attachement;
import ma.zs.stocky.ws.dto.piecejointe.AttachementDto;

@Component
public class AttachementConverter {

    @Autowired
    private StageConverter stageConverter ;
    @Autowired
    private TypeAttachementConverter typeAttachementConverter ;
    private boolean typeAttachement;
    private boolean stage;

    public  AttachementConverter() {
        initObject(true);
    }


    public Attachement toItem(AttachementDto dto) {
        if (dto == null) {
            return null;
        } else {
        Attachement item = new Attachement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getContenu()))
                item.setContenu(dto.getContenu());
            if(StringUtil.isNotEmpty(dto.getTaille()))
                item.setTaille(dto.getTaille());
            if(this.typeAttachement && dto.getTypeAttachement()!=null)
                item.setTypeAttachement(typeAttachementConverter.toItem(dto.getTypeAttachement())) ;

            if(dto.getStage() != null && dto.getStage().getId() != null){
                item.setStage(new Stage());
                item.getStage().setId(dto.getStage().getId());
                item.getStage().setId(dto.getStage().getId());
            }




        return item;
        }
    }


    public AttachementDto toDto(Attachement item) {
        if (item == null) {
            return null;
        } else {
            AttachementDto dto = new AttachementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getContenu()))
                dto.setContenu(item.getContenu());
            if(StringUtil.isNotEmpty(item.getTaille()))
                dto.setTaille(item.getTaille());
            if(this.typeAttachement && item.getTypeAttachement()!=null) {
                dto.setTypeAttachement(typeAttachementConverter.toDto(item.getTypeAttachement())) ;

            }
            if(this.stage && item.getStage()!=null) {
                dto.setStage(stageConverter.toDto(item.getStage())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.typeAttachement = value;
        this.stage = value;
    }
	
    public List<Attachement> toItem(List<AttachementDto> dtos) {
        List<Attachement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AttachementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AttachementDto> toDto(List<Attachement> items) {
        List<AttachementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Attachement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AttachementDto dto, Attachement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getTypeAttachement() != null)
        typeAttachementConverter.copy(dto.getTypeAttachement(), t.getTypeAttachement());
        if (dto.getStage() != null)
        stageConverter.copy(dto.getStage(), t.getStage());
    }

    public List<Attachement> copy(List<AttachementDto> dtos) {
        List<Attachement> result = new ArrayList<>();
        if (dtos != null) {
            for (AttachementDto dto : dtos) {
                Attachement instance = new Attachement();
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
    public TypeAttachementConverter getTypeAttachementConverter(){
        return this.typeAttachementConverter;
    }
    public void setTypeAttachementConverter(TypeAttachementConverter typeAttachementConverter ){
        this.typeAttachementConverter = typeAttachementConverter;
    }
    public boolean  isTypeAttachement(){
        return this.typeAttachement;
    }
    public void  setTypeAttachement(boolean typeAttachement){
        this.typeAttachement = typeAttachement;
    }
    public boolean  isStage(){
        return this.stage;
    }
    public void  setStage(boolean stage){
        this.stage = stage;
    }
}
