package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.*;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.EnumElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Methods;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.message.MessageType;
import raf.dsw.classycraft.app.painters.ElementPainteri;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class MenjanjeSadrzaja implements State{
    @Override
    public void misKliknut(int x1, int y1, DiagramView diagramView) {
        boolean flag = false;
        ElementPainteri element = null;
        String s = "selekcija";
        Point point = new Point(x1,y1);
        if(diagramView.getScale()!=1){
            point = diagramView.getOriginalCoordinates(new Point(x1, y1));

        }
        else point = new Point(x1, y1);
        int x = point.x;
        int y = point.y;
        for(ElementPainteri elementPainteri : diagramView.getPainteri()){
            //Point point = new Point(x, y);
            if(elementPainteri.elementAt(point, diagramView, s, elementPainteri)){
                flag = true;
                element = elementPainteri;
            }
            if(flag)
                break;
        }
        if(flag){
            if(element.getDiagramElements() instanceof Interclass) {
                if (element.getDiagramElements() instanceof Klasa) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    Object[] selectionValues = new Object[100];
                    Object[] lista = new Object[100];
                    int i;
                    for (i = 0; i < ((Klasa) element.getDiagramElements()).getAtributsList().size(); i++) {
                        String rec = ((Klasa) element.getDiagramElements()).getAtributsList().get(i).toString();
                        lista[i] = ((Klasa) element.getDiagramElements()).getAtributsList().get(i);
                        selectionValues[i] = rec;
                    }
                    selectionValues[i] = element.getDiagramElements().getName();
                    lista[i] = element.getDiagramElements();
                    Object basicSelection = selectionValues[0];

                    Object selection = JOptionPane.showInputDialog(null, "Sta zelite da promenite?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                    while (selection == null) {
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                        selection = JOptionPane.showInputDialog(null, "Sta zelite da promenite?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                    }
                    Object classContent = null;
                    for (int j = 0; j < lista.length; j++) {
                        if (lista[j].toString().equals(selection)) {
                            classContent = lista[j];
                            break;
                        }

                    }
                    if (classContent instanceof Klasa) {
                        JFrame f = new JFrame();
                        String name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        while (name == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        }
                        ((Klasa) classContent).setName(name);
                        diagramView.getDiagram().selektovano();
                    } else if (classContent instanceof Atributs) {
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues5 = {"String", "int", "double", "float", "boolean"};
                        String basicSelection5 = "String";
                        Object selection5 = JOptionPane.showInputDialog(null, "Koji tip atributa zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues5, basicSelection5);
                        while (selection5 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection5 = JOptionPane.showInputDialog(null, "Koji tip atributa zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues5, basicSelection5);
                        }
                        ((Atributs) classContent).setTip((String) selection5);
                        JFrame f = new JFrame();
                        String name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        while (name == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        }
                        ((Atributs) classContent).setName(name);
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues2 = {"public", "private", "protected", "default"};
                        String basicSelection2 = "public";
                        Object selection2 = JOptionPane.showInputDialog(null, "Koju vidljivost zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
                        while (selection2 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection2 = JOptionPane.showInputDialog(null, "Koju vidljivost zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
                        }
                        ((Atributs) classContent).setVidljivost(selection2.toString());

                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues3 = {"static", "nista"};
                        String basicSelection3 = "nista";
                        Object selection3 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete static?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
                        while (selection3 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection3 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete static?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
                        }
                        if (!(selection3.toString().equals("nista")))
                            ((Atributs) classContent).setStatic(true);
                        else ((Atributs) classContent).setStatic(false);
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues4 = {"apstrakt", "nista"};
                        String basicSelection4 = "nista";
                        Object selection4 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete abstrakt?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues4, basicSelection4);
                        while (selection4 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection4 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete abstrakt?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues4, basicSelection4);
                        }
                        if (!(selection4.toString().equals("nista")))
                            ((Atributs) classContent).setAbstract(true);
                        else ((Atributs) classContent).setAbstract(false);
                        diagramView.getDiagram().selektovano();
                    } else if (classContent instanceof Methods) {
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues6 = {"void", "int", "String", "boolean", "float", "double"};
                        String basicSelection6 = "void";
                        Object selection6 = JOptionPane.showInputDialog(null, "Koji povratni ti metode zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues6, basicSelection6);
                        while (selection6 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection6 = JOptionPane.showInputDialog(null, "Koji povratni ti metode zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues6, basicSelection6);
                        }
                        ((Methods) classContent).setTip(selection6.toString());
                        JFrame f = new JFrame();
                        String name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        while (name == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        }
                        ((Methods) classContent).setName(name);
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        String kraj = null;
                        Object[] selectionValues9 = {"int", "String", "boolean", "float", "double", "Object", "nista"};
                        String basicSelection9 = "int";
                        Object selection9 = JOptionPane.showInputDialog(null, "Koji tip ulaznog argumenta zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues9, basicSelection9);
                        while (selection9 == null || selection9 != "nista") {
                            if (selection9 != null) {
                                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Nastavite da birate tip argumenta", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                                kraj += selection9.toString() + ",";
                                selection9 = JOptionPane.showInputDialog(null, "Koji tip ulaznog argumenta zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues9, basicSelection9);
                            } else {
                                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                                selection9 = JOptionPane.showInputDialog(null, "Koji tip ulaznog argumenta zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues9, basicSelection9);
                            }
                        }
                        ((Methods) classContent).getUlazniElementi().clear();
                        String rec[] = kraj.split(",");
                        for (int m = 0; m < rec.length - 1; m++) {
                            ((Methods) classContent).getUlazniElementi().add(rec[m]);
                        }
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues2 = {"public", "private", "protected", "default"};
                        String basicSelection2 = "public";
                        Object selection2 = JOptionPane.showInputDialog(null, "Koju vidljivost zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
                        while (selection2 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection2 = JOptionPane.showInputDialog(null, "Koju vidljivost zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
                        }
                        ((Methods) classContent).setVidljivost(selection2.toString());
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues3 = {"static", "nista"};
                        String basicSelection3 = "nista";
                        Object selection3 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete static?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
                        while (selection3 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection3 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete static?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
                        }
                        if (!(selection3.toString().equals("nista")))
                            ((Methods) classContent).setStatic(true);
                        else ((Methods) classContent).setStatic(false);
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues4 = {"apstrakt", "nista"};
                        String basicSelection4 = "nista";
                        Object selection4 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete abstrakt?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues4, basicSelection4);
                        while (selection4 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection4 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete abstrakt?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues4, basicSelection4);
                        }
                        if (!(selection4.toString().equals("nista")))
                            ((Methods) classContent).setAbstract(true);
                        else ((Methods) classContent).setAbstract(false);
                        diagramView.getDiagram().selektovano();
                    } else if (classContent instanceof EnumElements) {
                        JFrame f = new JFrame();
                        String name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        while (name == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        }
                        //System.out.println(name);
                        ((EnumElements) classContent).setName(name);
                        diagramView.getDiagram().selektovano();
                    }
                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                } else if (element.getDiagramElements() instanceof Interfejs) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    Object[] selectionValues = new Object[100];
                    Object[] lista = new Object[100];
                    int i;
                    for (i = 0; i < ((Interfejs) element.getDiagramElements()).getAtributsList().size(); i++) {
                        String rec = ((Interfejs) element.getDiagramElements()).getAtributsList().get(i).toString();
                        lista[i] = ((Interfejs) element.getDiagramElements()).getAtributsList().get(i);
                        selectionValues[i] = rec;
                    }
                    selectionValues[i] = element.getDiagramElements().getName();
                    lista[i] = element.getDiagramElements();
                    Object basicSelection = selectionValues[0];

                    Object selection = JOptionPane.showInputDialog(null, "Sta zelite da promenite?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                    while (selection == null) {
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                        selection = JOptionPane.showInputDialog(null, "Sta zelite da promenite?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                    }
                    Object classContent = null;
                    for (int j = 0; j < lista.length; j++) {
                        if (lista[j].toString().equals(selection)) {
                            classContent = lista[j];
                            break;
                        }

                    }
                    if (classContent instanceof Interfejs) {
                        JFrame f = new JFrame();
                        String name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        while (name == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        }
                        ((Interfejs) classContent).setName(name);
                        diagramView.getDiagram().selektovano();
                    } else if (classContent instanceof Methods) {
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues6 = {"void", "int", "String", "boolean", "float", "double"};
                        String basicSelection6 = "void";
                        Object selection6 = JOptionPane.showInputDialog(null, "Koji povratni ti metode zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues6, basicSelection6);
                        while (selection6 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection6 = JOptionPane.showInputDialog(null, "Koji povratni ti metode zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues6, basicSelection6);
                        }
                        ((Methods) classContent).setTip(selection6.toString());
                        JFrame f = new JFrame();
                        String name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        while (name == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            name = JOptionPane.showInputDialog(f, "Unesite ime: ");
                        }
                        ((Methods) classContent).setName(name);
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        String kraj = null;
                        Object[] selectionValues9 = {"int", "String", "boolean", "float", "double", "Object", "nista"};
                        String basicSelection9 = "int";
                        Object selection9 = JOptionPane.showInputDialog(null, "Koji tip ulaznog argumenta zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues9, basicSelection9);
                        while (selection9 == null || selection9 != "nista") {
                            if (selection9 != null) {
                                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Nastavite da birate tip argumenta", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                                kraj += selection9.toString() + ",";
                                selection9 = JOptionPane.showInputDialog(null, "Koji tip ulaznog argumenta zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues9, basicSelection9);
                            } else {
                                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                                selection9 = JOptionPane.showInputDialog(null, "Koji tip ulaznog argumenta zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues9, basicSelection9);
                            }
                        }
                        ((Methods) classContent).getUlazniElementi().clear();
                        String rec[] = kraj.split(",");
                        for (int m = 0; m < rec.length - 1; m++) {
                            ((Methods) classContent).getUlazniElementi().add(rec[m]);
                        }
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues2 = {"public", "private", "protected", "default"};
                        String basicSelection2 = "public";
                        Object selection2 = JOptionPane.showInputDialog(null, "Koju vidljivost zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
                        while (selection2 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection2 = JOptionPane.showInputDialog(null, "Koju vidljivost zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
                        }
                        ((Methods) classContent).setVidljivost(selection2.toString());
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues3 = {"static", "nista"};
                        String basicSelection3 = "nista";
                        Object selection3 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete static?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
                        while (selection3 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection3 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete static?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
                        }
                        if (!(selection3.toString().equals("nista")))
                            ((Methods) classContent).setStatic(true);
                        else ((Methods) classContent).setStatic(false);
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        Object[] selectionValues4 = {"apstrakt", "nista"};
                        String basicSelection4 = "nista";
                        Object selection4 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete abstrakt?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues4, basicSelection4);
                        while (selection4 == null) {
                            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                            selection4 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete abstrakt?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues4, basicSelection4);
                        }
                        if (!(selection4.toString().equals("nista")))
                            ((Methods) classContent).setAbstract(true);
                        else ((Methods) classContent).setAbstract(false);
                        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                        diagramView.getDiagram().selektovano();

                    }

                } else if (element.getDiagramElements() instanceof Enumm) {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    Object[] selectionValues = new Object[100];
                    Object[] lista = new Object[100];
                    int i;
                    for (i = 0; i < ((Enumm) element.getDiagramElements()).getAtributsList().size(); i++) {
                        String rec = ((Enumm) element.getDiagramElements()).getAtributsList().get(i).toString();
                        lista[i] = ((Enumm) element.getDiagramElements()).getAtributsList().get(i);
                        selectionValues[i] = rec;
                    }
                    selectionValues[i] = element.getDiagramElements().getName();
                    lista[i] = element.getDiagramElements();
                    Object basicSelection = selectionValues[0];

                    Object selection = JOptionPane.showInputDialog(null, "Sta zelite da promenite?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                    while (selection == null) {
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                        selection = JOptionPane.showInputDialog(null, "Sta zelite da promenite?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                    }
                    Object classContent = null;
                    for (int j = 0; j < lista.length; j++) {
                        if (lista[j].toString().equals(selection)) {
                            classContent = lista[j];
                            break;
                        }

                    }
                         if (classContent instanceof Enumm) {
                             JFrame f = new JFrame();
                             String name = JOptionPane.showInputDialog(f, "Unesite ime enuma: ");
                             while (name == null) {
                                 ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                                 name = JOptionPane.showInputDialog(f, "Unesite ime enuma: ");
                             }
                             ((Enumm) classContent).setName(name);
                             diagramView.getDiagram().selektovano();
                         }
                        else if (classContent instanceof EnumElements) {
                            JFrame f2 = new JFrame();
                            String name2 = JOptionPane.showInputDialog(f2, "Unesite ime emun elementa: ");
                            while (name2 == null) {
                                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                                name2 = JOptionPane.showInputDialog(f2, "Unesite ime enum elementa: ");
                            }
                            ((EnumElements) classContent).setName(name2);
                            diagramView.getDiagram().selektovano();
                        }
                        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());


                }


            }
            else if(element.getDiagramElements() instanceof Connection){
                if(element.getDiagramElements() instanceof Agregacija){
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    Object[] selectionValues2 = {"0..1", "0..*"};
                    String basicSelection2 = "0..1";
                    Object selection2 = JOptionPane.showInputDialog(null, "Izaberite kardinalnost", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
                    while(selection2 == null){
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                        selection2 = JOptionPane.showInputDialog(null, "Izaberite kardinalnost", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
                    }
                    ((Agregacija)element.getDiagramElements()).setKardinalnost(selection2.toString());
                    JFrame f = new JFrame();
                    String name = JOptionPane.showInputDialog(f, "Unesite ime promenljive: ");
                    while (name == null) {
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                        name = JOptionPane.showInputDialog(f, "Unesite ime promenljive: ");
                    }
                    ((Agregacija)element.getDiagramElements()).setImePromenljive(name);
                }
                else if(element.getDiagramElements() instanceof Kompozicija){
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    Object[] selectionValues2 = {"0..1", "0..*"};
                    String basicSelection2 = "0..1";
                    Object selection2 = JOptionPane.showInputDialog(null, "Izaberite kardinalnost", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
                    while(selection2 == null){
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                        selection2 = JOptionPane.showInputDialog(null, "Izaberite kardinalnost", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
                    }
                    ((Kompozicija)element.getDiagramElements()).setKardinalnost(selection2.toString());
                    JFrame f = new JFrame();
                    String name = JOptionPane.showInputDialog(f, "Unesite ime promenljive: ");
                    while (name == null) {
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                        name = JOptionPane.showInputDialog(f, "Unesite ime promenljive: ");
                    }
                    ((Kompozicija)element.getDiagramElements()).setImePromeljive(name);
                }
                else if(element.getDiagramElements() instanceof Zavisnost){
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    Object[] selectionValues3 = {"Call", "Instantiate"};
                    String basicSelection3 = "Call";
                    Object selection3 = JOptionPane.showInputDialog(null, "Izaberite kardinalnost", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
                    while(selection3 == null){
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                        selection3 = JOptionPane.showInputDialog(null, "Izaberite kardinalnost", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
                    }
                    ((Zavisnost)element.getDiagramElements()).setTip(selection3.toString());
                }
                else {

                }
            }


        }
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

    }
}
