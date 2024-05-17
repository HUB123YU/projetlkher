package ma.zs.stocky.bean.core.stage;

import java.util.Objects;





import ma.zs.stocky.bean.core.etudiant.Etudiant;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stage_etudiant")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="stage_etudiant_seq",sequenceName="stage_etudiant_seq",allocationSize=1, initialValue = 1)
public class StageEtudiant  extends BaseEntity     {

    private Long id;



    private Stage stage ;
    private Etudiant etudiant ;


    public StageEtudiant(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="stage_etudiant_seq")
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
    @JoinColumn(name = "etudiant")
    public Etudiant getEtudiant(){
        return this.etudiant;
    }
    public void setEtudiant(Etudiant etudiant){
        this.etudiant = etudiant;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageEtudiant stageEtudiant = (StageEtudiant) o;
        return id != null && id.equals(stageEtudiant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

