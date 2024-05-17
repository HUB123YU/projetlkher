package ma.zs.stocky.bean.core.departement;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "domaine")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="domaine_seq",sequenceName="domaine_seq",allocationSize=1, initialValue = 1)
public class Domaine  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String code;



    public Domaine(){
        super();
    }

    public Domaine(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public Domaine(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="domaine_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
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
        Domaine domaine = (Domaine) o;
        return id != null && id.equals(domaine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

