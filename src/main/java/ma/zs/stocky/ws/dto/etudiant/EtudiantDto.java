package  ma.zs.stocky.ws.dto.etudiant;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zs.stocky.zynerator.security.bean.Role;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.stocky.ws.dto.departement.FiliereDto;
import ma.zs.stocky.ws.dto.appartenance.NationaliteDto;
import ma.zs.stocky.ws.dto.appartenance.GenreDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EtudiantDto  extends AuditBaseDto {

    private String adresse  ;
    private String telephone  ;
    private String dateNaissance ;
    private String codeAppoge  ;
    private Boolean credentialsNonExpired  ;
    private Boolean enabled  ;
    private Boolean accountNonExpired  ;
    private Boolean accountNonLocked  ;
    private Boolean passwordChanged  ;
    private String username  ;
    private String password  ;

    private GenreDto genre ;
    private NationaliteDto nationalite ;
    private FiliereDto filiere ;



    private Collection<Role> roles;
    public EtudiantDto(){
        super();
    }



    @Log
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    @Log
    public String getTelephone(){
        return this.telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateNaissance(){
        return this.dateNaissance;
    }
    public void setDateNaissance(String dateNaissance){
        this.dateNaissance = dateNaissance;
    }

    @Log
    public String getCodeAppoge(){
        return this.codeAppoge;
    }
    public void setCodeAppoge(String codeAppoge){
        this.codeAppoge = codeAppoge;
    }

    @Log
    public Boolean getCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }
    public void setCredentialsNonExpired(Boolean credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Log
    public Boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }

    @Log
    public Boolean getAccountNonExpired(){
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(Boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }

    @Log
    public Boolean getAccountNonLocked(){
        return this.accountNonLocked;
    }
    public void setAccountNonLocked(Boolean accountNonLocked){
        this.accountNonLocked = accountNonLocked;
    }

    @Log
    public Boolean getPasswordChanged(){
        return this.passwordChanged;
    }
    public void setPasswordChanged(Boolean passwordChanged){
        this.passwordChanged = passwordChanged;
    }

    @Log
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    @Log
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }


    public GenreDto getGenre(){
        return this.genre;
    }

    public void setGenre(GenreDto genre){
        this.genre = genre;
    }
    public NationaliteDto getNationalite(){
        return this.nationalite;
    }

    public void setNationalite(NationaliteDto nationalite){
        this.nationalite = nationalite;
    }
    public FiliereDto getFiliere(){
        return this.filiere;
    }

    public void setFiliere(FiliereDto filiere){
        this.filiere = filiere;
    }







    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
