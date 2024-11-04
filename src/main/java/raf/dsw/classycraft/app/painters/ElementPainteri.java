package raf.dsw.classycraft.app.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

@Getter
@Setter

public abstract class ElementPainteri {

    //private Stroke stroke;
    private Rectangle rectangle;
    //private Paint paint;
    private DiagramElements diagramElements;
    private ClassyTreeItem classyTreeItem;
    private Shape shape;
    private String name;
//    private int x;
//    private int y;
//    private int width;
//    private int height;
//    private int suma = 20;
  final static float dash1[] = {10.0f};
//
//    private List<Point> tackeIcrtavanja = new ArrayList<>(4);
//
//    private int najveciWidth = 0;

    //private List<Integer> duzinaAtributa = new ArrayList<>();
    //BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

    public ElementPainteri(String name,DiagramElements diagramElements) {
        //this.stroke = dashed;
        this.name = name;
        this.diagramElements = diagramElements;
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
    }
//    public void povecajSumu(){
//        suma += 20;
//    }
//    public void umanjiSumu(){
//        suma -= 20;
//    }
    public void napraviRectangle(int x, int y, int width, int height){
        rectangle = new Rectangle(x, y, width + 10, height + 10);
    }
    public void setRectangle( int width, int height){
        this.rectangle.setSize(width + 30, height + 30);
    }


    public abstract void draw(Graphics g, DiagramView diagramView);
    public abstract boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri);
}
