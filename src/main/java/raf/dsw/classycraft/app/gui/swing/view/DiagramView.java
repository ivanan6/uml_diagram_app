package raf.dsw.classycraft.app.gui.swing.view;

import javafx.scene.transform.Scale;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.listeners.DiagramListener;
import raf.dsw.classycraft.app.observer.Subscriber;
import raf.dsw.classycraft.app.painters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class DiagramView extends JPanel implements Subscriber {
    private Diagram diagram;
    private JViewport viewport;
    private Klasa klasa;

    private JPanel panel;
    private String imeTaba;
    private double scale = 1;
    private AffineTransform affineTransform = new AffineTransform();
    private PackageView packageView;
    private ClassyTreeItem classyTreeItem;

    private List<ElementPainteri> painteri = new ArrayList<>();
    private List<ElementPainteri> selectionModel = new ArrayList<>();


    public DiagramView(Diagram prosledjeniDiagram, PackageView prosledjeniPackageView, ClassyTreeItem classyTreeItem) {
        this.classyTreeItem = classyTreeItem;
        this.packageView = prosledjeniPackageView;
        this.imeTaba = prosledjeniDiagram.getName();
        this.diagram = prosledjeniDiagram;
        this.diagram.addSubscriber(this);
        //addMouseListener(new MouseDragged(this));
        var xd = new DiagramListener(this);
        addMouseListener(xd);
        addMouseMotionListener(xd);
        this.setPreferredSize(new Dimension(5000, 5000));
    }

public void setTr(double scale, int x, int y){
        this.scale = scale;
        //JViewport j = packageView.getJViewport();
        //j.setViewPosition(new Point((int) (x*scale), (int) (y*scale)));

        //j.setLocation((int) (x*scale), (int) (y*scale));
        //packageView.getJScrollPane().setViewportView(this);
    affineTransform.setToIdentity();
    affineTransform.scale(this.scale, this.scale);

        repaint();
}
    @Override
    public void update(Object var1,String tekst) {
        String staroIme = this.imeTaba;
        if(tekst.equals("crtanje")){
            repaint();
        }
        else if(var1 == "null"){
            packageView.promenaImena("null", staroIme);
        }else{
            imeTaba = String.valueOf(var1);
            packageView.promenaImena(imeTaba, staroIme);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g = (Graphics2D)g;
        ((Graphics2D) g).transform(affineTransform);
        for(ElementPainteri elementi : this.painteri){
            if(elementi instanceof KlasaPainter){

                ((Klasa)elementi.getDiagramElements()).setBroj(5);
                elementi.draw(g, this);
            } else if (elementi instanceof InterfejsPainter) {
                ((Interfejs)elementi.getDiagramElements()).setBroj(5);
                elementi.draw(g, this);
            } else if (elementi instanceof EnumPainter) {
                ((Enumm)elementi.getDiagramElements()).setBroj(5);
                elementi.draw(g, this);
            }
            if(elementi instanceof AtributPainter){
                if(elementi.getDiagramElements() instanceof Klasa)
                     ((Klasa)elementi.getDiagramElements()).povecajBroj();
                elementi.draw(g, this);
            } else if (elementi instanceof MetodaPainter) {
                if(elementi.getDiagramElements() instanceof Klasa)
                     ((Klasa)elementi.getDiagramElements()).povecajBroj();
                else if(elementi.getDiagramElements() instanceof Interfejs)
                    ((Interfejs)elementi.getDiagramElements()).povecajBroj();
                elementi.draw(g, this);
            } else if (elementi instanceof EnumElementsPainter) {
                if(elementi.getDiagramElements() instanceof Klasa)
                    ((Klasa)elementi.getDiagramElements()).povecajBroj();
                else if(elementi.getDiagramElements() instanceof Enumm)
                    ((Enumm)elementi.getDiagramElements()).povecajBroj();
                elementi.draw(g, this);
            } else{
                elementi.draw(g, this);
            }

        }
    }
    public Point getOriginalCoordinates(Point scaledPoint) {
        try {
            AffineTransform inverseTransform = affineTransform.createInverse();
            Point originalPoint = new Point();
            inverseTransform.transform(scaledPoint, originalPoint);
            return originalPoint;
        } catch (NoninvertibleTransformException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
