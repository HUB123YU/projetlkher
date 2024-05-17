package  ma.zs.stocky.ws.converter.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.stage.TypeStage;
import ma.zs.stocky.ws.dto.stage.TypeStageDto;

@Component
public class TypeStageConverter {


    public  TypeStageConverter() {
    }


    public TypeStage toItem(TypeStageDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeStage item = new TypeStage();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public TypeStageDto toDto(TypeStage item) {
        if (item == null) {
            return null;
        } else {
            TypeStageDto dto = new TypeStageDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<TypeStage> toItem(List<TypeStageDto> dtos) {
        List<TypeStage> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeStageDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeStageDto> toDto(List<TypeStage> items) {
        List<TypeStageDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeStage item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeStageDto dto, TypeStage t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeStage> copy(List<TypeStageDto> dtos) {
        List<TypeStage> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeStageDto dto : dtos) {
                TypeStage instance = new TypeStage();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
