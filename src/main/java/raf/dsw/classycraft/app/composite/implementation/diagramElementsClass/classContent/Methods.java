package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeLeaf;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Methods extends ClassContent{
    private String vidljivost;
    private boolean isStatic;
    private boolean isAbstract;
    private List<String> ulazniElementi = new ArrayList<>();
    private String tip;
    public Methods(ClassyNodeLeaf classyNodeLeaf, Paint paint, String name,String tip, boolean isStatic, boolean isAbstract) {
        super(classyNodeLeaf, paint, name);
        this.vidljivost = vidljivost;
        this.isStatic = isStatic;
        this.tip = tip;
        this.isAbstract = isAbstract;
    }
    public void setVidljivost(String vidljivost) {
        if(vidljivost.toLowerCase().equals("private"))
            this.vidljivost = "-";
        else if (vidljivost.toLowerCase().equals("public")) {
            this.vidljivost = "+";
        }else if (vidljivost.toLowerCase().equals("protected")) {
            this.vidljivost = "#";
        }
        else{
            this.vidljivost = "~";
        }
        //notifySubscriber("", "crtanje");
    }

    @Override
    public String toString() {
        return this.getVidljivost() + this.getName()+ this.getUlazniElementi() + ": "+this.getTip() ;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Methods)) return false;
//        Methods methods = (Methods) o;
//        return Objects.equals(vidljivost, methods.vidljivost) && Objects.equals(tip, methods.tip);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(vidljivost, tip);
    }
}
