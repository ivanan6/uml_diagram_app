package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;

import java.awt.*;
@Getter
@Setter
public class Kompozicija extends Connection{
    private String kardinalnost;
    private String imePromeljive;
    public Kompozicija(ClassyNode parent, String name,int x, int y, String kardinalnost, String imePromeljive) {
        super(parent, name,x,y);
        this.kardinalnost = kardinalnost;
        this.imePromeljive = imePromeljive;
        super.setStroke(new BasicStroke(1));
    }
}
