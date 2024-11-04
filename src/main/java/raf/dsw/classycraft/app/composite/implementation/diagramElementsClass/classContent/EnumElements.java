package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeLeaf;

import java.awt.*;

public class EnumElements extends ClassContent{

    public EnumElements(ClassyNodeLeaf classyNodeLeaf, Paint paint, String name) {
        super(classyNodeLeaf, paint, name);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Methods)) return false;
//        EnumElements e = (EnumElements) o;
//        return this.getName().equals(e.getName());
//    }

    @Override
    public String toString() {
        return "Enum: " + this.getName();
    }
}
