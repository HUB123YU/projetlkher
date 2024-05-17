package  ma.zs.stocky.ws.converter.encadrant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.societe.SocieteConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.encadrant.EncadrantExterne;
import ma.zs.stocky.ws.dto.encadrant.EncadrantExterneDto;

@Component
public class EncadrantExterneConverter {

    @Autowired
    private SocieteConverter societeConverter ;
    private boolean societe;

    public  EncadrantExterneConverter() {
        initObject(true);
    }


    public EncadrantExterne toItem(EncadrantExterneDto dto) {
        if (dto == null) {
            return null;
        } else {
        EncadrantExterne item = new EncadrantExterne();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getPrenom()))
                item.setPrenom(dto.getPrenom());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getTelephone()))
                item.setTelephone(dto.getTelephone());
            if(StringUtil.isNotEmpty(dto.getBiographie()))
                item.setBiographie(dto.getBiographie());
            if(this.societe && dto.getSociete()!=null)
                item.setSociete(societeConverter.toItem(dto.getSociete())) ;




        return item;
        }
    }


    public EncadrantExterneDto toDto(EncadrantExterne item) {
        if (item == null) {
            return null;
        } else {
            EncadrantExterneDto dto = new EncadrantExterneDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getPrenom()))
                dto.setPrenom(item.getPrenom());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getTelephone()))
                dto.setTelephone(item.getTelephone());
            if(StringUtil.isNotEmpty(item.getBiographie()))
                dto.setBiographie(item.getBiographie());
            if(this.societe && item.getSociete()!=null) {
                dto.setSociete(societeConverter.toDto(item.getSociete())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.societe = value;
    }
	
    public List<EncadrantExterne> toItem(List<EncadrantExterneDto> dtos) {
        List<EncadrantExterne> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EncadrantExterneDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EncadrantExterneDto> toDto(List<EncadrantExterne> items) {
        List<EncadrantExterneDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EncadrantExterne item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EncadrantExterneDto dto, EncadrantExterne t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getSociete() != null)
        societeConverter.copy(dto.getSociete(), t.getSociete());
    }

    public List<EncadrantExterne> copy(List<EncadrantExterneDto> dtos) {
        List<EncadrantExterne> result = new ArrayList<>();
        if (dtos != null) {
            for (EncadrantExterneDto dto : dtos) {
                EncadrantExterne instance = new EncadrantExterne();
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
    public boolean  isSociete(){
        return this.societe;
    }
    public void  setSociete(boolean societe){
        this.societe = societe;
    }
}
