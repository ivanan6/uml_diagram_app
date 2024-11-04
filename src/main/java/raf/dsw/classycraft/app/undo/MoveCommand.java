package raf.dsw.classycraft.app.undo;

import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.ElementPainteri;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveCommand extends AbstractCommand{

    private List<ElementPainteri> elementPainteri = new ArrayList<>();
    private Interclass interclass;
    private List<Point> stariPoints = new ArrayList<>();
    private List<Point> noviPoints = new ArrayList<>();
    private DiagramView diagramView;

    public MoveCommand( DiagramView diagramView) {
        this.diagramView = diagramView;
    }

    public List<ElementPainteri> getElementPainteri() {
        return elementPainteri;
    }

    public List<Point> getStariPoints() {
        return stariPoints;
    }

    public List<Point> getNoviPoints() {
        return noviPoints;
    }

    @Override
    public void doCommand() {
        int index = 0;
        for(ElementPainteri e : this.getElementPainteri()){

            int x = (int) this.getNoviPoints().get(index).getX();
            int y =  (int) this.getNoviPoints().get(index).getY();
            ((Interclass)e.getDiagramElements()).setX(x);
            ((Interclass)e.getDiagramElements()).setY(y);
            index++;
        }
        diagramView.getDiagram().selektovano();
    }

    @Override
    public void undoCommand() {
        int index = 0;
        for(ElementPainteri e : this.getElementPainteri()){

            int x = (int) this.getStariPoints().get(index).getX();
            int y =  (int) this.getStariPoints().get(index).getY();
                ((Interclass)e.getDiagramElements()).setX(x);
                ((Interclass)e.getDiagramElements()).setY(y);
            index++;
        }
        diagramView.getDiagram().selektovano();
    }
}
