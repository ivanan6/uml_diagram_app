package raf.dsw.classycraft.app.composite.abstraction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class ClassyNodeLeaf extends ClassyNode{
    public ClassyNodeLeaf(ClassyNode parent, String name) {
        super(parent, name);
    }
}
