package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Methods;
import raf.dsw.classycraft.app.observer.Publisher;
import raf.dsw.classycraft.app.observer.Subscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public class Interfejs extends Interclass implements Publisher {

    private List<ClassContent> atributsList = new ArrayList<>();

    private List<Subscriber> subscribers = new ArrayList<>();
    private int broj = 5;

    public Interfejs(ClassyNode parent, String name, int x, int y, int width, int height,Paint paint) {
        super(parent, name, x, y, width, height,paint);
    }
    public void dodaj(ClassContent classContent){
        if(atributsList.isEmpty())
            this.broj = 0;
        if(!(atributsList.contains(classContent))){
            if(classContent instanceof Methods) {
                //((Atributs) classContent).setBroj(broj);
                //broj+=20;

                atributsList.add(classContent);
                //((Diagram)getParent()).notifySubscriber("","crtanje");
            }

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
    public int getBroj() {
        return broj;
    }
    public int povecajBroj(){
        broj+= 20;
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }
}
