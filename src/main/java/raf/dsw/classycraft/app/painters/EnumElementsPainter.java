package raf.dsw.classycraft.app.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.EnumElements;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;
@Getter
@Setter
public class EnumElementsPainter extends ElementPainteri {
    private EnumElements enumElements;

    public EnumElementsPainter(String name,DiagramElements diagramElements, EnumElements enumElements) {
        super(name, diagramElements);
        this.enumElements = enumElements;

    }

    @Override
    public void draw(Graphics g, DiagramView diagramView) {
        if(getDiagramElements() instanceof Klasa)
          g.drawString(this.enumElements.toString(), ((Interclass)getDiagramElements()).getX(), ((Interclass)getDiagramElements()).getY() + ((Klasa)getDiagramElements()).getBroj());
        else if (getDiagramElements() instanceof Enumm) {
            g.drawString(this.enumElements.toString(), ((Interclass)getDiagramElements()).getX(), ((Interclass)getDiagramElements()).getY() + ((Enumm)getDiagramElements()).getBroj());
        }
    }


    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        return false;
    }
}
