package  ma.zs.stocky.ws.converter.encadrant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.encadrant.EncadrantInterneConverter;
import ma.zs.stocky.ws.converter.jury.JuryConverter;

import ma.zs.stocky.bean.core.jury.Jury;


import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.encadrant.JuryEncadrantInterne;
import ma.zs.stocky.ws.dto.encadrant.JuryEncadrantInterneDto;

@Component
public class JuryEncadrantInterneConverter {

    @Autowired
    private EncadrantInterneConverter encadrantInterneConverter ;
    @Autowired
    private JuryConverter juryConverter ;
    private boolean encadrantInterne;
    private boolean jury;

    public  JuryEncadrantInterneConverter() {
        initObject(true);
    }


    public JuryEncadrantInterne toItem(JuryEncadrantInterneDto dto) {
        if (dto == null) {
            return null;
        } else {
        JuryEncadrantInterne item = new JuryEncadrantInterne();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(this.encadrantInterne && dto.getEncadrantInterne()!=null)
                item.setEncadrantInterne(encadrantInterneConverter.toItem(dto.getEncadrantInterne())) ;

            if(dto.getJury() != null && dto.getJury().getId() != null){
                item.setJury(new Jury());
                item.getJury().setId(dto.getJury().getId());
                item.getJury().setRef(dto.getJury().getRef());
            }




        return item;
        }
    }


    public JuryEncadrantInterneDto toDto(JuryEncadrantInterne item) {
        if (item == null) {
            return null;
        } else {
            JuryEncadrantInterneDto dto = new JuryEncadrantInterneDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.encadrantInterne && item.getEncadrantInterne()!=null) {
                dto.setEncadrantInterne(encadrantInterneConverter.toDto(item.getEncadrantInterne())) ;

            }
            if(this.jury && item.getJury()!=null) {
                dto.setJury(juryConverter.toDto(item.getJury())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.encadrantInterne = value;
        this.jury = value;
    }
	
    public List<JuryEncadrantInterne> toItem(List<JuryEncadrantInterneDto> dtos) {
        List<JuryEncadrantInterne> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (JuryEncadrantInterneDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<JuryEncadrantInterneDto> toDto(List<JuryEncadrantInterne> items) {
        List<JuryEncadrantInterneDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (JuryEncadrantInterne item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(JuryEncadrantInterneDto dto, JuryEncadrantInterne t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getEncadrantInterne() != null)
        encadrantInterneConverter.copy(dto.getEncadrantInterne(), t.getEncadrantInterne());
        if (dto.getJury() != null)
        juryConverter.copy(dto.getJury(), t.getJury());
    }

    public List<JuryEncadrantInterne> copy(List<JuryEncadrantInterneDto> dtos) {
        List<JuryEncadrantInterne> result = new ArrayList<>();
        if (dtos != null) {
            for (JuryEncadrantInterneDto dto : dtos) {
                JuryEncadrantInterne instance = new JuryEncadrantInterne();
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
    public JuryConverter getJuryConverter(){
        return this.juryConverter;
    }
    public void setJuryConverter(JuryConverter juryConverter ){
        this.juryConverter = juryConverter;
    }
    public boolean  isEncadrantInterne(){
        return this.encadrantInterne;
    }
    public void  setEncadrantInterne(boolean encadrantInterne){
        this.encadrantInterne = encadrantInterne;
    }
    public boolean  isJury(){
        return this.jury;
    }
    public void  setJury(boolean jury){
        this.jury = jury;
    }
}
