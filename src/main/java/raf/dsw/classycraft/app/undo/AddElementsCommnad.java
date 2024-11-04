package raf.dsw.classycraft.app.undo;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.AtributPainter;
import raf.dsw.classycraft.app.painters.ElementPainteri;

public class AddElementsCommnad extends AbstractCommand{

    private ClassContent classContent;
    private ElementPainteri elementPainteri;
    private Interclass interclass;
    private DiagramView diagramView;
    private int sirinaPre;

    public AddElementsCommnad(ClassContent classContent, ElementPainteri elementPainteri, Interclass interclass, DiagramView diagramView) {
        this.classContent = classContent;
        this.elementPainteri = elementPainteri;
        this.interclass = interclass;
        this.diagramView = diagramView;
    }

    public void setSirinaPre(int sirinaPre) {
        this.sirinaPre = sirinaPre;
    }

    @Override
    public void doCommand() {
        if(interclass instanceof Klasa){
            if(!((Klasa)interclass).getAtributsList().contains(classContent)) {
                ((Klasa) interclass).getAtributsList().add(classContent);
                interclass.setNajveciWidth(sirinaPre);
                ((Klasa) interclass).povecajSumu();
            }
        } else if (interclass instanceof Enumm) {
            if(!((Enumm)interclass).getAtributsList().contains(classContent)) {
                ((Enumm) interclass).getAtributsList().add(classContent);
                interclass.setNajveciWidth(sirinaPre);
                interclass.povecajSumu();
            }
        }
        else {
            if(!((Interfejs)interclass).getAtributsList().contains(classContent)) {
                ((Interfejs) interclass).getAtributsList().add(classContent);
                interclass.setNajveciWidth(sirinaPre);
                interclass.povecajSumu();
            }
        }

        if(!(diagramView.getPainteri().contains(elementPainteri)))
             diagramView.getPainteri().add(elementPainteri);
        diagramView.getDiagram().selektovano();
    }

    @Override
    public void undoCommand() {
        if(interclass instanceof Klasa){
            ((Klasa)interclass).getAtributsList().remove(classContent);

            int max = -1;
            for (int i = 0; i < interclass.getDuzinaAtributa().size(); i++) {
                if (max < interclass.getDuzinaAtributa().get(i))
                    max = interclass.getDuzinaAtributa().get(i);
            }
            interclass.setNajveciWidth(max);
            ((Klasa) interclass).umanjiSumu();
            ((Klasa)interclass).setBroj(5);


        } else if (interclass instanceof Enumm) {
            ((Enumm)interclass).getAtributsList().remove(classContent);

            int max = -1;
            for (int i = 0; i < interclass.getDuzinaAtributa().size(); i++) {
                if (max < interclass.getDuzinaAtributa().get(i))
                    max = interclass.getDuzinaAtributa().get(i);
            }
            interclass.setNajveciWidth(max);
            ((Enumm) interclass).umanjiSumu();
            ((Enumm)interclass).setBroj(5);


        }
        else {
            ((Interfejs)interclass).getAtributsList().remove(classContent);

            int max = -1;
            for (int i = 0; i < interclass.getDuzinaAtributa().size(); i++) {
                if (max < interclass.getDuzinaAtributa().get(i))
                    max = interclass.getDuzinaAtributa().get(i);
            }
            interclass.setNajveciWidth(max);
            ((Interfejs) interclass).umanjiSumu();
            ((Interfejs)interclass).setBroj(5);


        }

        diagramView.getPainteri().remove(elementPainteri);
        diagramView.getDiagram().selektovano();
    }
}
