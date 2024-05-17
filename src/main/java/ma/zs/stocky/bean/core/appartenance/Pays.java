package ma.zs.stocky.bean.core.appartenance;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pays")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="pays_seq",sequenceName="pays_seq",allocationSize=1, initialValue = 1)
public class Pays  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String reference;

    @Column(length = 500)
    private String libelle;



    public Pays(){
        super();
    }

    public Pays(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Pays(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="pays_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pays pays = (Pays) o;
        return id != null && id.equals(pays.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

