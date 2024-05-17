package ma.zs.stocky.bean.core.etudiant;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.stocky.bean.core.departement.Filiere;
import ma.zs.stocky.bean.core.appartenance.Nationalite;
import ma.zs.stocky.bean.core.appartenance.Genre;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zs.stocky.zynerator.security.bean.User;

@Entity
@Table(name = "etudiant")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="etudiant_seq",sequenceName="etudiant_seq",allocationSize=1, initialValue = 1)
public class Etudiant  extends User    {


    public Etudiant(String username) {
        super(username);
    }


    @Column(length = 500)
    private String adresse;

    @Column(length = 500)
    private String telephone;

    private LocalDateTime dateNaissance ;

    @Column(length = 500)
    private String codeAppoge;








    private Genre genre ;
    private Nationalite nationalite ;
    private Filiere filiere ;


    public Etudiant(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="etudiant_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getTelephone(){
        return this.telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public LocalDateTime getDateNaissance(){
        return this.dateNaissance;
    }
    public void setDateNaissance(LocalDateTime dateNaissance){
        this.dateNaissance = dateNaissance;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre")
    public Genre getGenre(){
        return this.genre;
    }
    public void setGenre(Genre genre){
        this.genre = genre;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationalite")
    public Nationalite getNationalite(){
        return this.nationalite;
    }
    public void setNationalite(Nationalite nationalite){
        this.nationalite = nationalite;
    }
    public String getCodeAppoge(){
        return this.codeAppoge;
    }
    public void setCodeAppoge(String codeAppoge){
        this.codeAppoge = codeAppoge;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filiere")
    public Filiere getFiliere(){
        return this.filiere;
    }
    public void setFiliere(Filiere filiere){
        this.filiere = filiere;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etudiant etudiant = (Etudiant) o;
        return id != null && id.equals(etudiant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

