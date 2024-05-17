package  ma.zs.stocky.ws.converter.jury;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.stocky.zynerator.util.ListUtil;

import ma.zs.stocky.ws.converter.encadrant.EncadrantInterneConverter;
import ma.zs.stocky.ws.converter.encadrant.JuryEncadrantInterneConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.jury.Jury;
import ma.zs.stocky.ws.dto.jury.JuryDto;

@Component
public class JuryConverter {

    @Autowired
    private EncadrantInterneConverter encadrantInterneConverter ;
    @Autowired
    private JuryEncadrantInterneConverter juryEncadrantInterneConverter ;
    private boolean juryEncadrantInternes;

    public  JuryConverter() {
        initList(true);
    }


    public Jury toItem(JuryDto dto) {
        if (dto == null) {
            return null;
        } else {
        Jury item = new Jury();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getNombreMembres()))
                item.setNombreMembres(dto.getNombreMembres());

            if(this.juryEncadrantInternes && ListUtil.isNotEmpty(dto.getJuryEncadrantInternes()))
                item.setJuryEncadrantInternes(juryEncadrantInterneConverter.toItem(dto.getJuryEncadrantInternes()));


        return item;
        }
    }


    public JuryDto toDto(Jury item) {
        if (item == null) {
            return null;
        } else {
            JuryDto dto = new JuryDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getNombreMembres()))
                dto.setNombreMembres(item.getNombreMembres());
        if(this.juryEncadrantInternes && ListUtil.isNotEmpty(item.getJuryEncadrantInternes())){
            juryEncadrantInterneConverter.init(true);
            juryEncadrantInterneConverter.setJury(false);
            dto.setJuryEncadrantInternes(juryEncadrantInterneConverter.toDto(item.getJuryEncadrantInternes()));
            juryEncadrantInterneConverter.setJury(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.juryEncadrantInternes = value;
    }
	
    public List<Jury> toItem(List<JuryDto> dtos) {
        List<Jury> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (JuryDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<JuryDto> toDto(List<Jury> items) {
        List<JuryDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Jury item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(JuryDto dto, Jury t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getJuryEncadrantInternes() != null)
            t.setJuryEncadrantInternes(juryEncadrantInterneConverter.copy(dto.getJuryEncadrantInternes()));
    }

    public List<Jury> copy(List<JuryDto> dtos) {
        List<Jury> result = new ArrayList<>();
        if (dtos != null) {
            for (JuryDto dto : dtos) {
                Jury instance = new Jury();
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
    public JuryEncadrantInterneConverter getJuryEncadrantInterneConverter(){
        return this.juryEncadrantInterneConverter;
    }
    public void setJuryEncadrantInterneConverter(JuryEncadrantInterneConverter juryEncadrantInterneConverter ){
        this.juryEncadrantInterneConverter = juryEncadrantInterneConverter;
    }
    public boolean  isJuryEncadrantInternes(){
        return this.juryEncadrantInternes ;
    }
    public void  setJuryEncadrantInternes(boolean juryEncadrantInternes ){
        this.juryEncadrantInternes  = juryEncadrantInternes ;
    }
}
