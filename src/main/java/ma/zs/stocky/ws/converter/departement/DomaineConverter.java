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
import ma.zs.stocky.bean.core.departement.Domaine;
import ma.zs.stocky.ws.dto.departement.DomaineDto;

@Component
public class DomaineConverter {


    public  DomaineConverter() {
    }


    public Domaine toItem(DomaineDto dto) {
        if (dto == null) {
            return null;
        } else {
        Domaine item = new Domaine();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());



        return item;
        }
    }


    public DomaineDto toDto(Domaine item) {
        if (item == null) {
            return null;
        } else {
            DomaineDto dto = new DomaineDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());


        return dto;
        }
    }


	
    public List<Domaine> toItem(List<DomaineDto> dtos) {
        List<Domaine> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DomaineDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DomaineDto> toDto(List<Domaine> items) {
        List<DomaineDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Domaine item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DomaineDto dto, Domaine t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Domaine> copy(List<DomaineDto> dtos) {
        List<Domaine> result = new ArrayList<>();
        if (dtos != null) {
            for (DomaineDto dto : dtos) {
                Domaine instance = new Domaine();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
