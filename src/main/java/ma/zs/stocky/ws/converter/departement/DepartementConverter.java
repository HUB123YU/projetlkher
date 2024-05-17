package  ma.zs.stocky.ws.converter.departement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.departement.Departement;
import ma.zs.stocky.ws.dto.departement.DepartementDto;

@Component
public class DepartementConverter {


    public  DepartementConverter() {
    }


    public Departement toItem(DepartementDto dto) {
        if (dto == null) {
            return null;
        } else {
        Departement item = new Departement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());



        return item;
        }
    }


    public DepartementDto toDto(Departement item) {
        if (item == null) {
            return null;
        } else {
            DepartementDto dto = new DepartementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());


        return dto;
        }
    }


	
    public List<Departement> toItem(List<DepartementDto> dtos) {
        List<Departement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DepartementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DepartementDto> toDto(List<Departement> items) {
        List<DepartementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Departement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DepartementDto dto, Departement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Departement> copy(List<DepartementDto> dtos) {
        List<Departement> result = new ArrayList<>();
        if (dtos != null) {
            for (DepartementDto dto : dtos) {
                Departement instance = new Departement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
