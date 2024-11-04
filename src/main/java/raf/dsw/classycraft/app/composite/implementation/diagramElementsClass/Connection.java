package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;

import java.awt.*;

@Getter
@Setter

public abstract class Connection extends DiagramElements {
    private Interclass from;
    private Stroke stroke;
    private Point pocetnaTacka;
    private Point krajnjaTacka;
    private Interclass to;
    //private Point point;
    private Point endPoint;
    private Color color;
    private int x;
    private int y;
    public Connection(ClassyNode parent, String name, int x, int y) {
        super(parent, name);
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
