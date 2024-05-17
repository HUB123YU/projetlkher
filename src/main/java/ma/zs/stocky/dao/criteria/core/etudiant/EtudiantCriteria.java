package  ma.zs.stocky.dao.criteria.core.etudiant;


import ma.zs.stocky.dao.criteria.core.departement.FiliereCriteria;
import ma.zs.stocky.dao.criteria.core.appartenance.NationaliteCriteria;
import ma.zs.stocky.dao.criteria.core.appartenance.GenreCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class EtudiantCriteria extends  BaseCriteria  {

    private String adresse;
    private String adresseLike;
    private String telephone;
    private String telephoneLike;
    private LocalDateTime dateNaissance;
    private LocalDateTime dateNaissanceFrom;
    private LocalDateTime dateNaissanceTo;
    private String codeAppoge;
    private String codeAppogeLike;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean passwordChanged;
    private String username;
    private String usernameLike;
    private String password;
    private String passwordLike;

    private GenreCriteria genre ;
    private List<GenreCriteria> genres ;
    private NationaliteCriteria nationalite ;
    private List<NationaliteCriteria> nationalites ;
    private FiliereCriteria filiere ;
    private List<FiliereCriteria> filieres ;


    public EtudiantCriteria(){}

    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getAdresseLike(){
        return this.adresseLike;
    }
    public void setAdresseLike(String adresseLike){
        this.adresseLike = adresseLike;
    }

    public String getTelephone(){
        return this.telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getTelephoneLike(){
        return this.telephoneLike;
    }
    public void setTelephoneLike(String telephoneLike){
        this.telephoneLike = telephoneLike;
    }

    public LocalDateTime getDateNaissance(){
        return this.dateNaissance;
    }
    public void setDateNaissance(LocalDateTime dateNaissance){
        this.dateNaissance = dateNaissance;
    }
    public LocalDateTime getDateNaissanceFrom(){
        return this.dateNaissanceFrom;
    }
    public void setDateNaissanceFrom(LocalDateTime dateNaissanceFrom){
        this.dateNaissanceFrom = dateNaissanceFrom;
    }
    public LocalDateTime getDateNaissanceTo(){
        return this.dateNaissanceTo;
    }
    public void setDateNaissanceTo(LocalDateTime dateNaissanceTo){
        this.dateNaissanceTo = dateNaissanceTo;
    }
    public String getCodeAppoge(){
        return this.codeAppoge;
    }
    public void setCodeAppoge(String codeAppoge){
        this.codeAppoge = codeAppoge;
    }
    public String getCodeAppogeLike(){
        return this.codeAppogeLike;
    }
    public void setCodeAppogeLike(String codeAppogeLike){
        this.codeAppogeLike = codeAppogeLike;
    }

    public Boolean getCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }
    public void setCredentialsNonExpired(Boolean credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public Boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }
    public Boolean getAccountNonExpired(){
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(Boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }
    public Boolean getAccountNonLocked(){
        return this.accountNonLocked;
    }
    public void setAccountNonLocked(Boolean accountNonLocked){
        this.accountNonLocked = accountNonLocked;
    }
    public Boolean getPasswordChanged(){
        return this.passwordChanged;
    }
    public void setPasswordChanged(Boolean passwordChanged){
        this.passwordChanged = passwordChanged;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsernameLike(){
        return this.usernameLike;
    }
    public void setUsernameLike(String usernameLike){
        this.usernameLike = usernameLike;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPasswordLike(){
        return this.passwordLike;
    }
    public void setPasswordLike(String passwordLike){
        this.passwordLike = passwordLike;
    }


    public GenreCriteria getGenre(){
        return this.genre;
    }

    public void setGenre(GenreCriteria genre){
        this.genre = genre;
    }
    public List<GenreCriteria> getGenres(){
        return this.genres;
    }

    public void setGenres(List<GenreCriteria> genres){
        this.genres = genres;
    }
    public NationaliteCriteria getNationalite(){
        return this.nationalite;
    }

    public void setNationalite(NationaliteCriteria nationalite){
        this.nationalite = nationalite;
    }
    public List<NationaliteCriteria> getNationalites(){
        return this.nationalites;
    }

    public void setNationalites(List<NationaliteCriteria> nationalites){
        this.nationalites = nationalites;
    }
    public FiliereCriteria getFiliere(){
        return this.filiere;
    }

    public void setFiliere(FiliereCriteria filiere){
        this.filiere = filiere;
    }
    public List<FiliereCriteria> getFilieres(){
        return this.filieres;
    }

    public void setFilieres(List<FiliereCriteria> filieres){
        this.filieres = filieres;
    }
}
