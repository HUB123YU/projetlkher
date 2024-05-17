package  ma.zs.stocky.ws.converter.appartenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.appartenance.Pays;
import ma.zs.stocky.ws.dto.appartenance.PaysDto;

@Component
public class PaysConverter {


    public  PaysConverter() {
    }


    public Pays toItem(PaysDto dto) {
        if (dto == null) {
            return null;
        } else {
        Pays item = new Pays();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public PaysDto toDto(Pays item) {
        if (item == null) {
            return null;
        } else {
            PaysDto dto = new PaysDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Pays> toItem(List<PaysDto> dtos) {
        List<Pays> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaysDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaysDto> toDto(List<Pays> items) {
        List<PaysDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Pays item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaysDto dto, Pays t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Pays> copy(List<PaysDto> dtos) {
        List<Pays> result = new ArrayList<>();
        if (dtos != null) {
            for (PaysDto dto : dtos) {
                Pays instance = new Pays();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
