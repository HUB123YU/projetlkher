package  ma.zs.stocky.dao.criteria.core.jury;



import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class JuryCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String nombreMembres;
    private String nombreMembresMin;
    private String nombreMembresMax;



    public JuryCriteria(){}

    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getRefLike(){
        return this.refLike;
    }
    public void setRefLike(String refLike){
        this.refLike = refLike;
    }

    public String getNombreMembres(){
        return this.nombreMembres;
    }
    public void setNombreMembres(String nombreMembres){
        this.nombreMembres = nombreMembres;
    }   
    public String getNombreMembresMin(){
        return this.nombreMembresMin;
    }
    public void setNombreMembresMin(String nombreMembresMin){
        this.nombreMembresMin = nombreMembresMin;
    }
    public String getNombreMembresMax(){
        return this.nombreMembresMax;
    }
    public void setNombreMembresMax(String nombreMembresMax){
        this.nombreMembresMax = nombreMembresMax;
    }
      

}
