package raf.dsw.classycraft.app.composite.factory;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.Project;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.observer.message.MessageType;

import javax.swing.*;
import java.time.LocalDateTime;

public class DiagramFactory extends ClassyNodeFactory{
    private static int cnt = 1;
    private static int cnt2 = 1;
    private static int cnt3 = 1;
    @Override
    ClassyNode createChild(String ime) {
        ClassyNode classyNode = null;

//        if(ime.toLowerCase().equals("klasa")){
//            classyNode = new Klasa(ApplicationFramework.getInstance().getClassyRepository().getRoot(), "Klasa" + String.valueOf(cnt));
//            cnt++;
//        }
//        else if(ime.toLowerCase().equals("interface")){
//            classyNode = new Interfejs(ApplicationFramework.getInstance().getClassyRepository().getRoot(), "Intreface" + String.valueOf(cnt2));
//            cnt2++;
//        }
//        else {
//            classyNode = new Enumm(ApplicationFramework.getInstance().getClassyRepository().getRoot(), "Enum" + String.valueOf(cnt3));
//            cnt3++;
//        }



        return classyNode;
    }


}
