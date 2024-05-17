package ma.zs.stocky.bean.core.piecejointe;

import java.util.Objects;





import ma.zs.stocky.bean.core.stage.Stage;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "attachement")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="attachement_seq",sequenceName="attachement_seq",allocationSize=1, initialValue = 1)
public class Attachement  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String nom;

    @Column(length = 500)
    private String contenu;

    private BigDecimal taille = BigDecimal.ZERO;

    private TypeAttachement typeAttachement ;
    private Stage stage ;


    public Attachement(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="attachement_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getContenu(){
        return this.contenu;
    }
    public void setContenu(String contenu){
        this.contenu = contenu;
    }
    public BigDecimal getTaille(){
        return this.taille;
    }
    public void setTaille(BigDecimal taille){
        this.taille = taille;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_attachement")
    public TypeAttachement getTypeAttachement(){
        return this.typeAttachement;
    }
    public void setTypeAttachement(TypeAttachement typeAttachement){
        this.typeAttachement = typeAttachement;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage")
    public Stage getStage(){
        return this.stage;
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachement attachement = (Attachement) o;
        return id != null && id.equals(attachement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

