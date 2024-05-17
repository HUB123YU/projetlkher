package ma.zs.stocky.bean.core.stage;

import java.util.Objects;





import ma.zs.stocky.bean.core.piecejointe.Attachement;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "stage_attachement")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="stage_attachement_seq",sequenceName="stage_attachement_seq",allocationSize=1, initialValue = 1)
public class StageAttachement  extends BaseEntity     {

    private Long id;



    private BigDecimal size = BigDecimal.ZERO;

    private Stage stage ;
    private Attachement attachement ;


    public StageAttachement(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="stage_attachement_seq")
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
    @JoinColumn(name = "attachement")
    public Attachement getAttachement(){
        return this.attachement;
    }
    public void setAttachement(Attachement attachement){
        this.attachement = attachement;
    }
    public BigDecimal getSize(){
        return this.size;
    }
    public void setSize(BigDecimal size){
        this.size = size;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageAttachement stageAttachement = (StageAttachement) o;
        return id != null && id.equals(stageAttachement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

