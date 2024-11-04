package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;

import java.awt.*;
@Getter
@Setter
public class Agregacija extends Connection{
    private String vidljivost;
    private String kardinalnost;
    private String imePromenljive;
    public Agregacija(ClassyNode parent, String name, int x, int y, String kardinalnost,String imePromenljive) {
        super(parent, name, x, y);
        this.imePromenljive = imePromenljive;
        this.kardinalnost = kardinalnost;
        super.setStroke(new BasicStroke(1));
    }
}
