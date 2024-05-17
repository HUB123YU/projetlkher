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
import ma.zs.stocky.bean.core.appartenance.Genre;
import ma.zs.stocky.ws.dto.appartenance.GenreDto;

@Component
public class GenreConverter {


    public  GenreConverter() {
    }


    public Genre toItem(GenreDto dto) {
        if (dto == null) {
            return null;
        } else {
        Genre item = new Genre();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());



        return item;
        }
    }


    public GenreDto toDto(Genre item) {
        if (item == null) {
            return null;
        } else {
            GenreDto dto = new GenreDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());


        return dto;
        }
    }


	
    public List<Genre> toItem(List<GenreDto> dtos) {
        List<Genre> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (GenreDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<GenreDto> toDto(List<Genre> items) {
        List<GenreDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Genre item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(GenreDto dto, Genre t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Genre> copy(List<GenreDto> dtos) {
        List<Genre> result = new ArrayList<>();
        if (dtos != null) {
            for (GenreDto dto : dtos) {
                Genre instance = new Genre();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
