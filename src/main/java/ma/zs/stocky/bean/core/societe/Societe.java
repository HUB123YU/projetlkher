package ma.zs.stocky.bean.core.societe;

import java.util.Objects;





import ma.zs.stocky.bean.core.departement.SecteurActivite;
import ma.zs.stocky.bean.core.appartenance.Ville;
import ma.zs.stocky.bean.core.appartenance.Pays;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "societe")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="societe_seq",sequenceName="societe_seq",allocationSize=1, initialValue = 1)
public class Societe  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String ice;

    @Column(length = 500)
    private String nom;

    @Column(length = 500)
    private String adresse;

    @Column(length = 500)
    private String fax;

    @Column(length = 500)
    private String domaine;

    @Column(length = 500)
    private String email;

    @Column(length = 500)
    private String telephone;

    @Column(length = 500)
    private String codePostal;

    private Ville ville ;
    private SecteurActivite secteurActivite ;
    private Pays pays ;


    public Societe(){
        super();
    }

    public Societe(Long id,String ice){
        this.id = id;
        this.ice = ice ;
    }
    public Societe(String ice){
        this.ice = ice ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="societe_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getIce(){
        return this.ice;
    }
    public void setIce(String ice){
        this.ice = ice;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getFax(){
        return this.fax;
    }
    public void setFax(String fax){
        this.fax = fax;
    }
    public String getDomaine(){
        return this.domaine;
    }
    public void setDomaine(String domaine){
        this.domaine = domaine;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ville")
    public Ville getVille(){
        return this.ville;
    }
    public void setVille(Ville ville){
        this.ville = ville;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secteur_activite")
    public SecteurActivite getSecteurActivite(){
        return this.secteurActivite;
    }
    public void setSecteurActivite(SecteurActivite secteurActivite){
        this.secteurActivite = secteurActivite;
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
    public String getCodePostal(){
        return this.codePostal;
    }
    public void setCodePostal(String codePostal){
        this.codePostal = codePostal;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pays")
    public Pays getPays(){
        return this.pays;
    }
    public void setPays(Pays pays){
        this.pays = pays;
    }

    @Transient
    public String getLabel() {
        label = ice;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Societe societe = (Societe) o;
        return id != null && id.equals(societe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

