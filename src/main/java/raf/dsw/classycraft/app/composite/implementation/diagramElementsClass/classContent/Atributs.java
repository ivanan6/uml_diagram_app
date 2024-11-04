package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeLeaf;
import raf.dsw.classycraft.app.observer.Publisher;
import raf.dsw.classycraft.app.observer.Subscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Atributs extends ClassContent implements Publisher {
    private String vidljivost;
    private List<Subscriber> subscribers = new ArrayList<>();
    private boolean isStatic;
    private boolean isAbstract;
    private String tip;
    private  int broj = 20;

    public Atributs(ClassyNodeLeaf classyNodeLeaf, Paint paint, String name, String tip, boolean isStatic, boolean isAbstract) {
        super(classyNodeLeaf, paint, name);

        this.isStatic = isStatic;
        this.isAbstract = isAbstract;
        this.tip = tip;
    }

    @Override
    public String toString() {

        return vidljivost + " " + super.getName() + ": " + tip;
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

    }


    @Override
    public void addSubscriber(Subscriber var1) {
        if(var1 != null){
            if(!this.subscribers.contains(var1)){
                this.subscribers.add(var1);
            }
        }
    }

    @Override
    public void removeSubscriber(Subscriber var1) {
        if(var1 != null && this.subscribers!= null && this.subscribers.contains(var1))
            this.subscribers.remove(var1);

    }

    @Override
    public void notifySubscriber(Object var1,String tekst) {
        if(var1 == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for (Subscriber s : this.subscribers)
            s.update(var1,tekst);
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(obj instanceof Atributs){
//            Atributs atributs = (Atributs) obj;
//            return super.getName().equals(atributs.getName()) && this.tip.equals(atributs.getTip());
//        }
//        return false;
//
//    }


    public void setBroj(int broj) {
        this.broj = broj;
    }

}
