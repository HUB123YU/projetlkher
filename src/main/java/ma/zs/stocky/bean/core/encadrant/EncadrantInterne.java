package ma.zs.stocky.bean.core.encadrant;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zs.stocky.zynerator.security.bean.User;

@Entity
@Table(name = "encadrant_interne")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="encadrant_interne_seq",sequenceName="encadrant_interne_seq",allocationSize=1, initialValue = 1)
public class EncadrantInterne  extends User    {


    public EncadrantInterne(String username) {
        super(username);
    }


    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String biographie;










    public EncadrantInterne(){
        super();
    }

    public EncadrantInterne(Long id,String code){
        this.id = id;
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="encadrant_interne_seq")
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
    public String getBiographie(){
        return this.biographie;
    }
    public void setBiographie(String biographie){
        this.biographie = biographie;
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
        EncadrantInterne encadrantInterne = (EncadrantInterne) o;
        return id != null && id.equals(encadrantInterne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

