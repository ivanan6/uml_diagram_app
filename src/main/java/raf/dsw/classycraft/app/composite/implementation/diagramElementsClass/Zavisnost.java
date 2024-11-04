package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;

import java.awt.*;
@Getter
@Setter
public class Zavisnost extends Connection{
    final static float dash1[] = {10.0f};
    private String tip;
    public Zavisnost(ClassyNode parent, String name, int x, int y, String tip) {
        super(parent, name,x,y);
        this.tip = tip;
        super.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f));
    }
}
