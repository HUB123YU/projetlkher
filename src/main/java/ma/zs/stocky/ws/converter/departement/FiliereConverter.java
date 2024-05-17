package  ma.zs.stocky.ws.converter.departement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.departement.DepartementConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.departement.Filiere;
import ma.zs.stocky.ws.dto.departement.FiliereDto;

@Component
public class FiliereConverter {

    @Autowired
    private DepartementConverter departementConverter ;
    private boolean departement;

    public  FiliereConverter() {
        initObject(true);
    }


    public Filiere toItem(FiliereDto dto) {
        if (dto == null) {
            return null;
        } else {
        Filiere item = new Filiere();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(this.departement && dto.getDepartement()!=null)
                item.setDepartement(departementConverter.toItem(dto.getDepartement())) ;




        return item;
        }
    }


    public FiliereDto toDto(Filiere item) {
        if (item == null) {
            return null;
        } else {
            FiliereDto dto = new FiliereDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(this.departement && item.getDepartement()!=null) {
                dto.setDepartement(departementConverter.toDto(item.getDepartement())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.departement = value;
    }
	
    public List<Filiere> toItem(List<FiliereDto> dtos) {
        List<Filiere> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (FiliereDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<FiliereDto> toDto(List<Filiere> items) {
        List<FiliereDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Filiere item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(FiliereDto dto, Filiere t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getDepartement() != null)
        departementConverter.copy(dto.getDepartement(), t.getDepartement());
    }

    public List<Filiere> copy(List<FiliereDto> dtos) {
        List<Filiere> result = new ArrayList<>();
        if (dtos != null) {
            for (FiliereDto dto : dtos) {
                Filiere instance = new Filiere();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public DepartementConverter getDepartementConverter(){
        return this.departementConverter;
    }
    public void setDepartementConverter(DepartementConverter departementConverter ){
        this.departementConverter = departementConverter;
    }
    public boolean  isDepartement(){
        return this.departement;
    }
    public void  setDepartement(boolean departement){
        this.departement = departement;
    }
}
