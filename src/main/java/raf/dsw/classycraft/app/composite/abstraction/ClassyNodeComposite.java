package raf.dsw.classycraft.app.composite.abstraction;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.Project;
import raf.dsw.classycraft.app.composite.implementation.ProjectExplorer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Getter
@Setter

public abstract class ClassyNodeComposite extends ClassyNode{
    private List<ClassyNode> children;

    public ClassyNodeComposite(ClassyNode parent, String name) {
        super(parent, name);
        children = new ArrayList<>();
    }


    public abstract void addChild(ClassyNode child);
    public abstract void deleteChild(ClassyNode child);



}
