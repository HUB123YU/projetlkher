package  ma.zs.stocky.ws.converter.societe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.departement.SecteurActiviteConverter;
import ma.zs.stocky.ws.converter.appartenance.VilleConverter;
import ma.zs.stocky.ws.converter.appartenance.PaysConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.societe.Societe;
import ma.zs.stocky.ws.dto.societe.SocieteDto;

@Component
public class SocieteConverter {

    @Autowired
    private SecteurActiviteConverter secteurActiviteConverter ;
    @Autowired
    private VilleConverter villeConverter ;
    @Autowired
    private PaysConverter paysConverter ;
    private boolean ville;
    private boolean secteurActivite;
    private boolean pays;

    public  SocieteConverter() {
        initObject(true);
    }


    public Societe toItem(SocieteDto dto) {
        if (dto == null) {
            return null;
        } else {
        Societe item = new Societe();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getIce()))
                item.setIce(dto.getIce());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getAdresse()))
                item.setAdresse(dto.getAdresse());
            if(StringUtil.isNotEmpty(dto.getFax()))
                item.setFax(dto.getFax());
            if(StringUtil.isNotEmpty(dto.getDomaine()))
                item.setDomaine(dto.getDomaine());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getTelephone()))
                item.setTelephone(dto.getTelephone());
            if(StringUtil.isNotEmpty(dto.getCodePostal()))
                item.setCodePostal(dto.getCodePostal());
            if(this.ville && dto.getVille()!=null)
                item.setVille(villeConverter.toItem(dto.getVille())) ;

            if(this.secteurActivite && dto.getSecteurActivite()!=null)
                item.setSecteurActivite(secteurActiviteConverter.toItem(dto.getSecteurActivite())) ;

            if(this.pays && dto.getPays()!=null)
                item.setPays(paysConverter.toItem(dto.getPays())) ;




        return item;
        }
    }


    public SocieteDto toDto(Societe item) {
        if (item == null) {
            return null;
        } else {
            SocieteDto dto = new SocieteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getIce()))
                dto.setIce(item.getIce());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getAdresse()))
                dto.setAdresse(item.getAdresse());
            if(StringUtil.isNotEmpty(item.getFax()))
                dto.setFax(item.getFax());
            if(StringUtil.isNotEmpty(item.getDomaine()))
                dto.setDomaine(item.getDomaine());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getTelephone()))
                dto.setTelephone(item.getTelephone());
            if(StringUtil.isNotEmpty(item.getCodePostal()))
                dto.setCodePostal(item.getCodePostal());
            if(this.ville && item.getVille()!=null) {
                dto.setVille(villeConverter.toDto(item.getVille())) ;

            }
            if(this.secteurActivite && item.getSecteurActivite()!=null) {
                dto.setSecteurActivite(secteurActiviteConverter.toDto(item.getSecteurActivite())) ;

            }
            if(this.pays && item.getPays()!=null) {
                dto.setPays(paysConverter.toDto(item.getPays())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.ville = value;
        this.secteurActivite = value;
        this.pays = value;
    }
	
    public List<Societe> toItem(List<SocieteDto> dtos) {
        List<Societe> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SocieteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SocieteDto> toDto(List<Societe> items) {
        List<SocieteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Societe item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SocieteDto dto, Societe t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getVille() != null)
        villeConverter.copy(dto.getVille(), t.getVille());
        if (dto.getSecteurActivite() != null)
        secteurActiviteConverter.copy(dto.getSecteurActivite(), t.getSecteurActivite());
        if (dto.getPays() != null)
        paysConverter.copy(dto.getPays(), t.getPays());
    }

    public List<Societe> copy(List<SocieteDto> dtos) {
        List<Societe> result = new ArrayList<>();
        if (dtos != null) {
            for (SocieteDto dto : dtos) {
                Societe instance = new Societe();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public SecteurActiviteConverter getSecteurActiviteConverter(){
        return this.secteurActiviteConverter;
    }
    public void setSecteurActiviteConverter(SecteurActiviteConverter secteurActiviteConverter ){
        this.secteurActiviteConverter = secteurActiviteConverter;
    }
    public VilleConverter getVilleConverter(){
        return this.villeConverter;
    }
    public void setVilleConverter(VilleConverter villeConverter ){
        this.villeConverter = villeConverter;
    }
    public PaysConverter getPaysConverter(){
        return this.paysConverter;
    }
    public void setPaysConverter(PaysConverter paysConverter ){
        this.paysConverter = paysConverter;
    }
    public boolean  isVille(){
        return this.ville;
    }
    public void  setVille(boolean ville){
        this.ville = ville;
    }
    public boolean  isSecteurActivite(){
        return this.secteurActivite;
    }
    public void  setSecteurActivite(boolean secteurActivite){
        this.secteurActivite = secteurActivite;
    }
    public boolean  isPays(){
        return this.pays;
    }
    public void  setPays(boolean pays){
        this.pays = pays;
    }
}
