package ma.zs.stocky.bean.core.stage;

import java.util.Objects;





import ma.zs.stocky.bean.core.encadrant.EncadrantInterne;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stage_encadrant_interne")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="stage_encadrant_interne_seq",sequenceName="stage_encadrant_interne_seq",allocationSize=1, initialValue = 1)
public class StageEncadrantInterne  extends BaseEntity     {

    private Long id;



    private Stage stage ;
    private EncadrantInterne encadrantInterne ;


    public StageEncadrantInterne(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="stage_encadrant_interne_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage")
    public Stage getStage(){
        return this.stage;
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encadrant_interne")
    public EncadrantInterne getEncadrantInterne(){
        return this.encadrantInterne;
    }
    public void setEncadrantInterne(EncadrantInterne encadrantInterne){
        this.encadrantInterne = encadrantInterne;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageEncadrantInterne stageEncadrantInterne = (StageEncadrantInterne) o;
        return id != null && id.equals(stageEncadrantInterne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

