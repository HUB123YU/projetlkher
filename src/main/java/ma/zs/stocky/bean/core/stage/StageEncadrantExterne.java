package ma.zs.stocky.bean.core.stage;

import java.util.Objects;





import ma.zs.stocky.bean.core.encadrant.EncadrantExterne;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stage_encadrant_externe")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="stage_encadrant_externe_seq",sequenceName="stage_encadrant_externe_seq",allocationSize=1, initialValue = 1)
public class StageEncadrantExterne  extends BaseEntity     {

    private Long id;



    private Stage stage ;
    private EncadrantExterne encadrantExterne ;


    public StageEncadrantExterne(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="stage_encadrant_externe_seq")
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
    @JoinColumn(name = "encadrant_externe")
    public EncadrantExterne getEncadrantExterne(){
        return this.encadrantExterne;
    }
    public void setEncadrantExterne(EncadrantExterne encadrantExterne){
        this.encadrantExterne = encadrantExterne;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageEncadrantExterne stageEncadrantExterne = (StageEncadrantExterne) o;
        return id != null && id.equals(stageEncadrantExterne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

