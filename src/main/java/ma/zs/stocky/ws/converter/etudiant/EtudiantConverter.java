package  ma.zs.stocky.ws.converter.etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.departement.FiliereConverter;
import ma.zs.stocky.ws.converter.appartenance.NationaliteConverter;
import ma.zs.stocky.ws.converter.appartenance.GenreConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.etudiant.Etudiant;
import ma.zs.stocky.ws.dto.etudiant.EtudiantDto;

@Component
public class EtudiantConverter {

    @Autowired
    private FiliereConverter filiereConverter ;
    @Autowired
    private NationaliteConverter nationaliteConverter ;
    @Autowired
    private GenreConverter genreConverter ;
    private boolean genre;
    private boolean nationalite;
    private boolean filiere;

    public  EtudiantConverter() {
        initObject(true);
    }


    public Etudiant toItem(EtudiantDto dto) {
        if (dto == null) {
            return null;
        } else {
        Etudiant item = new Etudiant();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getAdresse()))
                item.setAdresse(dto.getAdresse());
            if(StringUtil.isNotEmpty(dto.getTelephone()))
                item.setTelephone(dto.getTelephone());
            if(StringUtil.isNotEmpty(dto.getDateNaissance()))
                item.setDateNaissance(DateUtil.stringEnToDate(dto.getDateNaissance()));
            if(StringUtil.isNotEmpty(dto.getCodeAppoge()))
                item.setCodeAppoge(dto.getCodeAppoge());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setEnabled(dto.getEnabled());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            item.setPasswordChanged(dto.getPasswordChanged());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(this.genre && dto.getGenre()!=null)
                item.setGenre(genreConverter.toItem(dto.getGenre())) ;

            if(this.nationalite && dto.getNationalite()!=null)
                item.setNationalite(nationaliteConverter.toItem(dto.getNationalite())) ;

            if(this.filiere && dto.getFiliere()!=null)
                item.setFiliere(filiereConverter.toItem(dto.getFiliere())) ;



            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public EtudiantDto toDto(Etudiant item) {
        if (item == null) {
            return null;
        } else {
            EtudiantDto dto = new EtudiantDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getAdresse()))
                dto.setAdresse(item.getAdresse());
            if(StringUtil.isNotEmpty(item.getTelephone()))
                dto.setTelephone(item.getTelephone());
            if(item.getDateNaissance()!=null)
                dto.setDateNaissance(DateUtil.dateTimeToString(item.getDateNaissance()));
            if(StringUtil.isNotEmpty(item.getCodeAppoge()))
                dto.setCodeAppoge(item.getCodeAppoge());
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
            if(this.genre && item.getGenre()!=null) {
                dto.setGenre(genreConverter.toDto(item.getGenre())) ;

            }
            if(this.nationalite && item.getNationalite()!=null) {
                dto.setNationalite(nationaliteConverter.toDto(item.getNationalite())) ;

            }
            if(this.filiere && item.getFiliere()!=null) {
                dto.setFiliere(filiereConverter.toDto(item.getFiliere())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.genre = value;
        this.nationalite = value;
        this.filiere = value;
    }
	
    public List<Etudiant> toItem(List<EtudiantDto> dtos) {
        List<Etudiant> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EtudiantDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EtudiantDto> toDto(List<Etudiant> items) {
        List<EtudiantDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Etudiant item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EtudiantDto dto, Etudiant t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getGenre() != null)
        genreConverter.copy(dto.getGenre(), t.getGenre());
        if (dto.getNationalite() != null)
        nationaliteConverter.copy(dto.getNationalite(), t.getNationalite());
        if (dto.getFiliere() != null)
        filiereConverter.copy(dto.getFiliere(), t.getFiliere());
    }

    public List<Etudiant> copy(List<EtudiantDto> dtos) {
        List<Etudiant> result = new ArrayList<>();
        if (dtos != null) {
            for (EtudiantDto dto : dtos) {
                Etudiant instance = new Etudiant();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public FiliereConverter getFiliereConverter(){
        return this.filiereConverter;
    }
    public void setFiliereConverter(FiliereConverter filiereConverter ){
        this.filiereConverter = filiereConverter;
    }
    public NationaliteConverter getNationaliteConverter(){
        return this.nationaliteConverter;
    }
    public void setNationaliteConverter(NationaliteConverter nationaliteConverter ){
        this.nationaliteConverter = nationaliteConverter;
    }
    public GenreConverter getGenreConverter(){
        return this.genreConverter;
    }
    public void setGenreConverter(GenreConverter genreConverter ){
        this.genreConverter = genreConverter;
    }
    public boolean  isGenre(){
        return this.genre;
    }
    public void  setGenre(boolean genre){
        this.genre = genre;
    }
    public boolean  isNationalite(){
        return this.nationalite;
    }
    public void  setNationalite(boolean nationalite){
        this.nationalite = nationalite;
    }
    public boolean  isFiliere(){
        return this.filiere;
    }
    public void  setFiliere(boolean filiere){
        this.filiere = filiere;
    }
}
