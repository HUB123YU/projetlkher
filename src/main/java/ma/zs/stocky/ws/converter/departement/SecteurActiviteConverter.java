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
import ma.zs.stocky.bean.core.departement.SecteurActivite;
import ma.zs.stocky.ws.dto.departement.SecteurActiviteDto;

@Component
public class SecteurActiviteConverter {


    public  SecteurActiviteConverter() {
    }


    public SecteurActivite toItem(SecteurActiviteDto dto) {
        if (dto == null) {
            return null;
        } else {
        SecteurActivite item = new SecteurActivite();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public SecteurActiviteDto toDto(SecteurActivite item) {
        if (item == null) {
            return null;
        } else {
            SecteurActiviteDto dto = new SecteurActiviteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<SecteurActivite> toItem(List<SecteurActiviteDto> dtos) {
        List<SecteurActivite> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SecteurActiviteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SecteurActiviteDto> toDto(List<SecteurActivite> items) {
        List<SecteurActiviteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (SecteurActivite item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SecteurActiviteDto dto, SecteurActivite t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<SecteurActivite> copy(List<SecteurActiviteDto> dtos) {
        List<SecteurActivite> result = new ArrayList<>();
        if (dtos != null) {
            for (SecteurActiviteDto dto : dtos) {
                SecteurActivite instance = new SecteurActivite();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
