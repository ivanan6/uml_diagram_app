package raf.dsw.classycraft.app.composite.factory;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ApplicationFramework;

public abstract class ClassyNodeFactory {
    abstract ClassyNode createChild(String ime);
    public  ClassyNode getChild(String ime){
        return createChild(ime);
    }
}
