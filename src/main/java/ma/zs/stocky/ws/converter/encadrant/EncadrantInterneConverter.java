package  ma.zs.stocky.ws.converter.encadrant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.ws.dto.encadrant.EncadrantInterneDto;

@Component
public class EncadrantInterneConverter {


    public  EncadrantInterneConverter() {
    }


    public EncadrantInterne toItem(EncadrantInterneDto dto) {
        if (dto == null) {
            return null;
        } else {
        EncadrantInterne item = new EncadrantInterne();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getBiographie()))
                item.setBiographie(dto.getBiographie());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setEnabled(dto.getEnabled());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            item.setPasswordChanged(dto.getPasswordChanged());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());


            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public EncadrantInterneDto toDto(EncadrantInterne item) {
        if (item == null) {
            return null;
        } else {
            EncadrantInterneDto dto = new EncadrantInterneDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getBiographie()))
                dto.setBiographie(item.getBiographie());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());


        return dto;
        }
    }


	
    public List<EncadrantInterne> toItem(List<EncadrantInterneDto> dtos) {
        List<EncadrantInterne> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EncadrantInterneDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EncadrantInterneDto> toDto(List<EncadrantInterne> items) {
        List<EncadrantInterneDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EncadrantInterne item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EncadrantInterneDto dto, EncadrantInterne t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<EncadrantInterne> copy(List<EncadrantInterneDto> dtos) {
        List<EncadrantInterne> result = new ArrayList<>();
        if (dtos != null) {
            for (EncadrantInterneDto dto : dtos) {
                EncadrantInterne instance = new EncadrantInterne();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
