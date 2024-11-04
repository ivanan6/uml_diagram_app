package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Connection;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.ElementPainteri;
import raf.dsw.classycraft.app.painters.KlasaPainter;
import raf.dsw.classycraft.app.painters.LasoPainter;

import java.awt.*;

public class Selekcija implements State{
    private LasoPainter lasoPainter = null;
    private Point point;
    public void misKliknut(int x2, int y2, DiagramView diagramView){
        boolean flag = false;
        ElementPainteri element = null;
        String s = "selekcija";
        point = new Point(x2,y2);
        if(diagramView.getScale()!=1){
            point = diagramView.getOriginalCoordinates(new Point(x2, y2));

        }
        else
            point = new Point(x2, y2);
        int x = point.x;
        int y = point.y;
        for(ElementPainteri elementPainteri : diagramView.getPainteri()){
            //Point point = new Point(x, y);
            if(elementPainteri.elementAt(point, diagramView, s, elementPainteri)){
                flag = true;
                element = elementPainteri;
            }
            if(flag)
                break;
        }
        if(flag){
            if(element.getDiagramElements() instanceof Interclass){
                diagramView.getSelectionModel().add(element);
                Interclass i = ((Interclass)element.getDiagramElements());
                if(((Interclass)element.getDiagramElements()).getSuma() == 20){
                    element.napraviRectangle(i.getX() - 10, i.getY() - 10, i.getWidth() + 30, i.getHeight() + 20);
                }else {
                    element.napraviRectangle(i.getX() - 10, i.getY() - 10, i.getWidth() + 30, i.getSuma() + 20);
                }
                diagramView.getDiagram().selektovano();
            } else if (element.getDiagramElements() instanceof Connection) {
                Connection c = (Connection) element.getDiagramElements();
                int x1 = Math.min(c.getPocetnaTacka().x, c.getKrajnjaTacka().x);
                int y1 = Math.min(c.getPocetnaTacka().y, c.getKrajnjaTacka().y);
                int width = Math.abs(c.getPocetnaTacka().x - c.getKrajnjaTacka().x);
                int height = Math.abs(c.getPocetnaTacka().y - c.getKrajnjaTacka().y);

                element.napraviRectangle(x1,y1, width, height);
                diagramView.getSelectionModel().add(element);
                diagramView.getDiagram().notifySubscriber("", "crtanje");
            }


        }
        else if(flag == false){
            for(ElementPainteri e : diagramView.getSelectionModel()){
                e.setRectangle(null);
                diagramView.getDiagram().selektovano();
            }
            diagramView.getSelectionModel().clear();

        }
        point = new Point(x, y);

    }
    public void misPovucen(int x3, int y3, DiagramView diagramView){
        Point point = new Point(x3,y3);
        if(diagramView.getScale()!=1){
            point = diagramView.getOriginalCoordinates(new Point(x3, y3));

        }
        else point = new Point(x3, y3);
        int x = point.x;
        int y = point.y;
        if(lasoPainter == null){
            lasoPainter = new LasoPainter(Color.BLUE, "laso", null, x, y, 0, 0);
            lasoPainter.setFirstPoint(point);
            diagramView.getPainteri().add(lasoPainter);
        }
        lasoPainter.setEndPoint(new Point(x, y));
        boolean flag = false;
        ElementPainteri element = null;
        String s = "laso";
        for(ElementPainteri elementPainteri : diagramView.getPainteri()){
            //Point point = new Point(x, y);// ovo nam je sad nebitno
            if(lasoPainter.elementAt(point, diagramView, s, elementPainteri)){
                lasoPainter.getPainteriList().add(elementPainteri);
                if(!(diagramView.getSelectionModel().contains(elementPainteri)))
                    diagramView.getSelectionModel().add(elementPainteri);
                element = elementPainteri;

                if(element.getDiagramElements() instanceof Interclass){
                    Interclass i = (Interclass)elementPainteri.getDiagramElements();
                    if(i.getWidth() != 250)
                        element.napraviRectangle(i.getX() - 10, i.getY() - 10, i.getWidth() + 30, i.getSuma() + 30);
                    else
                        element.napraviRectangle(i.getX() - 10, i.getY() - 10, i.getWidth() + 30, i.getHeight() + 30);
                    diagramView.getDiagram().notifySubscriber("", "crtanje");
                }
                else{
                    Connection c = (Connection) elementPainteri.getDiagramElements();
                        int x1 = Math.min(c.getPocetnaTacka().x, c.getKrajnjaTacka().x);
                        int y1 = Math.min(c.getPocetnaTacka().y, c.getKrajnjaTacka().y);
                        int width = Math.abs(c.getPocetnaTacka().x - c.getKrajnjaTacka().x);
                        int height = Math.abs(c.getPocetnaTacka().y - c.getKrajnjaTacka().y);

                    element.napraviRectangle(x1,y1, width, height);
                    diagramView.getDiagram().notifySubscriber("", "crtanje");
                }

            }
            else{
                elementPainteri.setRectangle(null);
                diagramView.getDiagram().notifySubscriber("","crtanje");
            }

        }



    }
    public void misOtpusten(int x, int y, DiagramView diagramView){

        diagramView.getPainteri().remove(lasoPainter);
        lasoPainter = null;
        diagramView.getDiagram().notifySubscriber("", "crtanje");
    }
}
