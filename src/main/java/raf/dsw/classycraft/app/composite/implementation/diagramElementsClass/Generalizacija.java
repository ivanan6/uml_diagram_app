package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;

import java.awt.*;
@Setter
@Getter
public class Generalizacija extends Connection{

    public Generalizacija(ClassyNode parent, String name,int x,int y) {
        super(parent, name,x,y);
        super.setStroke(new BasicStroke(1));
    }
}
