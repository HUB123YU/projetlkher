package ma.zs.stocky.bean.core.encadrant;

import java.util.Objects;





import ma.zs.stocky.bean.core.societe.Societe;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "encadrant_externe")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="encadrant_externe_seq",sequenceName="encadrant_externe_seq",allocationSize=1, initialValue = 1)
public class EncadrantExterne  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String nom;

    @Column(length = 500)
    private String prenom;

    @Column(length = 500)
    private String email;

    @Column(length = 500)
    private String telephone;

    @Column(length = 500)
    private String biographie;

    private Societe societe ;


    public EncadrantExterne(){
        super();
    }

    public EncadrantExterne(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public EncadrantExterne(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="encadrant_externe_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getTelephone(){
        return this.telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getBiographie(){
        return this.biographie;
    }
    public void setBiographie(String biographie){
        this.biographie = biographie;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "societe")
    public Societe getSociete(){
        return this.societe;
    }
    public void setSociete(Societe societe){
        this.societe = societe;
    }

    @Transient
    public String getLabel() {
        label = code;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncadrantExterne encadrantExterne = (EncadrantExterne) o;
        return id != null && id.equals(encadrantExterne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

