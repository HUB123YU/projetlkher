package ma.zs.stocky.bean.core.departement;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "departement")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="departement_seq",sequenceName="departement_seq",allocationSize=1, initialValue = 1)
public class Departement  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String code;



    public Departement(){
        super();
    }

    public Departement(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public Departement(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="departement_seq")
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
        Departement departement = (Departement) o;
        return id != null && id.equals(departement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

