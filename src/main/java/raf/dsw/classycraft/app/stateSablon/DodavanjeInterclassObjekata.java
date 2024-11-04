package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.factory.ClassyNodeFactory;
import raf.dsw.classycraft.app.composite.factory.FactoryUtil;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.painters.ElementPainteri;
import raf.dsw.classycraft.app.painters.EnumPainter;
import raf.dsw.classycraft.app.painters.InterfejsPainter;
import raf.dsw.classycraft.app.painters.KlasaPainter;
import raf.dsw.classycraft.app.undo.AddInterClassObjekatCommand;

import javax.swing.*;
import java.awt.*;

public class DodavanjeInterclassObjekata implements State{
    private String s;
    private static int cnt = 1;
    private static int cnt2 = 1;
    private static int cnt3 = 1;

    public DodavanjeInterclassObjekata(String s) {
        this.s = s;
    }

    public void misKliknut(int x1, int y1, DiagramView diagramView){
        DiagramElements diagramElements = null;
        Diagram d = diagramView.getDiagram();
        ElementPainteri elementPainteri = null;
        Point point = new Point(x1,y1);
        if(diagramView.getScale()!=1){
            point = diagramView.getOriginalCoordinates(new Point(x1, y1));

        }
        else point = new Point(x1, y1);
        int x = point.x;
        int y = point.y;
        boolean flag = false;
        for(ElementPainteri element : diagramView.getPainteri()){

           // point = new Point(x, y);
            if(element.elementAt(point, diagramView, "", element)){
                flag = true;
            }
            if (flag)
                break;
        }

        if(!flag){
            if(s.toLowerCase().equals("klasa")){
               diagramElements = new Klasa(d, "Klasa" + String.valueOf(cnt), x,y ,250,130,Color.black);
                cnt++;
                elementPainteri = new KlasaPainter(s,diagramElements);
                diagramView.getPainteri().add(elementPainteri);
                d.addChild(diagramElements);
            }
            else if(s.toLowerCase().equals("interface")){
                diagramElements = new Interfejs(d, "Interfejs" + String.valueOf(cnt2),x,y,250,130,Color.black);
                cnt2++;
                elementPainteri = new InterfejsPainter(s,diagramElements);
                diagramView.getPainteri().add(elementPainteri);
                d.addChild(diagramElements);

            }
            else {
                diagramElements = new Enumm(d, "Enum" + String.valueOf(cnt3),x,y,250,130,Color.black);
                cnt3++;
                elementPainteri = new EnumPainter(s,diagramElements);
                diagramView.getPainteri().add(elementPainteri);
                d.addChild(diagramElements);
            }
            ClassyTreeItem nova = new ClassyTreeItem(diagramView.getDiagram());
            int childCount = diagramView.getClassyTreeItem().getChildCount();
            boolean flag1 = false;
            for (int i1 = 0; i1 < childCount; i1++) {
                ClassyTreeItem childNode = (ClassyTreeItem) diagramView.getClassyTreeItem().getChildAt(i1);
                if (childNode.getClassyNode().equals(diagramView.getDiagram())) {
                    nova = childNode;
                    flag1 = true;
                }
            }
            ClassyTreeItem c = null;
            if(flag1==true){
                 c = new ClassyTreeItem(diagramElements);
                nova.add(c);
                elementPainteri.setClassyTreeItem(c);
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
            }
                AddInterClassObjekatCommand command = new AddInterClassObjekatCommand((ClassyTreeItem) c.getParent(), c, (Interclass) diagramElements, elementPainteri, diagramView);
                ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);




        }
    }
    public void misPovucen(int x, int y, DiagramView diagramView){

    }
    public void misOtpusten(int x, int y, DiagramView diagramView){

    }
}
