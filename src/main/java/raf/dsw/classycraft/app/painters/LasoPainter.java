package raf.dsw.classycraft.app.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Connection;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LasoPainter extends ElementPainteri {
    private List<ElementPainteri> painteriList = new ArrayList<>();
    private Point endPoint;
    private Point firstPoint;
    private Paint paint;
    private int x;
    private int y;
    private int width;
    private int height;


    public LasoPainter(Paint paint, String name, DiagramElements diagramElements, int x, int y, int width, int height) {
        super(name, diagramElements);
        this.paint = paint;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    @Override
    public void draw(Graphics g, DiagramView diagramView) {
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke dashed2 = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
        g2.setStroke(dashed2);
        g2.drawRect(firstPoint.x, firstPoint.y, endPoint.x - firstPoint.x, endPoint.y - firstPoint.y);
    }

    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        Rectangle r = new Rectangle(firstPoint.x, firstPoint.y, endPoint.x - firstPoint.x, endPoint.y - firstPoint.y);
        if(elementPainteri.getDiagramElements() instanceof Interclass){
            Interclass i = (Interclass)elementPainteri.getDiagramElements();
            if(((Interclass)elementPainteri.getDiagramElements()).getWidth() != 250){
                if (r.contains(i.getX(), i.getY()) || r.contains(i.getX() + i.getWidth(), i.getY()) || r.contains(i.getX(), i.getY() + i.getSuma()) || r.contains(i.getX() + i.getWidth(), i.getY() + i.getSuma())) {
                    return true;
                }
            }
            else{
                if (r.contains(i.getX(), i.getY()) || r.contains(i.getX() + i.getWidth(), i.getY()) || r.contains(i.getX(), i.getY() + i.getHeight()) || r.contains(i.getX() + i.getWidth(), i.getY() + i.getHeight())) {
                    return true;
                }
            }
        }
        else if(elementPainteri.getDiagramElements() instanceof Connection){
            Connection c = (Connection) elementPainteri.getDiagramElements();
            if(r.contains(c.getPocetnaTacka().x, c.getPocetnaTacka().y) || r.contains(c.getKrajnjaTacka().x, c.getKrajnjaTacka().y))
                return true;
        }
        return false;
    }
}
