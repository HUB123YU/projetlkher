package  ma.zs.stocky.dao.criteria.core.encadrant;


import ma.zs.stocky.dao.criteria.core.jury.JuryCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class JuryEncadrantInterneCriteria extends  BaseCriteria  {


    private EncadrantInterneCriteria encadrantInterne ;
    private List<EncadrantInterneCriteria> encadrantInternes ;
    private JuryCriteria jury ;
    private List<JuryCriteria> jurys ;


    public JuryEncadrantInterneCriteria(){}


    public EncadrantInterneCriteria getEncadrantInterne(){
        return this.encadrantInterne;
    }

    public void setEncadrantInterne(EncadrantInterneCriteria encadrantInterne){
        this.encadrantInterne = encadrantInterne;
    }
    public List<EncadrantInterneCriteria> getEncadrantInternes(){
        return this.encadrantInternes;
    }

    public void setEncadrantInternes(List<EncadrantInterneCriteria> encadrantInternes){
        this.encadrantInternes = encadrantInternes;
    }
    public JuryCriteria getJury(){
        return this.jury;
    }

    public void setJury(JuryCriteria jury){
        this.jury = jury;
    }
    public List<JuryCriteria> getJurys(){
        return this.jurys;
    }

    public void setJurys(List<JuryCriteria> jurys){
        this.jurys = jurys;
    }
}
