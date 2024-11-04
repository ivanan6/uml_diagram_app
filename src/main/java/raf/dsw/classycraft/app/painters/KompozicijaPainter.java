package raf.dsw.classycraft.app.painters;

import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Connection;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class KompozicijaPainter extends ElementPainteri {

    public KompozicijaPainter(String name,DiagramElements diagramElements) {
        super(name, diagramElements);


    }

    @Override
    public void draw(Graphics g, DiagramView diagramView) {
        Connection c = (Connection) getDiagramElements();
        if(c.getTo() == null){
            g.setColor(Color.BLACK);
            g.drawLine(c.getX(), c.getY(), c.getEndPoint().x, c.getEndPoint().y);
        }
        else{
            Point point1 = null;
            Point point2 = null;
            int najkraca = Integer.MAX_VALUE;
            for(Point p : c.getFrom().getTackeIcrtavanja()){
                for(Point p2 :c.getTo().getTackeIcrtavanja()){
                    int broj = (int) Math.sqrt((p2.x - p.x) * (p2.x - p.x) + (p2.y - p.y) * (p2.y - p.y));
                    if(najkraca > broj){
                        najkraca = broj;
                        point1 = new Point(p);
                        point2 = new Point(p2);
                    }
                }
            }
            c.setPocetnaTacka(point1);
            c.setKrajnjaTacka(point2);
            ((Graphics2D)g).setStroke(c.getStroke());
            //g.drawLine(point1.x, point1.y, point2.x, point2.y);

            Line2D line2D = new Line2D.Double(point1.x, point1.y, point2.x, point2.y);
            setShape(line2D);
            g.drawLine(point1.x, point1.y, point2.x, point2.y);
            Path2D.Double path = new Path2D.Double ();
            path.moveTo (point1.x, point1.y) ;
            double x = point1.x + 6;
            double y = point1.y - 6;
            path.lineTo(x, y);
            double x1 = point1.x + 12;
            double y1 = point1.y;
            path.lineTo(x1,y1);
            double x2 = point1.x + 6;
            double y2 = point1.y + 6;
            path.lineTo(x2,y2) ;
            path.closePath();

            AffineTransform at = new AffineTransform();
            double ang = Math.atan2(point2.y - point1.y, point2.x-point1.x);
            at.rotate (ang, point1.x,point1.y) ;
            path.transform(at);
            g.setColor(Color.BLACK);
            ((Graphics2D)g).fill(path);



        }
        if(this.getRectangle() != null){
            Connection c1 = (Connection) getDiagramElements();
            int x1 = Math.min(c1.getPocetnaTacka().x, c1.getKrajnjaTacka().x);
            int y1 = Math.min(c1.getPocetnaTacka().y, c1.getKrajnjaTacka().y);
            int width = Math.abs(c1.getPocetnaTacka().x - c1.getKrajnjaTacka().x);
            int height = Math.abs(c1.getPocetnaTacka().y - c1.getKrajnjaTacka().y);

            this.napraviRectangle(x1,y1, width, height);
            BasicStroke dashed2 = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
            ((Graphics2D) g).setStroke(dashed2);
            g.setColor(Color.blue);
            ((Graphics2D) g).draw(this.getRectangle());
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(1.0f));
        }
    }


    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        if(s.equals("selekcija")){
            Connection c = (Connection) elementPainteri.getDiagramElements();
            double distance = ((Line2D)getShape()).ptSegDist(pos.x, pos.y);
            return distance <= 5;
        }
        else{
            int x1;
            int y1;
            int width;
            int height;
            Connection c = (Connection) elementPainteri.getDiagramElements();
            if(c.getKrajnjaTacka() != null){
                x1 = Math.min(c.getPocetnaTacka().x, c.getKrajnjaTacka().x);
                y1 = Math.min(c.getPocetnaTacka().y, c.getKrajnjaTacka().y);
                width = Math.abs(c.getPocetnaTacka().x - c.getKrajnjaTacka().x);
                height = Math.abs(c.getPocetnaTacka().y - c.getKrajnjaTacka().y);
                Rectangle r = new Rectangle(x1, y1, width, height);
                Rectangle r2 = new Rectangle(pos.x, pos.y, pos.x + 10, pos.y + 10);
                if(r.intersects(r2))
                    return true;
            }
            return false;
        }
    }
}
