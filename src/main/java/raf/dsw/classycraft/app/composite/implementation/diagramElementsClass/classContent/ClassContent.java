package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeLeaf;

import java.awt.*;

@Getter
@Setter
public abstract class ClassContent extends ClassyNodeLeaf {
    private String name;
    private Paint paint;

    public ClassContent(ClassyNodeLeaf classyNodeLeaf, Paint paint,String name) {
        super(classyNodeLeaf, name);
        this.name = name;
    }
}
