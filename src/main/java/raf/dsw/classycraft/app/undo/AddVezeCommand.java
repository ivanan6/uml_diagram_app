package raf.dsw.classycraft.app.undo;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Connection;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.painters.ElementPainteri;

import javax.swing.*;

public class AddVezeCommand extends AbstractCommand {
    private ClassyTreeItem parent;
    private ClassyTreeItem child;
    private Interclass interclass;
    private ElementPainteri elementPainteri;
    private DiagramView diagramView;

    public AddVezeCommand(ElementPainteri elementPainteri, DiagramView diagramView) {
        this.elementPainteri = elementPainteri;
        this.diagramView = diagramView;
    }

    @Override
    public void doCommand() {
        if(!(diagramView.getPainteri().contains(elementPainteri))){
            diagramView.getPainteri().add(elementPainteri);
            Interclass from = ((Connection)elementPainteri.getDiagramElements()).getFrom();
            Interclass to = ((Connection)elementPainteri.getDiagramElements()).getTo();
            from.getKonekcije().add((Connection) elementPainteri.getDiagramElements());
            to.getKonekcije().add((Connection) elementPainteri.getDiagramElements());
            diagramView.getDiagram().selektovano();
        }

    }

    @Override
    public void undoCommand() {
       diagramView.getPainteri().remove(elementPainteri);
        Interclass from = ((Connection)elementPainteri.getDiagramElements()).getFrom();
        Interclass to = ((Connection)elementPainteri.getDiagramElements()).getTo();
        from.getKonekcije().remove(elementPainteri.getDiagramElements());
        to.getKonekcije().remove(elementPainteri.getDiagramElements());
       diagramView.getDiagram().selektovano();
    }
}
