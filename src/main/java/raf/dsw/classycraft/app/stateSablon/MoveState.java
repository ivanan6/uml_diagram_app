package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Connection;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.ElementPainteri;
import raf.dsw.classycraft.app.undo.MoveCommand;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveState implements State{
    private ElementPainteri painter;
    private List<Point> point2 = new ArrayList<>();
    @Override
    public void misKliknut(int x1, int y1, DiagramView diagramView) {
        Point point = new Point(x1,y1);
        if(diagramView.getScale()!=1){
            point = diagramView.getOriginalCoordinates(new Point(x1, y1));

        }
        else point = new Point(x1, y1);
        int x = point.x;
        int y = point.y;
        boolean flag = false;
        for(ElementPainteri e : diagramView.getSelectionModel()){
            if(e.getDiagramElements() instanceof Interclass)
                point2.add(new Point(x - ((Interclass)e.getDiagramElements()).getX(), y - ((Interclass)e.getDiagramElements()).getY()));
           /* else {
                point2.add(new Point(x - ((Connection)e.getDiagramElements()).getPocetnaTacka().x, y - ((Connection) e.getDiagramElements()).getPocetnaTacka().y));
                point2.add(new Point(x - ((Connection)e.getDiagramElements()).getKrajnjaTacka().x, y - ((Connection) e.getDiagramElements()).getKrajnjaTacka().y));

            }*/
            if(e.elementAt(point, diagramView, "selekcija", e)){
                flag = true;
                painter = e;

            }
        }
        if(!flag){
            diagramView.getSelectionModel().clear();
            diagramView.getDiagram().selektovano();
        }


    }

    @Override
    public void misPovucen(int x1, int y1, DiagramView diagramView) {
        Point point = new Point(x1,y1);
        if(diagramView.getScale()!=1){
            point = diagramView.getOriginalCoordinates(new Point(x1, y1));

        }
        else point = new Point(x1, y1);
        int x = point.x;
        int y = point.y;
        int index = 0;
        //System.out.println(point2.size());
        if(!(diagramView.getSelectionModel().isEmpty())){
            for (ElementPainteri e : diagramView.getSelectionModel()){
                if(e.getDiagramElements() instanceof Interclass){
                    Interclass i = (Interclass)e.getDiagramElements();
                    i.setX(x - point2.get(index).x);
                    i.setY(y - point2.get(index).y);
                    index++;
                    e.napraviRectangle(i.getX() - 10, i.getY() - 10, i.getWidth() + 30, i.getHeight() +30);
                    //diagramView.getDiagram().selektovano();

                } else if (e.getDiagramElements() instanceof Connection) {
                   ((Connection) e.getDiagramElements()).setFrom(((Connection) e.getDiagramElements()).getFrom());
                   ((Connection) e.getDiagramElements()).setTo(((Connection) e.getDiagramElements()).getTo());
                   //index += 2;
                }


            }                diagramView.getDiagram().selektovano();

        }
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        boolean flag = false;
        for(ElementPainteri e : diagramView.getSelectionModel()){
            if(e.getDiagramElements() instanceof Interclass){
                Point p = new Point(((Interclass) e.getDiagramElements()).getX(), ((Interclass) e.getDiagramElements()).getY());
                if(e.elementAt(p, diagramView, "move", e)){
                    flag = true;
                }
                if(flag){
                    break;
                }
            }

        }
        if(flag){
            for(ElementPainteri e : diagramView.getSelectionModel()){
                if(e.getDiagramElements() instanceof Interclass){
                    ((Interclass)e.getDiagramElements()).setX(((Interclass) e.getDiagramElements()).getStaroX());
                    ((Interclass)e.getDiagramElements()).setY(((Interclass) e.getDiagramElements()).getStaroY());


                }
                else{
                    ((Connection) e.getDiagramElements()).setFrom(((Connection) e.getDiagramElements()).getFrom());
                    ((Connection) e.getDiagramElements()).setTo(((Connection) e.getDiagramElements()).getTo());
                }
            }

        }

        else{
            MoveCommand command = new MoveCommand(diagramView);
            for(ElementPainteri e : diagramView.getSelectionModel()){
                if(e.getDiagramElements() instanceof Interclass){
                    Point point = new Point( ((Interclass)e.getDiagramElements()).getStaroX(),((Interclass)e.getDiagramElements()).getStaroY());
                    command.getStariPoints().add(point);
                    ((Interclass)e.getDiagramElements()).setStaroX(((Interclass) e.getDiagramElements()).getX());
                    ((Interclass)e.getDiagramElements()).setStaroY(((Interclass) e.getDiagramElements()).getY());
                    Point point2 = new Point(((Interclass)e.getDiagramElements()).getStaroX(),((Interclass)e.getDiagramElements()).getStaroY());
                    command.getNoviPoints().add(point2);
                    command.getElementPainteri().add(e);
                }
            }
            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
        }

        for(ElementPainteri e : diagramView.getSelectionModel()){
            e.setRectangle(null);
        }

        diagramView.getSelectionModel().clear();
        diagramView.getDiagram().selektovano();
    }
}
