package raf.dsw.classycraft.app.undo;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.ElementPainteri;

import java.util.ArrayList;
import java.util.List;

public class MultiselectionBrisanjeCommand extends AbstractCommand{
    private DiagramView diagramView;
    private List<ElementPainteri> sadrzaj = new ArrayList<>();
    private boolean flag = false;
    private List<ElementPainteri> elementPainteriList;

    public MultiselectionBrisanjeCommand(DiagramView diagramView) {
        this.diagramView = diagramView;
    }

    public void setElementPainteriList(List<ElementPainteri> elementPainteriList) {
        this.elementPainteriList = elementPainteriList;
    }

    public List<ElementPainteri> getSadrzaj() {
        return sadrzaj;
    }

    @Override
    public void doCommand() {
        if(flag){
            diagramView.getPainteri().removeAll(elementPainteriList);
            diagramView.getPainteri().removeAll(sadrzaj);
            diagramView.getDiagram().selektovano();
        }
    }

    @Override
    public void undoCommand() {
        diagramView.getPainteri().addAll(elementPainteriList);
        //diagramView.getPainteri().addAll(sadrzaj);
        diagramView.getDiagram().selektovano();
        this.flag = true;
    }
}
