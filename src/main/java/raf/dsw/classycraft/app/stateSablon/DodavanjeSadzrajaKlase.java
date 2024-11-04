package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.EnumElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Methods;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.painters.*;
import raf.dsw.classycraft.app.undo.AddElementsCommnad;
import raf.dsw.classycraft.app.undo.AddInterClassObjekatCommand;

import javax.swing.*;
import java.awt.*;

public class DodavanjeSadzrajaKlase implements State{
    private String a;
    public DodavanjeSadzrajaKlase(String s) {
        this.a = s;
    }

    public void misKliknut(int x1, int y1, DiagramView diagramView) {
        if (this.a != null) {
            ClassContent classContent = null;
            Point point = new Point(x1, y1);

            if (diagramView.getScale() != 1) {
                point = diagramView.getOriginalCoordinates(new Point(x1, y1));

            } else point = new Point(x1, y1);
            int x = point.x;
            int y = point.y;
            boolean flag = false;
            ElementPainteri element = null;
            for (ElementPainteri elementPainteri : diagramView.getPainteri()) {
                if (elementPainteri.elementAt(point, diagramView, "selekcija", elementPainteri)) {
                    flag = true;
                    element = elementPainteri;
                }
                if (flag)
                    break;
            }
            String novaRec[] = a.split(" ");
            if (flag) {
                if (element.getDiagramElements() instanceof Klasa) {

                    AtributPainter atributPainter = null;


                    boolean flag2 = false;
                    boolean flag3 = false;
                    if (novaRec[0].toLowerCase().equals("atribut")) {
                        if (novaRec[4].toLowerCase().equals("static"))
                            flag2 = true;
                        if (novaRec[5].toLowerCase().equals("apstrakt"))
                            flag3 = true;

                        classContent = new Atributs(element.getDiagramElements(), Color.black, novaRec[2], novaRec[1], flag2, flag3);
                        if (!(((Klasa) element.getDiagramElements()).getAtributsList().contains(classContent))) {
                            int sirinaPre = ((Klasa) element.getDiagramElements()).getWidth();
                            ((Klasa) element.getDiagramElements()).povecajSumu();
                            ((Klasa) element.getDiagramElements()).setBroj(5);
                            ((Atributs) classContent).setVidljivost(novaRec[3]);
                            atributPainter = new AtributPainter(classContent.toString(), element.getDiagramElements(), (Atributs) classContent);
                            diagramView.getPainteri().add(atributPainter);
                            ((Klasa) element.getDiagramElements()).dodaj(classContent);
                            diagramView.getDiagram().notifySubscriber("", "crtanje");


                            AddElementsCommnad command = new AddElementsCommnad(classContent, atributPainter, (Interclass) element.getDiagramElements(), diagramView);
                            command.setSirinaPre(sirinaPre);
                            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                        }


                    } else if (novaRec[0].toLowerCase().equals("metoda")) {
                        MetodaPainter metodaPainter = null;
                        String novaRec2[] = a.split(" ");
                        for (int i = 0; i < novaRec2.length; i++) {
                            System.out.println(novaRec2[i] + " prvi");
                            //((Methods) classContent).getUlazniElementi().add(rec[i]);
                        }
                        if (novaRec2[5].toLowerCase().equals("static"))
                            flag2 = true;
                        if (novaRec2[6].toLowerCase().equals("apstrakt"))
                            flag3 = true;

                        classContent = new Methods(element.getDiagramElements(), Color.black, novaRec2[2], novaRec2[1], flag2, flag3);
                        ((Methods) classContent).setVidljivost(novaRec2[4]);
                        String rec[] = novaRec2[3].split(",");
                        for (int i = 0; i < rec.length - 1; i++) {
                            if (i == 0) {
                                String r[] = rec[i].split("\\(");
                                ((Methods) classContent).getUlazniElementi().add(r[1]);
                            } else
                                ((Methods) classContent).getUlazniElementi().add(rec[i]);
                        }
                        if (!(((Klasa) element.getDiagramElements()).getAtributsList().contains(classContent))) {
                            int sirinaPre = ((Klasa) element.getDiagramElements()).getWidth();
                            ((Klasa) element.getDiagramElements()).povecajSumu();
                            ((Klasa) element.getDiagramElements()).setBroj(5);
                            metodaPainter = new MetodaPainter(classContent.toString(), element.getDiagramElements(), (Methods) classContent);
                            diagramView.getPainteri().add(metodaPainter);
                            ((Klasa) element.getDiagramElements()).dodaj(classContent);
                            diagramView.getDiagram().notifySubscriber("", "crtanje");

                            AddElementsCommnad command = new AddElementsCommnad(classContent, metodaPainter, (Interclass) element.getDiagramElements(), diagramView);
                            command.setSirinaPre(sirinaPre);
                            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                        }
                    } else {
                        EnumElementsPainter enumPainter = null;
                        classContent = new EnumElements(element.getDiagramElements(), Color.black, novaRec[2].toUpperCase());
                        if (!(((Klasa) element.getDiagramElements()).getAtributsList().contains((EnumElements)classContent))) {
                            int sirinaPre = ((Klasa) element.getDiagramElements()).getWidth();
                            ((Klasa) element.getDiagramElements()).povecajSumu();
                            ((Klasa) element.getDiagramElements()).setBroj(5);
                            enumPainter = new EnumElementsPainter(classContent.toString(), element.getDiagramElements(), (EnumElements) classContent);
                            diagramView.getPainteri().add(enumPainter);
                            ((Klasa) element.getDiagramElements()).dodaj(classContent);
                            diagramView.getDiagram().notifySubscriber("", "crtanje");

                            AddElementsCommnad command = new AddElementsCommnad(classContent, enumPainter, (Interclass) element.getDiagramElements(), diagramView);
                            command.setSirinaPre(sirinaPre);
                            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                        }
                    }



                } else if (element.getDiagramElements() instanceof Interfejs) {
                    boolean flag2 = false;
                    boolean flag3 = false;
                    if (novaRec[0].toLowerCase().equals("metoda")) {
                        MetodaPainter metodaPainter = null;
                        String novaRec2[] = a.split(" ");
                        for (int i = 0; i < novaRec2.length; i++) {
                            System.out.println(novaRec2[i] + " prvi");
                            //((Methods) classContent).getUlazniElementi().add(rec[i]);
                        }
                        if (novaRec2[5].toLowerCase().equals("static"))
                            flag2 = true;
                        if (novaRec2[6].toLowerCase().equals("apstrakt"))
                            flag3 = true;

                        classContent = new Methods(element.getDiagramElements(), Color.black, novaRec2[2], novaRec2[1], flag2, flag3);
                        ((Methods) classContent).setVidljivost(novaRec2[4]);
                        String rec[] = novaRec2[3].split(",");
                        for (int i = 0; i < rec.length - 1; i++) {
                            if (i == 0) {
                                String r[] = rec[i].split("\\(");
                                ((Methods) classContent).getUlazniElementi().add(r[1]);
                            } else
                                ((Methods) classContent).getUlazniElementi().add(rec[i]);
                        }
                        if (!(((Interfejs) element.getDiagramElements()).getAtributsList().contains(classContent))) {
                            int sirinaPre = ((Interfejs) element.getDiagramElements()).getWidth();
                            ((Interfejs) element.getDiagramElements()).povecajSumu();
                            //System.out.println(((Interfejs) element.getDiagramElements()).getSuma());
                            ((Interfejs) element.getDiagramElements()).setBroj(5);
                            metodaPainter = new MetodaPainter(classContent.toString(), element.getDiagramElements(), (Methods) classContent);
                            diagramView.getPainteri().add(metodaPainter);
                            ((Interfejs) element.getDiagramElements()).dodaj(classContent);
                            diagramView.getDiagram().notifySubscriber("", "crtanje");

                            AddElementsCommnad command = new AddElementsCommnad(classContent, metodaPainter, (Interclass) element.getDiagramElements(), diagramView);
                            command.setSirinaPre(sirinaPre);
                            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                        }
                    }

                } else if (element.getDiagramElements() instanceof Enumm) {
                    if (novaRec[0].toLowerCase().equals("enum")) {
                        EnumElementsPainter enumPainter = null;
                        classContent = new EnumElements(element.getDiagramElements(), Color.black, novaRec[2].toUpperCase());
                        if (!(((Enumm) element.getDiagramElements()).getAtributsList().contains(classContent))) {
                            int sirinaPre = ((Enumm) element.getDiagramElements()).getWidth();
                            ((Enumm) element.getDiagramElements()).povecajSumu();
                            ((Enumm) element.getDiagramElements()).setBroj(5);
                            enumPainter = new EnumElementsPainter(classContent.toString(), element.getDiagramElements(), (EnumElements) classContent);
                            diagramView.getPainteri().add(enumPainter);
                            ((Enumm) element.getDiagramElements()).dodaj(classContent);
                            diagramView.getDiagram().notifySubscriber("", "crtanje");

                            AddElementsCommnad command = new AddElementsCommnad(classContent, enumPainter, (Interclass) element.getDiagramElements(), diagramView);
                            command.setSirinaPre(sirinaPre);
                            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                        }
                    }

                }
            }
        }
    }
    public void misPovucen(int x, int y, DiagramView diagramView){

    }
    public void misOtpusten(int x, int y, DiagramView diagramView){
        this.a = null;
    }
}
