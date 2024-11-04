package raf.dsw.classycraft.app.undo;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.*;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.painters.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DuplicateCommand extends AbstractCommand{
    private ClassyTreeItem parent;
    private ClassyTreeItem child;
    private List<ElementPainteri> elementPainteriList = new ArrayList<>();
    private ElementPainteri element;
    private DiagramView diagramView;
    private Interclass interclass;
    public DuplicateCommand( DiagramView diagramView) {
        this.diagramView = diagramView;
    }

    public void setElement(ElementPainteri element) {
        this.element = element;
        child = element.getClassyTreeItem();
        parent = diagramView.getClassyTreeItem();
    }

    public void setInterclass(Interclass interclass) {
        this.interclass = interclass;
    }

    public List<ElementPainteri> getElementPainteriList() {
        return elementPainteriList;
    }

    @Override
    public void doCommand() {
        if(!(this.diagramView.getPainteri().contains(element))) {
            this.diagramView.getPainteri().add(element);
            for (ElementPainteri e : elementPainteriList) {
                diagramView.getPainteri().add(e);
            }

            this.diagramView.getDiagram().addChild(interclass);
        }
    }

    @Override
    public void undoCommand() {


        if (interclass instanceof Klasa) {
            for(ElementPainteri e : elementPainteriList){
                diagramView.getPainteri().remove(e);
            }
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());

            ((Klasa) interclass).getKonekcije().clear();
            ((Klasa) interclass).getAtributsList().clear();
            diagramView.getPainteri().remove(element);
            diagramView.getDiagram().deleteChild(interclass);

        } else if (interclass instanceof Interfejs) {
            for(ElementPainteri e : elementPainteriList){
                diagramView.getPainteri().remove(e);
            }
                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());


            ((Interfejs) interclass).getKonekcije().clear();
            ((Interfejs)interclass).getAtributsList().clear();
            diagramView.getPainteri().remove(element);
            diagramView.getDiagram().deleteChild(interclass);
            //flag = true;
        } else if (interclass instanceof Enumm) {
            for(ElementPainteri e : elementPainteriList){
                diagramView.getPainteri().remove(e);
            }
            ((Enumm) interclass).getKonekcije().clear();
            ((Enumm)interclass).getAtributsList().clear();
            diagramView.getPainteri().remove(element);
            diagramView.getDiagram().deleteChild(interclass);
        }
    }
}
