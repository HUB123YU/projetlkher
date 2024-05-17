package ma.zs.stocky.bean.core.jury;

import java.util.Objects;
import java.util.List;





import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;
import ma.zs.stocky.bean.core.encadrant.JuryEncadrantInterne;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jury")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="jury_seq",sequenceName="jury_seq",allocationSize=1, initialValue = 1)
public class Jury  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String ref;

    private Integer nombreMembres = 0;


    private List<JuryEncadrantInterne> juryEncadrantInternes ;

    public Jury(){
        super();
    }

    public Jury(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Jury(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="jury_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public Integer getNombreMembres(){
        return this.nombreMembres;
    }
    public void setNombreMembres(Integer nombreMembres){
        this.nombreMembres = nombreMembres;
    }
    @OneToMany(mappedBy = "jury")
    public List<JuryEncadrantInterne> getJuryEncadrantInternes(){
        return this.juryEncadrantInternes;
    }

    public void setJuryEncadrantInternes(List<JuryEncadrantInterne> juryEncadrantInternes){
        this.juryEncadrantInternes = juryEncadrantInternes;
    }

    @Transient
    public String getLabel() {
        label = ref;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jury jury = (Jury) o;
        return id != null && id.equals(jury.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

