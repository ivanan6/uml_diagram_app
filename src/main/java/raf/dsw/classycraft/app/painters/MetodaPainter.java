package raf.dsw.classycraft.app.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Methods;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;
@Getter
@Setter

public class MetodaPainter extends ElementPainteri {
    private Methods methods;

    public MetodaPainter(String name, DiagramElements diagramElements, Methods methods) {
        super(name, diagramElements);
        this.methods = methods;


    }

    @Override
    public void draw(Graphics g, DiagramView diagramView) {
        if(this.methods.isAbstract()) {
            Font font = new Font(this.methods.toString(), Font.ITALIC, 13);
            g.setFont(font);
        }
        if(this.methods.isStatic()){

        }
        if(getDiagramElements() instanceof Klasa)
            g.drawString(this.methods.toString(), ((Interclass)getDiagramElements()).getX(), ((Interclass)getDiagramElements()).getY() + ((Klasa)getDiagramElements()).getBroj());
        else if(getDiagramElements() instanceof Interfejs)
            g.drawString(this.methods.toString(), ((Interclass)getDiagramElements()).getX(), ((Interclass)getDiagramElements()).getY() + ((Interfejs)getDiagramElements()).getBroj());

    }


    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        return false;
    }
}
