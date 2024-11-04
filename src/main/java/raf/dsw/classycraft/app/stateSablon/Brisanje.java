package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.*;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.EnumElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Methods;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.message.MessageType;
import raf.dsw.classycraft.app.painters.*;
import raf.dsw.classycraft.app.undo.AddBrisanjeCommand;
import raf.dsw.classycraft.app.undo.AddInterClassObjekatCommand;
import raf.dsw.classycraft.app.undo.BrisanjeElementCommand;
import raf.dsw.classycraft.app.undo.MultiselectionBrisanjeCommand;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class Brisanje implements State{
    String a;
    public Brisanje(String s) {
        this.a = s;
    }

    public void misKliknut(int x1, int y1, DiagramView diagramView){
        Point point = new Point(x1,y1);
        if(diagramView.getScale()!=1){
            point = diagramView.getOriginalCoordinates(new Point(x1, y1));
        }
        else{
            point = new Point(x1, y1);
        }
        int x = point.x;
        int y = point.y;
        boolean flag1 = false;
        ElementPainteri elements = null;
        for (ElementPainteri element : diagramView.getPainteri()) {
            point = new Point(x1, y1);
            if (element.elementAt(point, diagramView, "selekcija", element)) {
                elements = element;
                flag1 = true;
                if (flag1) {
                    break;
                }
            }
        }
        if(diagramView.getSelectionModel().isEmpty() && flag1 == true) {
            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValue = {"Element", "Diagram element"};
            String basicSelectio = "Element";
            Object selectio = JOptionPane.showInputDialog(null, "Sta zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValue, basicSelectio);
            while (selectio == null) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                selectio = JOptionPane.showInputDialog(null, "Sta zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValue, basicSelectio);
            }
            String a = selectio.toString();

            if (a.toLowerCase().equals("diagram element")) {
                List<ElementPainteri> novaLista = new ArrayList<>();
                List<ElementPainteri> listaVeza = new ArrayList<>();
                if(flag1){

                if (elements.getDiagramElements() instanceof Klasa) {
                    AddBrisanjeCommand command = new AddBrisanjeCommand((ClassyTreeItem) elements.getClassyTreeItem().getParent(), elements.getClassyTreeItem(), (Interclass) elements.getDiagramElements(), elements, diagramView);
                    for (int i = 0; i < ((Klasa) elements.getDiagramElements()).getAtributsList().size(); i++) {
                        ClassContent k = ((Klasa) elements.getDiagramElements()).getAtributsList().get(i);
                        Iterator e = diagramView.getPainteri().iterator();
                        while (e.hasNext()) {
                            ElementPainteri next = (ElementPainteri) e.next();
                            String novarec[] = null;
                            if (next instanceof AtributPainter) {
                                        //novarec = next.getDiagramElements().getName().split(" ");
                                        //System.out.println(novarec[2]);
                                if (((AtributPainter) next).getAtribut().equals(k)) {
                                    novaLista.add(next);
                                            e.remove();
                                }
                            } else if (next instanceof MetodaPainter) {
                                if (((MetodaPainter) next).getMethods().equals(k)) {
                                    novaLista.add(next);
                                        e.remove();
                                }
                            } else if (next instanceof EnumElementsPainter) {
                                if (((EnumElementsPainter) next).getEnumElements().equals(k)) {
                                    novaLista.add(next);
                                        e.remove();
                                }
                            }
                        }
                    }
                    command.setElementPainteriList(novaLista);

                    for (int i = 0; i < ((Klasa) elements.getDiagramElements()).getKonekcije().size(); i++) {

                        Connection k = ((Klasa) elements.getDiagramElements()).getKonekcije().get(i);
                        Iterator e = diagramView.getPainteri().iterator();
                        while (e.hasNext()) {
                            ElementPainteri next = (ElementPainteri) e.next();
                            if (next instanceof AgregacijaPainter) {

                                next.getClassyTreeItem().removeFromParent();
                                if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                    listaVeza.add(next);
                                    e.remove();
                                    next.getClassyTreeItem().removeFromParent();

                                }
                            }
                            else if (next instanceof ZavisnostPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                    listaVeza.add(next);
                                            e.remove();
                                    next.getClassyTreeItem().removeFromParent();

                                }
                            }
                            else if (next instanceof GeneralizacijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                    listaVeza.add(next);
                                    e.remove();
                                    next.getClassyTreeItem().removeFromParent();

                                }
                            }
                            else if (next instanceof KompozicijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                    listaVeza.add(next);
                                    e.remove();
                                    next.getClassyTreeItem().removeFromParent();

                                }
                            }
                            command.setListaVeza(listaVeza);
                           SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                        }
                    }       ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                            ((Klasa) elements.getDiagramElements()).getKonekcije().clear();
                            ((Klasa) elements.getDiagramElements()).getAtributsList().clear();
                            diagramView.getPainteri().remove(elements);
                            diagramView.getDiagram().deleteChild(elements.getDiagramElements());
                            //flag1 = true;
                        } else if (elements.getDiagramElements() instanceof Interfejs) {
                    AddBrisanjeCommand command = new AddBrisanjeCommand((ClassyTreeItem) elements.getClassyTreeItem().getParent(), elements.getClassyTreeItem(), (Interclass) elements.getDiagramElements(), elements, diagramView);

                            for (int i = 0; i < ((Interfejs) elements.getDiagramElements()).getAtributsList().size(); i++) {
                                ClassContent k = ((Interfejs) elements.getDiagramElements()).getAtributsList().get(i);
                                Iterator e = diagramView.getPainteri().iterator();
                                while (e.hasNext()) {
                                    ElementPainteri next = (ElementPainteri) e.next();

                                    String novarec[] = null;
                                    if (next instanceof MetodaPainter) {
                                        if (((MetodaPainter) next).getMethods().equals(k)) {
                                            novaLista.add(next);
                                            e.remove();
                                        }
                                    }
                                }
                            }
                            command.setElementPainteriList(novaLista);

                            for (int i = 0; i < ((Interfejs) elements.getDiagramElements()).getKonekcije().size(); i++) {
                                Connection k = ((Interfejs) elements.getDiagramElements()).getKonekcije().get(i);
                                Iterator e = diagramView.getPainteri().iterator();
                                while (e.hasNext()) {
                                    ElementPainteri next = (ElementPainteri) e.next();
                                    if (next instanceof AgregacijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                            listaVeza.add(next);
                                            e.remove();
                                            next.getClassyTreeItem().removeFromParent();

                                        }
                                    }
                                    else if (next instanceof ZavisnostPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                            listaVeza.add(next);
                                            e.remove();
                                            next.getClassyTreeItem().removeFromParent();

                                        }
                                    }
                                    else if (next instanceof GeneralizacijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                            listaVeza.add(next);
                                            e.remove();
                                            next.getClassyTreeItem().removeFromParent();

                                        }
                                    }
                                    else if (next instanceof KompozicijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                            listaVeza.add(next);
                                            e.remove();
                                            next.getClassyTreeItem().removeFromParent();

                                        }
                                    }
                                    command.setListaVeza(listaVeza);
                                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                                }
                            }
                    ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                            ((Interfejs) elements.getDiagramElements()).getKonekcije().clear();
                            ((Interfejs) elements.getDiagramElements()).getAtributsList().clear();
                            diagramView.getPainteri().remove(elements);
                            diagramView.getDiagram().deleteChild(elements.getDiagramElements());
                        } else if (elements.getDiagramElements() instanceof Enumm) {
                    AddBrisanjeCommand command = new AddBrisanjeCommand((ClassyTreeItem) elements.getClassyTreeItem().getParent(), elements.getClassyTreeItem(), (Interclass) elements.getDiagramElements(), elements, diagramView);

                            for (int i = 0; i < ((Enumm) elements.getDiagramElements()).getAtributsList().size(); i++) {
                                ClassContent k = ((Enumm) elements.getDiagramElements()).getAtributsList().get(i);
                                Iterator e = diagramView.getPainteri().iterator();
                                while (e.hasNext()) {
                                    ElementPainteri next = (ElementPainteri) e.next();

                                    String novarec[] = null;
                                    if (next instanceof EnumElementsPainter) {
                                        if (((EnumElementsPainter) next).getEnumElements().equals(k)) {
                                            novaLista.add(next);
                                            e.remove();
                                        }
                                    }
                                }
                            }
                            command.setElementPainteriList(novaLista);

                            for (int i = 0; i < ((Enumm) elements.getDiagramElements()).getKonekcije().size(); i++) {
                                Connection k = ((Enumm) elements.getDiagramElements()).getKonekcije().get(i);
                                Iterator e = diagramView.getPainteri().iterator();
                                while (e.hasNext()) {
                                    ElementPainteri next = (ElementPainteri) e.next();
                                    System.out.println(next.toString() + " next");
                                    if (next instanceof AgregacijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                            listaVeza.add(next);
                                            e.remove();
                                            next.getClassyTreeItem().removeFromParent();

                                        }
                                    }
                                    else if (next instanceof ZavisnostPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                            listaVeza.add(next);
                                            e.remove();
                                            next.getClassyTreeItem().removeFromParent();

                                        }
                                    }
                                    else if (next instanceof GeneralizacijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                            listaVeza.add(next);
                                            e.remove();
                                            next.getClassyTreeItem().removeFromParent();

                                        }
                                    }
                                    else if (next instanceof KompozicijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                            listaVeza.add(next);
                                            e.remove();
                                            next.getClassyTreeItem().removeFromParent();

                                        }
                                    }
                                    command.setListaVeza(listaVeza);
                                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                                }
                            }
                    ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                            ((Enumm) elements.getDiagramElements()).getKonekcije().clear();
                            ((Enumm) elements.getDiagramElements()).getAtributsList().clear();
                            diagramView.getPainteri().remove(elements);
                            diagramView.getDiagram().deleteChild(elements.getDiagramElements());
                            //flag = true;


                } else if (elements.getDiagramElements() instanceof Connection) {
                    AddBrisanjeCommand command = new AddBrisanjeCommand((ClassyTreeItem) elements.getClassyTreeItem().getParent(), elements.getClassyTreeItem(), null, elements, diagramView);
                    (((Connection) elements.getDiagramElements()).getFrom()).getKonekcije().remove(elements.getDiagramElements());
                    (((Connection) elements.getDiagramElements()).getTo()).getKonekcije().remove(elements.getDiagramElements());
                    elements.getClassyTreeItem().removeFromParent();
                    diagramView.getPainteri().remove(elements);
                    ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                    diagramView.getDiagram().notifySubscriber("", "crtanje");

                        }

                    elements.getClassyTreeItem().removeFromParent();
                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                    }


                }
             else {
                boolean flag12 = false;
                ElementPainteri e = null;
                for (ElementPainteri element : diagramView.getPainteri()) {

                    point = new Point(x, y);
                    if (element.elementAt(point, diagramView, "selekcija", element)) {
                        e = element;
                        flag12 = true;
                        if (flag12) {
                            break;
                        }
                    }
                }
                if(flag12){
                    if (e.elementAt(point, diagramView, "selekcija", e)) {
                        if(e.getDiagramElements() instanceof Klasa){
                            if (!((Klasa)e.getDiagramElements()).getAtributsList().isEmpty()) {
                                JDialog.setDefaultLookAndFeelDecorated(true);
                                Object[] selectionValues = new Object[100];
                                for (int i = 0; i < ((Klasa) e.getDiagramElements()).getAtributsList().size(); i++) {
                                    String rec = ((Klasa) e.getDiagramElements()).getAtributsList().get(i).toString();
                                    selectionValues[i] = rec;
                                }
                                System.out.println("usooo2");
                                Object basicSelection = selectionValues[0];

                                Object selection = JOptionPane.showInputDialog(null, "Koji element zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                                while (selection == null) {
                                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                                    selection = JOptionPane.showInputDialog(null, "Koji element zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                                }
                                int cnt = 0;
                                Iterator iterator = diagramView.getPainteri().iterator();
                                while (iterator.hasNext()) {
                                    ElementPainteri elementPainteri = (ElementPainteri) iterator.next();
                                    if (elementPainteri instanceof AtributPainter) {
                                        cnt++;
                                        BrisanjeElementCommand command = new BrisanjeElementCommand(elementPainteri, diagramView, (Interclass) e.getDiagramElements());
                                        int sirinaPre = ((Klasa) e.getDiagramElements()).getWidth();
                                        command.setSirinaPre(sirinaPre);
                                        command.setAtributPainter((AtributPainter) elementPainteri);
                                        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                                        if (((AtributPainter) elementPainteri).getAtribut().toString().equals(selection)) {
                                            if (((Interclass) e.getDiagramElements()).getDuzinaAtributa().size() == 1)
                                                ((Interclass) e.getDiagramElements()).getDuzinaAtributa().clear();
                                            else
                                                ((Interclass) e.getDiagramElements()).getDuzinaAtributa().remove(cnt - 1);
                                            iterator.remove();
                                            break;
                                        }
                                    } else if (elementPainteri instanceof MetodaPainter) {
                                        cnt++;
                                        BrisanjeElementCommand command = new BrisanjeElementCommand(elementPainteri, diagramView, (Interclass) e.getDiagramElements());
                                        int sirinaPre = ((Klasa) e.getDiagramElements()).getWidth();
                                        command.setSirinaPre(sirinaPre);
                                        command.setMetodaPainter((MetodaPainter) elementPainteri);
                                        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                                        if (((MetodaPainter) elementPainteri).getMethods().toString().equals(selection)) {
                                            if (((Interclass) e.getDiagramElements()).getDuzinaAtributa().size() == 1)
                                                ((Interclass) e.getDiagramElements()).getDuzinaAtributa().clear();
                                            else
                                                ((Interclass) e.getDiagramElements()).getDuzinaAtributa().remove(cnt - 1);
                                            iterator.remove();
                                            break;
                                        }
                                    } else if (elementPainteri instanceof EnumElementsPainter) {
                                        cnt++;
                                        BrisanjeElementCommand command = new BrisanjeElementCommand(elementPainteri, diagramView, (Interclass) e.getDiagramElements());
                                        int sirinaPre = ((Klasa) e.getDiagramElements()).getWidth();
                                        command.setSirinaPre(sirinaPre);
                                        command.setEnumElements((EnumElementsPainter) elementPainteri);
                                        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                                        if (((EnumElementsPainter) elementPainteri).getEnumElements().toString().equals(selection)) {
                                            if (((Interclass) e.getDiagramElements()).getDuzinaAtributa().size() == 1)
                                                ((Interclass) e.getDiagramElements()).getDuzinaAtributa().clear();
                                            else
                                                ((Interclass) e.getDiagramElements()).getDuzinaAtributa().remove(cnt - 1);
                                            iterator.remove();
                                            break;
                                        }
                                    }
                                }
                                int max = -1;
                                for (int i = 0; i < ((Interclass) e.getDiagramElements()).getDuzinaAtributa().size(); i++) {
                                    if (max < ((Interclass) e.getDiagramElements()).getDuzinaAtributa().get(i))
                                        max = ((Interclass) e.getDiagramElements()).getDuzinaAtributa().get(i);
                                }
                                ((Interclass) e.getDiagramElements()).setNajveciWidth(max);
                                Iterator iterator2 = ((Klasa) e.getDiagramElements()).getAtributsList().iterator();
                                while (iterator2.hasNext()) {
                                    cnt++;
                                    ClassContent ele = (ClassContent) iterator2.next();
                                    String rec2 = null;
                                    if (ele instanceof Atributs) {
                                        Atributs at = (Atributs) ele;
                                        rec2 = ele.toString();
                                        String s = selection.toString();
                                        if (rec2.equals(s)) {
                                            ((Klasa) e.getDiagramElements()).umanjiSumu();
                                            ((Klasa) e.getDiagramElements()).setBroj(5);
                                            ((Klasa) e.getDiagramElements()).getAtributsList().remove(ele);
                                            try {
                                                iterator2.remove();
                                            } catch (ConcurrentModificationException e1) {
                                                System.out.println("");

                                            }

                                            diagramView.getDiagram().notifySubscriber("", "crtanje");

                                            break;
                                        }
                                    } else if (ele instanceof Methods) {
                                        Methods at = (Methods) ele;
                                        rec2 = ele.toString();
                                        String s = selection.toString();
                                        if (rec2.equals(s)) {
                                            System.out.println("jdksada");
                                            ((Klasa) e.getDiagramElements()).umanjiSumu();
                                            ((Klasa) e.getDiagramElements()).setBroj(5);
                                            ((Klasa) e.getDiagramElements()).getAtributsList().remove(ele);
                                            try {
                                                iterator2.remove();
                                            } catch (ConcurrentModificationException e1) {
                                                System.out.println("");

                                            }

                                            diagramView.getDiagram().notifySubscriber("", "crtanje");

                                            break;
                                        }
                                    } else if (ele instanceof EnumElements) {
                                        EnumElements at = (EnumElements) ele;
                                        rec2 = ele.toString();
                                        String s = selection.toString();
                                        if (rec2.equals(s)) {
                                            ((Klasa) e.getDiagramElements()).umanjiSumu();
                                            ((Klasa) e.getDiagramElements()).setBroj(5);
                                            ((Klasa) e.getDiagramElements()).getAtributsList().remove(ele);
                                            try {
                                                iterator2.remove();
                                            } catch (ConcurrentModificationException e1) {
                                                System.out.println("");

                                            }

                                            diagramView.getDiagram().notifySubscriber("", "crtanje");

                                            break;
                                        }
                                    }
                                }
                         /*if(cnt == ((Klasa)element.getDiagramElements()).getAtributsList().size() + 1){
                             element.umanjiSumu();
                         }*/


                                //flag = true;
                            }
                        }
                        else if(e.getDiagramElements() instanceof Interfejs){
                            if (!((Interfejs)e.getDiagramElements()).getAtributsList().isEmpty()) {
                                JDialog.setDefaultLookAndFeelDecorated(true);
                                Object[] selectionValues = new Object[100];
                                for (int i = 0; i < ((Interfejs) e.getDiagramElements()).getAtributsList().size(); i++) {
                                    String rec = ((Interfejs) e.getDiagramElements()).getAtributsList().get(i).toString();
                                    selectionValues[i] = rec;
                                }
                                Object basicSelection = selectionValues[0];

                                Object selection = JOptionPane.showInputDialog(null, "Koji element zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                                System.out.println(basicSelection);
                                while (selection == null) {
                                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                                    selection = JOptionPane.showInputDialog(null, "Koji element zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                                }
                                int cnt = 0;
                                Iterator iterator = diagramView.getPainteri().iterator();
                                while (iterator.hasNext()) {
                                    ElementPainteri elementPainteri = (ElementPainteri) iterator.next();
                                    if (elementPainteri instanceof MetodaPainter) {
                                        cnt++;
                                        BrisanjeElementCommand command = new BrisanjeElementCommand(elementPainteri, diagramView, (Interclass) e.getDiagramElements());
                                        int sirinaPre = ((Klasa) e.getDiagramElements()).getWidth();
                                        command.setSirinaPre(sirinaPre);
                                        command.setMetodaPainter((MetodaPainter) elementPainteri);
                                        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                                        if (((MetodaPainter) elementPainteri).getMethods().toString().equals(selection)) {
                                            if (((Interclass) e.getDiagramElements()).getDuzinaAtributa().size() == 1)
                                                ((Interclass) e.getDiagramElements()).getDuzinaAtributa().clear();
                                            else
                                                ((Interclass) e.getDiagramElements()).getDuzinaAtributa().remove(cnt - 1);
                                            iterator.remove();
                                            break;
                                        }
                                    }
                                }
                                int max = -1;
                                for (int i = 0; i < ((Interclass) e.getDiagramElements()).getDuzinaAtributa().size(); i++) {
                                    if (max < ((Interclass) e.getDiagramElements()).getDuzinaAtributa().get(i))
                                        max = ((Interclass) e.getDiagramElements()).getDuzinaAtributa().get(i);
                                }
                                ((Interclass) e.getDiagramElements()).setNajveciWidth(max);
                                Iterator iterator2 = ((Interfejs) e.getDiagramElements()).getAtributsList().iterator();
                                while (iterator2.hasNext()) {
                                    cnt++;
                                    ClassContent ele = (ClassContent) iterator2.next();
                                    String rec2 = null;
                                    if (ele instanceof Methods) {
                                        Methods at = (Methods) ele;
                                        rec2 = ele.toString();
                                        String s = selection.toString();
                                        if (rec2.equals(s)) {

                                            ((Interfejs) e.getDiagramElements()).umanjiSumu();
                                            ((Interfejs) e.getDiagramElements()).setBroj(5);
                                            ((Interfejs) e.getDiagramElements()).getAtributsList().remove(ele);
                                            try {
                                                iterator2.remove();
                                            } catch (ConcurrentModificationException e1) {
                                                System.out.println("");

                                            }

                                            diagramView.getDiagram().notifySubscriber("", "crtanje");

                                            break;
                                        }
                                    }


                                }
                                //flag = true;
                            }
                        }
                        else if(e.getDiagramElements() instanceof Enumm){
                            if (!((Enumm)e.getDiagramElements()).getAtributsList().isEmpty()) {
                                JDialog.setDefaultLookAndFeelDecorated(true);
                                Object[] selectionValues = new Object[100];
                                for (int i = 0; i < ((Enumm) e.getDiagramElements()).getAtributsList().size(); i++) {
                                    String rec = ((Enumm) e.getDiagramElements()).getAtributsList().get(i).toString();
                                    selectionValues[i] = rec;
                                }
                                Object basicSelection = selectionValues[0];

                                Object selection = JOptionPane.showInputDialog(null, "Koji element zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                                while (selection == null) {
                                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                                    selection = JOptionPane.showInputDialog(null, "Koji element zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                                }
                                int cnt = 0;
                                Iterator iterator = diagramView.getPainteri().iterator();
                                while (iterator.hasNext()) {
                                    ElementPainteri elementPainteri = (ElementPainteri) iterator.next();
                                    if (elementPainteri instanceof EnumElementsPainter) {
                                        cnt++;
                                        BrisanjeElementCommand command = new BrisanjeElementCommand(elementPainteri, diagramView, (Interclass) e.getDiagramElements());
                                        int sirinaPre = ((Klasa) e.getDiagramElements()).getWidth();
                                        command.setSirinaPre(sirinaPre);
                                        command.setEnumElements((EnumElementsPainter) elementPainteri);
                                        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                                        if (((EnumElementsPainter) elementPainteri).getEnumElements().toString().equals(selection)) {
                                            if (((Interclass) e.getDiagramElements()).getDuzinaAtributa().size() == 1)
                                                ((Interclass) e.getDiagramElements()).getDuzinaAtributa().clear();
                                            else
                                                ((Interclass) e.getDiagramElements()).getDuzinaAtributa().remove(cnt - 1);
                                            iterator.remove();
                                            break;
                                        }
                                    }
                                }
                                int max = -1;
                                for (int i = 0; i < ((Interclass) e.getDiagramElements()).getDuzinaAtributa().size(); i++) {
                                    if (max < ((Interclass) e.getDiagramElements()).getDuzinaAtributa().get(i))
                                        max = ((Interclass) e.getDiagramElements()).getDuzinaAtributa().get(i);
                                }
                                ((Interclass) e.getDiagramElements()).setNajveciWidth(max);
                                Iterator iterator2 = ((Enumm) e.getDiagramElements()).getAtributsList().iterator();
                                while (iterator2.hasNext()) {
                                    cnt++;
                                    ClassContent ele = (ClassContent) iterator2.next();
                                    String rec2 = null;
                                    if (ele instanceof EnumElements) {
                                        EnumElements at = (EnumElements) ele;
                                        rec2 = ele.toString();
                                        String s = selection.toString();
                                        if (rec2.equals(s)) {
                                            ((Enumm) e.getDiagramElements()).umanjiSumu();
                                            ((Enumm) e.getDiagramElements()).setBroj(5);
                                            ((Enumm) e.getDiagramElements()).getAtributsList().remove(ele);
                                            try {
                                                iterator2.remove();
                                            } catch (ConcurrentModificationException e1) {
                                                System.out.println("");

                                            }

                                            diagramView.getDiagram().notifySubscriber("", "crtanje");

                                            break;
                                        }
                                    }


                                }
                                //flag = true;
                            }
                        }

                    }
                }
            }

        }else{
            List<ElementPainteri> elementPainteriList = new ArrayList<>();
            elementPainteriList.addAll(diagramView.getSelectionModel());

            MultiselectionBrisanjeCommand command = new MultiselectionBrisanjeCommand(diagramView);
            command.setElementPainteriList(elementPainteriList);

            for(ElementPainteri element : diagramView.getSelectionModel()){
                if(element.getDiagramElements() instanceof Klasa){
                    for(int i = 0; i < ((Klasa) element.getDiagramElements()).getAtributsList().size(); i++){
                        ClassContent k = ((Klasa)element.getDiagramElements()).getAtributsList().get(i);
                        Iterator e = diagramView.getPainteri().iterator();
                        while(e.hasNext()){
                            ElementPainteri next = (ElementPainteri) e.next();
                            if(next instanceof AtributPainter){

                                if(((AtributPainter) next).getAtribut().equals(k)){
                                    command.getSadrzaj().add(next);
                                    e.remove();
                                }
                            } else if (next instanceof MetodaPainter) {
                                if(((MetodaPainter) next).getMethods().equals(k)){
                                    command.getSadrzaj().add(next);
                                    e.remove();
                                }
                            }
                            else if(next instanceof EnumElementsPainter){
                                if(((EnumElementsPainter) next).getEnumElements().equals(k)){
                                    command.getSadrzaj().add(next);
                                    e.remove();
                                }
                            }
                        }
                    }
                    for (int i = 0; i < ((Klasa) element.getDiagramElements()).getKonekcije().size(); i++) {

                        Connection k = ((Klasa) element.getDiagramElements()).getKonekcije().get(i);

                        Iterator e = diagramView.getPainteri().iterator();
                        while (e.hasNext()) {
                            ElementPainteri next = (ElementPainteri) e.next();
                            if (next instanceof AgregacijaPainter) {
                                if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }

                            }
                            else if (next instanceof ZavisnostPainter) {

                                if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof GeneralizacijaPainter) {

                                if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }

                            }
                            else if (next instanceof KompozicijaPainter) {

                                if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            try{
                                next.getClassyTreeItem().removeFromParent();
                            }
                            catch (NullPointerException ej){
                                System.out.print("");
                            }
                        }
                    }
                    ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                    ((Klasa) element.getDiagramElements()).getKonekcije().clear();
                    ((Klasa) element.getDiagramElements()).getAtributsList().clear();
                    diagramView.getPainteri().remove(element);
                    try{
                        element.getClassyTreeItem().removeFromParent();
                    }
                    catch (NullPointerException e){
                        System.out.print("");
                    }

                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                    diagramView.getDiagram().deleteChild(element.getDiagramElements());

                }
                else if(element.getDiagramElements() instanceof Interfejs){
                    for(int i = 0; i < ((Interfejs) element.getDiagramElements()).getAtributsList().size(); i++){
                        ClassContent k = ((Interfejs)element.getDiagramElements()).getAtributsList().get(i);
                        Iterator e = diagramView.getPainteri().iterator();
                        while(e.hasNext()){
                            ElementPainteri next = (ElementPainteri) e.next();
                            if (next instanceof MetodaPainter) {
                                if(((MetodaPainter) next).getMethods().equals(k)){
                                    e.remove();
                                }
                            }
                        }
                    }
                    for (int i = 0; i < ((Interfejs) element.getDiagramElements()).getKonekcije().size(); i++) {
                        System.out.println("usao");
                        Connection k = ((Interfejs) element.getDiagramElements()).getKonekcije().get(i);
                        System.out.println(k.toString());
                        Iterator e = diagramView.getPainteri().iterator();
                        while (e.hasNext()) {
                            ElementPainteri next = (ElementPainteri) e.next();
                            if (next instanceof AgregacijaPainter) {
                                if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();

                                }
                            }
                            else if (next instanceof ZavisnostPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();

                                }
                            }
                            else if (next instanceof GeneralizacijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();

                                }
                            }
                            else if (next instanceof KompozicijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();

                                }
                            }
                            try{
                                next.getClassyTreeItem().removeFromParent();
                            }
                            catch (NullPointerException el){
                                System.out.print("");
                            }
                        }
                    }
                    ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                    ((Interfejs) element.getDiagramElements()).getKonekcije().clear();
                    ((Interfejs) element.getDiagramElements()).getAtributsList().clear();
                    diagramView.getPainteri().remove(element);
                    try{
                        element.getClassyTreeItem().removeFromParent();
                    }
                    catch (NullPointerException e){
                        System.out.print("");
                    }

                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                    diagramView.getDiagram().deleteChild(element.getDiagramElements());

                }
                else if(element.getDiagramElements() instanceof Enumm){
                    for(int i = 0; i < ((Enumm) element.getDiagramElements()).getAtributsList().size(); i++){
                        ClassContent k = ((Enumm)element.getDiagramElements()).getAtributsList().get(i);
                        Iterator e = diagramView.getPainteri().iterator();
                        while(e.hasNext()){
                            ElementPainteri next = (ElementPainteri) e.next();
                            if (next instanceof EnumElementsPainter) {
                                if(((EnumElementsPainter) next).getEnumElements().equals(k)){
                                    e.remove();
                                }
                            }
                        }
                    }
                    for (int i = 0; i < ((Enumm) element.getDiagramElements()).getKonekcije().size(); i++) {
                        Connection k = ((Enumm) element.getDiagramElements()).getKonekcije().get(i);
                        System.out.println(k.toString());
                        Iterator e = diagramView.getPainteri().iterator();
                        while (e.hasNext()) {
                            ElementPainteri next = (ElementPainteri) e.next();
                            //System.out.println(next.toString() + " next");
                            if (next instanceof AgregacijaPainter) {
                                if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof ZavisnostPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof GeneralizacijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof KompozicijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                        }
                    }
                    ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                    ((Enumm) element.getDiagramElements()).getKonekcije().clear();
                    ((Enumm) element.getDiagramElements()).getAtributsList().clear();
                    diagramView.getPainteri().remove(element);
                    try{
                        element.getClassyTreeItem().removeFromParent();
                    }
                    catch (NullPointerException e){
                        System.out.print("");
                    }

                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                    diagramView.getDiagram().deleteChild(element.getDiagramElements());

                }

                else if(element.getDiagramElements() instanceof Connection){
                    try{
                        element.getClassyTreeItem().removeFromParent();
                        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                    }
                    catch (NullPointerException e){
                        System.out.print("");
                    }
                        diagramView.getPainteri().remove(element);
                        diagramView.getDiagram().notifySubscriber("", "crtanje");

                }


            }
            diagramView.getSelectionModel().clear();
            diagramView.getDiagram().notifySubscriber("", "crtanje");
        }



    }
    public void misPovucen(int x, int y, DiagramView diagramView){

    }
    public void misOtpusten(int x, int y, DiagramView diagramView){

    }
}
