package raf.dsw.classycraft.app.undo;

import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.EnumElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Methods;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.AtributPainter;
import raf.dsw.classycraft.app.painters.ElementPainteri;
import raf.dsw.classycraft.app.painters.EnumElementsPainter;
import raf.dsw.classycraft.app.painters.MetodaPainter;

public class BrisanjeElementCommand extends AbstractCommand{
    private ElementPainteri elementPainteri;
    private AtributPainter atributPainter;
    private MetodaPainter metodaPainter;
    private EnumElementsPainter enumElements;
    private DiagramView diagramView;
    private boolean flag = false;
    private Interclass interclass;
    private int sirinaPre;

    public BrisanjeElementCommand(ElementPainteri elementPainteri, DiagramView diagramView, Interclass interclass) {
        this.elementPainteri = elementPainteri;
        this.diagramView = diagramView;
        this.interclass = interclass;
    }
    public void setSirinaPre(int sirinaPre) {
        this.sirinaPre = sirinaPre;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setAtributPainter(AtributPainter atributPainter) {
        this.atributPainter = atributPainter;
    }

    public void setMetodaPainter(MetodaPainter metodaPainter) {
        this.metodaPainter = metodaPainter;
    }

    public void setEnumElements(EnumElementsPainter enumElements) {
        this.enumElements = enumElements;
    }

    @Override
    public void doCommand() {
        if(flag){
            diagramView.getPainteri().remove(elementPainteri);

            if(interclass instanceof Klasa){
                if(!(atributPainter == null)) {
                    ((Klasa) interclass).getAtributsList().remove(atributPainter.getAtribut());
                }
                else if(!(metodaPainter == null)) {
                    ((Klasa) interclass).getAtributsList().remove(metodaPainter.getMethods());
                }
                else if(!(enumElements == null)) {
                    ((Klasa) interclass).getAtributsList().remove(enumElements.getEnumElements());
                }
                int max = -1;
                for (int i = 0; i < interclass.getDuzinaAtributa().size(); i++) {
                    if (max < interclass.getDuzinaAtributa().get(i))
                        max = interclass.getDuzinaAtributa().get(i);
                }
                interclass.setNajveciWidth(max);
                ((Klasa) interclass).umanjiSumu();
                ((Klasa)interclass).setBroj(5);

            }
            else if(interclass instanceof Interfejs){
                if(!(metodaPainter == null)) {
                    ((Klasa) interclass).getAtributsList().remove(metodaPainter.getMethods());
                }
                int max = -1;
                for (int i = 0; i < interclass.getDuzinaAtributa().size(); i++) {
                    if (max < interclass.getDuzinaAtributa().get(i))
                        max = interclass.getDuzinaAtributa().get(i);
                }
                interclass.setNajveciWidth(max);
                ((Interfejs) interclass).umanjiSumu();
                ((Interfejs)interclass).setBroj(5);
            }
            else {
                if(!(enumElements == null)) {
                    ((Klasa) interclass).getAtributsList().remove(enumElements.getEnumElements());
                    int max = -1;
                    for (int i = 0; i < interclass.getDuzinaAtributa().size(); i++) {
                        if (max < interclass.getDuzinaAtributa().get(i))
                            max = interclass.getDuzinaAtributa().get(i);
                    }
                    interclass.setNajveciWidth(max);
                    ((Enumm) interclass).umanjiSumu();
                    ((Enumm)interclass).setBroj(5);
                }
            }
            diagramView.getDiagram().selektovano();
        }

    }

    @Override
    public void undoCommand() {
        this.flag = true;
        diagramView.getPainteri().add(elementPainteri);

        if(interclass instanceof Klasa){
            if(!(atributPainter == null)) {
                ((Klasa) interclass).getAtributsList().add(atributPainter.getAtribut());
                interclass.setNajveciWidth(sirinaPre);
                ((Klasa) interclass).povecajSumu();
            }
            else if(!(metodaPainter == null)) {
                ((Klasa) interclass).getAtributsList().add(metodaPainter.getMethods());
                interclass.setNajveciWidth(sirinaPre);
                interclass.povecajSumu();
            }
            else if(!(enumElements == null)) {
                ((Klasa) interclass).getAtributsList().add(enumElements.getEnumElements());
                interclass.setNajveciWidth(sirinaPre);
                interclass.povecajSumu();
            }
        }
        else if(interclass instanceof Interfejs){
            if(!(metodaPainter == null)) {
                ((Klasa) interclass).getAtributsList().add(metodaPainter.getMethods());
            }
        }
        else {
            if(!(enumElements == null)) {
                ((Klasa) interclass).getAtributsList().add(enumElements.getEnumElements());
            }
        }
        diagramView.getDiagram().selektovano();
    }
}
