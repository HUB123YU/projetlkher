package  ma.zs.stocky.ws.converter.piecejointe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.piecejointe.TypeAttachement;
import ma.zs.stocky.ws.dto.piecejointe.TypeAttachementDto;

@Component
public class TypeAttachementConverter {


    public  TypeAttachementConverter() {
    }


    public TypeAttachement toItem(TypeAttachementDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeAttachement item = new TypeAttachement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public TypeAttachementDto toDto(TypeAttachement item) {
        if (item == null) {
            return null;
        } else {
            TypeAttachementDto dto = new TypeAttachementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<TypeAttachement> toItem(List<TypeAttachementDto> dtos) {
        List<TypeAttachement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeAttachementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeAttachementDto> toDto(List<TypeAttachement> items) {
        List<TypeAttachementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeAttachement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeAttachementDto dto, TypeAttachement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeAttachement> copy(List<TypeAttachementDto> dtos) {
        List<TypeAttachement> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeAttachementDto dto : dtos) {
                TypeAttachement instance = new TypeAttachement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
