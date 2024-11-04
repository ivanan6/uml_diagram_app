package raf.dsw.classycraft.app.painters;

import lombok.Getter;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

@Getter
public class AtributPainter extends ElementPainteri {
    private Atributs atribut;
    //private ElementPainteri elementPainteri;


    public AtributPainter(String name, DiagramElements diagramElements, Atributs atributs) {
        super(name, diagramElements);
        this.atribut = atributs;
        //this.elementPainteri = elementPainteri;


    }

    @Override
    public void draw(Graphics g, DiagramView diagramView) {

        if(this.atribut.isAbstract()) {
            Font font = new Font(this.atribut.toString(), Font.ITALIC, 13);
            g.setFont(font);
        }
        if(this.atribut.isStatic()){

        }
        g.drawString(this.atribut.toString(), ((Interclass)getDiagramElements()).getX(), ((Interclass)getDiagramElements()).getY() + ((Klasa)getDiagramElements()).getBroj());

    }


    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        return false;
    }
}
