package ma.zs.stocky.bean.core.encadrant;

import java.util.Objects;





import ma.zs.stocky.bean.core.jury.Jury;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jury_encadrant_interne")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="jury_encadrant_interne_seq",sequenceName="jury_encadrant_interne_seq",allocationSize=1, initialValue = 1)
public class JuryEncadrantInterne  extends BaseEntity     {

    private Long id;



    private EncadrantInterne encadrantInterne ;
    private Jury jury ;


    public JuryEncadrantInterne(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="jury_encadrant_interne_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encadrant_interne")
    public EncadrantInterne getEncadrantInterne(){
        return this.encadrantInterne;
    }
    public void setEncadrantInterne(EncadrantInterne encadrantInterne){
        this.encadrantInterne = encadrantInterne;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jury")
    public Jury getJury(){
        return this.jury;
    }
    public void setJury(Jury jury){
        this.jury = jury;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JuryEncadrantInterne juryEncadrantInterne = (JuryEncadrantInterne) o;
        return id != null && id.equals(juryEncadrantInterne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

