package raf.dsw.classycraft.app.composite.abstraction;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class ClassyNode {
    private ClassyNode parent;
    private String name;
    public ClassyNode(ClassyNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
