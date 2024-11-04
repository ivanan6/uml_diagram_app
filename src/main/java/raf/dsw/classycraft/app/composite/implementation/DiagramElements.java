package raf.dsw.classycraft.app.composite.implementation;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeLeaf;

public abstract class DiagramElements extends ClassyNodeLeaf {

    private int Stroke;
    private String color;

    public DiagramElements(ClassyNode parent, String name) {
        super(parent, name);
    }
}
