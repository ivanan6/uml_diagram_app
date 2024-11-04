package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public abstract class Interclass extends DiagramElements {
    private List<Connection> konekcije= new ArrayList<>();
    //private String vidljivost;
    //private List<Integer> tacke;
    private List<ClassContent> classContentList = new ArrayList<>();
    private int staroX;
    private int staroY;
    private int x;
    private int y;
    private int width;
    private int height;
    private int suma = 20;
    final static float dash1[] = {10.0f};
    private Paint paint;
    //////////////////////////////

    ///////////////////////////////

    private List<Point> tackeIcrtavanja = new ArrayList<>(4);

    private int najveciWidth = 0;
    private List<Integer> duzinaAtributa = new ArrayList<>();
    private BasicStroke stroke = new BasicStroke(1.0f);

    public Interclass(ClassyNode parent, String name, int x, int y, int width, int height,Paint paint) {
        super(parent, name);
        //this.vidljivost = vidljivost;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.paint = paint;
        this.staroY = y;
        this.staroX = x;
    }

    public Interclass(ClassyNode parent, String name) {
        super(parent, name);
    }
    public void povecajSumu(){
        suma += 20;
    }
    public void umanjiSumu(){
        suma -= 20;
    }

}
