package raf.dsw.classycraft.app.undo;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.*;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.painters.*;

import javax.swing.*;
import java.util.Iterator;

public class AddInterClassObjekatCommand extends AbstractCommand {
    private ClassyTreeItem parent;
    private ClassyTreeItem child;
    private Interclass interclass;
    private ElementPainteri elementPainteri;
    private DiagramView diagramView;

    public AddInterClassObjekatCommand(ClassyTreeItem parent, ClassyTreeItem child, Interclass interclass, ElementPainteri elementPainteri, DiagramView diagramView) {
        this.parent = parent;
        this.child = child;
        this.interclass = interclass;
        this.elementPainteri = elementPainteri;
        this.diagramView = diagramView;
    }

    @Override
    public void doCommand() {
        if(child == null || parent == null) return;
        parent.add(child);
        ((ClassyNodeComposite)parent.getClassyNode()).addChild(child.getClassyNode());
        if(!(this.diagramView.getPainteri().contains(elementPainteri))){
            this.diagramView.getPainteri().add(elementPainteri);

        }

        this.diagramView.getDiagram().addChild(interclass);
    }

    @Override
    public void undoCommand() {
        if(child == null || parent == null) return;
        child.removeFromParent();
        ((ClassyNodeComposite)(parent.getClassyNode())).deleteChild(child.getClassyNode());
        if (interclass instanceof Klasa) {
            for (int i = 0; i < ((Klasa) interclass).getAtributsList().size(); i++) {
                ClassContent k = ((Klasa) interclass).getAtributsList().get(i);
                Iterator e = diagramView.getPainteri().iterator();
                while (e.hasNext()) {
                    ElementPainteri next = (ElementPainteri) e.next();
                    String novarec[] = null;
                    if (next instanceof AtributPainter) {
                        if (((AtributPainter) next).getAtribut().equals(k)) {
                            e.remove();
                        }
                    } else if (next instanceof MetodaPainter) {
                        if (((MetodaPainter) next).getMethods().equals(k)) {
                            e.remove();
                        }
                    } else if (next instanceof EnumElementsPainter) {
                        if (((EnumElementsPainter) next).getEnumElements().equals(k)) {
                            e.remove();
                        }
                    }
                }
            }
            for (int i = 0; i < ((Klasa) interclass).getKonekcije().size(); i++) {

                Connection k = ((Klasa) interclass).getKonekcije().get(i);
                Iterator e = diagramView.getPainteri().iterator();
                while (e.hasNext()) {
                    ElementPainteri next = (ElementPainteri) e.next();
                    if (next instanceof AgregacijaPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    } else if (next instanceof ZavisnostPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    } else if (next instanceof GeneralizacijaPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    } else if (next instanceof KompozicijaPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    }
                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                }
            }
            ((Klasa) interclass).getKonekcije().clear();
            ((Klasa) interclass).getAtributsList().clear();
            diagramView.getPainteri().remove(elementPainteri);
            diagramView.getDiagram().deleteChild(interclass);
            //flag1 = true;
        } else if (interclass instanceof Interfejs) {
            for (int i = 0; i < ((Interfejs) interclass).getAtributsList().size(); i++) {
                ClassContent k = ((Interfejs) interclass).getAtributsList().get(i);
                Iterator e = diagramView.getPainteri().iterator();
                while (e.hasNext()) {
                    ElementPainteri next = (ElementPainteri) e.next();

                    String novarec[] = null;
                    if (next instanceof MetodaPainter) {
                        if (((MetodaPainter) next).getMethods().equals(k)) {
                            e.remove();
                        }
                    }
                }
            }
            for (int i = 0; i < ((Interfejs) interclass).getKonekcije().size(); i++) {
                Connection k = ((Interfejs) interclass).getKonekcije().get(i);
                Iterator e = diagramView.getPainteri().iterator();
                while (e.hasNext()) {
                    ElementPainteri next = (ElementPainteri) e.next();
                    if (next instanceof AgregacijaPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    } else if (next instanceof ZavisnostPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    } else if (next instanceof GeneralizacijaPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    } else if (next instanceof KompozicijaPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    }
                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                }
            }
            ((Interfejs) interclass).getKonekcije().clear();
            ((Interfejs) interclass).getAtributsList().clear();
            diagramView.getPainteri().remove(elementPainteri);
            diagramView.getDiagram().deleteChild(interclass);
            //flag = true;
        } else if (interclass instanceof Enumm) {
            for (int i = 0; i < ((Enumm) interclass).getAtributsList().size(); i++) {
                ClassContent k = ((Enumm) interclass).getAtributsList().get(i);
                Iterator e = diagramView.getPainteri().iterator();
                while (e.hasNext()) {
                    ElementPainteri next = (ElementPainteri) e.next();

                    String novarec[] = null;
                    if (next instanceof EnumElementsPainter) {
                        if (((EnumElementsPainter) next).getEnumElements().equals(k)) {
                            e.remove();
                        }
                    }
                }
            }
            for (int i = 0; i < ((Enumm) interclass).getKonekcije().size(); i++) {
                Connection k = ((Enumm) interclass).getKonekcije().get(i);
                Iterator e = diagramView.getPainteri().iterator();
                while (e.hasNext()) {
                    ElementPainteri next = (ElementPainteri) e.next();
                    System.out.println(next.toString() + " next");
                    if (next instanceof AgregacijaPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    } else if (next instanceof ZavisnostPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    } else if (next instanceof GeneralizacijaPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    } else if (next instanceof KompozicijaPainter) {
                        next.getClassyTreeItem().removeFromParent();
                        if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                            e.remove();
                            next.getClassyTreeItem().removeFromParent();

                        }
                    }
                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                }
            }
            ((Enumm) interclass).getKonekcije().clear();
            ((Enumm)interclass).getAtributsList().clear();
            diagramView.getPainteri().remove(elementPainteri);
            diagramView.getDiagram().deleteChild(interclass);
        }
    }
}
