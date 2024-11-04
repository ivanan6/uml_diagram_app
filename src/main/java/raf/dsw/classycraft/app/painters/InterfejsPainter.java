package raf.dsw.classycraft.app.painters;

import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

public class InterfejsPainter extends ElementPainteri {


    public InterfejsPainter(String name,DiagramElements diagramElements) {
        super(name, diagramElements);


    }
    @Override
    public void draw(Graphics g, DiagramView diagramView) {
        Interfejs k = ((Interfejs)getDiagramElements());
        Graphics2D g2 = (Graphics2D) g;
        ((Graphics2D)g).setStroke(k.getStroke());
        for(ClassContent atribut : k.getAtributsList()){
            Font font2 = new Font(atribut.toString(), Font.PLAIN, 13);
            FontMetrics fm = g.getFontMetrics(font2);
            int width = fm.stringWidth(atribut.toString());
            if(!((Interclass)getDiagramElements()).getDuzinaAtributa().contains(width)){
                ((Interclass)getDiagramElements()).getDuzinaAtributa().add(width);
            }
            if(((Interclass)getDiagramElements()).getNajveciWidth() == 0){
                ((Interclass)getDiagramElements()).setNajveciWidth(width);
                ((Interclass)getDiagramElements()).setWidth(width + 10);
            }
            if(((Interclass)getDiagramElements()).getNajveciWidth() <= width){
                ((Interclass)getDiagramElements()).setNajveciWidth(width);
                ((Interclass)getDiagramElements()).setWidth(width + 10);
            }
        }
        if(!(k.getWidth() == 250)){
            g.setColor((Color) k.getPaint());
            g.drawRect(k.getX(), k.getY(), k.getWidth(), k.getSuma());
            Font font2 = new Font(getName(), Font.PLAIN, 15);
            FontMetrics fm = g.getFontMetrics(font2);
            int width = fm.stringWidth(getName());
            g.drawString(k.getName(), (int) ((int)((2 * k.getX() + k.getWidth())) / 2) - width / 2, (k.getY() + 10));
            Point p1, p2, p3, p4;
            p1 = new Point((2 * k.getX() + k.getWidth()) / 2, k.getY());
            p2 = new Point(k.getX(), (2 * k.getY() + k.getSuma()) / 2);
            p3 = new Point(k.getX() + k.getWidth(), (2 * k.getY() + k.getSuma()) / 2);
            p4 = new Point((2 * k.getX() + k.getWidth()) / 2, k.getY() + k.getSuma());
            if(k.getTackeIcrtavanja().isEmpty()){
                k.getTackeIcrtavanja().add(p1);
                k.getTackeIcrtavanja().add(p2);
                k.getTackeIcrtavanja().add(p3);
                k.getTackeIcrtavanja().add(p4);
            }
            else{
                k.getTackeIcrtavanja().get(0).setLocation(p1);
                k.getTackeIcrtavanja().get(1).setLocation(p2);
                k.getTackeIcrtavanja().get(2).setLocation(p3);
                k.getTackeIcrtavanja().get(3).setLocation(p4);
            }
            if(this.getRectangle()!= null)
                this.setRectangle(k.getWidth(), k.getSuma());

        }
        else{
            g.setColor((Color) k.getPaint());
            g.drawRect(k.getX(), k.getY(), k.getWidth(), k.getHeight());
            Font font2 = new Font(getName(), Font.PLAIN, 15);
            FontMetrics fm = g.getFontMetrics(font2);
            int width = fm.stringWidth(getName());
            g.drawString(k.getName(), (int) ((int)((2 * k.getX() + k.getWidth())) / 2) - width / 2, (k.getY() + 10));
            Point p1, p2, p3, p4;
            p1 = new Point((2 * k.getX() + k.getWidth()) / 2, k.getY());
            p2 = new Point(k.getX(), (2 * k.getY() +k.getHeight()) / 2);
            p3 = new Point(k.getX() + k.getWidth(), (2 * k.getY() + k.getHeight()) / 2);
            p4 = new Point((2 * k.getX() + k.getWidth()) / 2, k.getY() + k.getHeight());
            k.getTackeIcrtavanja().clear();
            k.getTackeIcrtavanja().add(p1);
            k.getTackeIcrtavanja().add(p2);
            k.getTackeIcrtavanja().add(p3);
            k.getTackeIcrtavanja().add(p4);
        }

        if(this.getRectangle() != null){
            BasicStroke dashed2 = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
            ((Graphics2D) g).setStroke(dashed2);
            g.setColor(Color.blue);
            ((Graphics2D) g).draw(this.getRectangle());
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(1.0f));
        }
//        Interfejs e = ((Interfejs)getDiagramElements());
//        g.setColor((Color) e.getPaint());
//        g.drawRect(e.getX(), e.getY(), e.getWidth(), e.getHeight());
//        g.drawString(e.getName(), ((e.getX() + e.getWidth())) / 2, (e.getY() + 10));

    }



    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        Interfejs k = ((Interfejs)getDiagramElements());
        Rectangle r = new Rectangle();
        if(!(k.getWidth() == 250)){
            r.setSize(k.getWidth(), k.getSuma());
        }
        else r.setSize(k.getWidth(), k.getHeight());

        r.setLocation(k.getX(), k.getY());
        if(s.equals("duplicate")){
            Interfejs k1 = ((Interfejs)getDiagramElements());
            Rectangle r2 = new Rectangle();
            if(!(k1.getWidth() == 250)){
                r2.setSize(k1.getWidth(), k1.getSuma());
            }
            else r2.setSize(k1.getWidth(), k1.getHeight());

            r2.setLocation(k1.getX(), k1.getY());
            if(!(k1.getWidth() == 250)){;
                if(r2.contains(pos.x, pos.y) || r2.contains(pos.x + k1.getWidth() , pos.y) || r2.contains(pos.x, pos.y + k1.getSuma()) || r2.contains(pos.x + k1.getWidth(), pos.y + k1.getSuma())){
                    return true;
                }
            }
            else {
                if(r2.contains(pos.x, pos.y) || r2.contains(pos.x + k1.getWidth() , pos.y) || r2.contains(pos.x, pos.y + k1.getHeight()) || r2.contains(pos.x + k1.getWidth(), pos.y + k1.getHeight())){
                    return true;
                }
            }
        }



        else if(s.equals("selekcija")){
            if(r.contains(pos.x, pos.y) || r.contains(pos.x + 10, pos.y) || r.contains(pos.x, pos.y + 10) || r.contains(pos.x + 10, pos.y + 10))
                return true;
        }
        else if(s.equals("move")){
            for(ElementPainteri e: diagramView.getPainteri()){
                if(e.getDiagramElements() instanceof Interclass){
                    Interclass interclass = (Interclass) e.getDiagramElements();
                    if(!(e.getDiagramElements().equals(this.getDiagramElements()))){
                        if(!(k.getWidth() == 250)){
                            Rectangle r2 = new Rectangle();
                            if(!(interclass.getWidth() == 250)){
                                r2.setSize(interclass.getWidth(), interclass.getSuma());
                                r2.setLocation(interclass.getX(), interclass.getY());
                            }
                            else{
                                r2.setSize(interclass.getWidth(), interclass.getHeight());
                                r2.setLocation(interclass.getX(), interclass.getY());
                            }
                            if(r2.contains(k.getX(), k.getY()) || r2.contains(k.getX() + k.getWidth() , k.getY()) || r2.contains(k.getX(), k.getY() + k.getSuma()) || r2.contains(k.getX() + k.getWidth(), k.getY()+ k.getSuma())){
                                return true;
                            }
                        }
                        else{
                            if(interclass.getWidth() != 250){
                                if(r.contains(interclass.getX(), interclass.getY()) || r.contains(interclass.getX() + interclass.getWidth() , interclass.getY()) || r.contains(interclass.getX(), interclass.getY() + interclass.getSuma()) || r.contains(interclass.getX() + interclass.getWidth(), interclass.getY()+ interclass.getSuma())){
                                    return true;
                                }
                            }
                            else{
                                if(r.contains(interclass.getX(), interclass.getY()) || r.contains(interclass.getX() + interclass.getWidth() , interclass.getY()) || r.contains(interclass.getX(), interclass.getY() + interclass.getHeight()) || r.contains(interclass.getX() + interclass.getWidth(), interclass.getY()+ interclass.getHeight())){
                                    return true;
                                }
                            }
                        }
                    }



                }


            }
        }
        else{
            Interfejs k1 = ((Interfejs)getDiagramElements());
            Rectangle r2 = new Rectangle();

            r2.setSize(250,130);

            r2.setLocation(k1.getX(), k1.getY());
            if(!(k1.getWidth() == 250)){
                if(r2.contains(pos.x, pos.y) || r2.contains(pos.x + k1.getWidth() , pos.y) || r2.contains(pos.x , pos.y + k1.getSuma()) || r2.contains(pos.x + k1.getWidth(), pos.y + k1.getSuma())){
                    return true;
                }
            }
            else {
                if(r2.contains(pos.x, pos.y) || r2.contains(pos.x + k1.getWidth() , pos.y) || r2.contains(pos.x , pos.y + k1.getHeight()) || r2.contains(pos.x + k1.getWidth(), pos.y + k1.getHeight())){
                    return true;
                }
            }
        }
        return false;

    }
}
