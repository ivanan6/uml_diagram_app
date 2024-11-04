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

public class AddBrisanjeCommand extends AbstractCommand{
    private boolean flag = false;
    private ClassyTreeItem parent;
    private ClassyTreeItem child;
    private Interclass interclass;
    private ElementPainteri elementPainteri;
    private DiagramView diagramView;
    private List<ElementPainteri> elementPainteriList = new ArrayList<>();
    private List<ElementPainteri> listaVeza = new ArrayList<>();

    public AddBrisanjeCommand(ClassyTreeItem parent, ClassyTreeItem child, Interclass interclass, ElementPainteri elementPainteri, DiagramView diagramView) {
        this.parent = parent;
        this.child = child;
        this.interclass = interclass;
        this.elementPainteri = elementPainteri;
        this.diagramView = diagramView;

    }

    public void setListaVeza(List<ElementPainteri> listaVeza) {
        this.listaVeza = listaVeza;
    }

    public void setElementPainteriList(List<ElementPainteri> elementPainteriList) {
        this.elementPainteriList = elementPainteriList;
    }

    @Override
    public void doCommand() {
        if(flag == true){
            if(child == null || parent == null) return;
            child.removeFromParent();
            ((ClassyNodeComposite)(parent.getClassyNode())).deleteChild(child.getClassyNode());
            if(elementPainteri.getDiagramElements() instanceof Connection){
                diagramView.getPainteri().remove(elementPainteri);
                Interclass from = ((Connection)elementPainteri.getDiagramElements()).getFrom();
                Interclass to = ((Connection)elementPainteri.getDiagramElements()).getTo();
                from.getKonekcije().remove(elementPainteri.getDiagramElements());
                to.getKonekcije().remove(elementPainteri.getDiagramElements());
                diagramView.getDiagram().selektovano();
             flag = true;
            }
            else{
                for(ElementPainteri e : elementPainteriList){
                    diagramView.getPainteri().remove(e);
                }

                for(ElementPainteri e : listaVeza){
                    diagramView.getPainteri().remove(e);
                }
                if (interclass instanceof Klasa) {
                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                    ((Klasa) interclass).getKonekcije().clear();
                    ((Klasa) interclass).getAtributsList().clear();
                    diagramView.getPainteri().remove(elementPainteri);
                    diagramView.getDiagram().deleteChild(interclass);
                    //flag1 = true;
                } else if (interclass instanceof Interfejs) {

                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());

                    ((Interfejs) interclass).getKonekcije().clear();
                    ((Interfejs)interclass).getAtributsList().clear();
                    diagramView.getPainteri().remove(elementPainteri);
                    diagramView.getDiagram().deleteChild(interclass);
                    //flag = true;
                } else if (interclass instanceof Enumm) {


                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());

                    ((Enumm) interclass).getKonekcije().clear();
                    ((Enumm)interclass).getAtributsList().clear();
                    diagramView.getPainteri().remove(elementPainteri);
                    diagramView.getDiagram().deleteChild(interclass);
                }
                flag = true;
            }
            }


    }

    @Override
    public void undoCommand() {
        if(child == null || parent == null) return;
        parent.add(child);
        ((ClassyNodeComposite)parent.getClassyNode()).addChild(child.getClassyNode());

        if(elementPainteri.getDiagramElements() instanceof Connection){
            diagramView.getPainteri().add(elementPainteri);
            Interclass from = ((Connection)elementPainteri.getDiagramElements()).getFrom();
            Interclass to = ((Connection)elementPainteri.getDiagramElements()).getTo();
            from.getKonekcije().add((Connection) elementPainteri.getDiagramElements());
            to.getKonekcije().add((Connection) elementPainteri.getDiagramElements());
            diagramView.getDiagram().selektovano();
            this.setFlag(true);
        }
        else{
            this.diagramView.getPainteri().add(elementPainteri);
            for(ElementPainteri e : elementPainteriList){
                diagramView.getPainteri().add(e);
            }
            for(ElementPainteri e : listaVeza){
                diagramView.getPainteri().add(e);
            }
            this.setFlag(true);
            this.diagramView.getDiagram().addChild(interclass);
        }

    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
