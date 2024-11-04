package raf.dsw.classycraft.app.composite.implementation;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeLeaf;
import raf.dsw.classycraft.app.observer.Publisher;
import raf.dsw.classycraft.app.observer.Subscriber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Diagram extends ClassyNodeComposite implements Publisher {

    private List<Subscriber> subscribers = new ArrayList<>();

    public Diagram(ClassyNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void addChild(ClassyNode child) {
        notifySubscriber( "", "crtanje");
        if(child instanceof DiagramElements){
            if(!super.getChildren().contains(child)){
                super.getChildren().add(child);
                child.setParent(this);
            }
        }

    }

    @Override
    public void deleteChild(ClassyNode child) {
        getChildren().remove(child);

        if (child instanceof ClassyNodeComposite) {
            deleteSub((ClassyNodeComposite) child);
        }else {
            notifySubscriber("", "crtanje");
        }
        child.setParent(null);
    }
    private void deleteSub(ClassyNodeComposite child) {
        List<ClassyNode> children = child.getChildren();
        Iterator<ClassyNode> iterator = children.iterator();

        while (iterator.hasNext()) {
            ClassyNode subDete = iterator.next();

            if(subDete instanceof Diagram)
                notifySubscriber(subDete,"delete");
            iterator.remove();
            if (subDete instanceof ClassyNodeComposite) {
                deleteSub((ClassyNodeComposite) subDete);
            }
        }
    }

    @Override
    public void setName(String name) {
        Package p = (Package) this.getParent();
        boolean flag = false;
        for(ClassyNode d : p.getChildren()){
            if(d instanceof Diagram){
                if(d.getName().equals(name)){
                    flag = true;
                    break;
                }

            }
        }
        if(!flag){
            super.setName(name);
            notifySubscriber(name,"");
        }
        else{
            notifySubscriber("null", "");
        }

    }
    public void selektovano(){
        notifySubscriber("", "crtanje");
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


}
