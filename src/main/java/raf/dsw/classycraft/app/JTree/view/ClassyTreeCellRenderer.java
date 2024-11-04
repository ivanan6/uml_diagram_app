package raf.dsw.classycraft.app.JTree.view;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.*;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Connection;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        URL imageURL = null;
        if(((ClassyTreeItem)value).getClassyNode() instanceof ProjectExplorer){
            imageURL = getClass().getResource("/images/exploration.png");
        } else if (((ClassyTreeItem)value).getClassyNode() instanceof Project) {
            imageURL = getClass().getResource("/images/project.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Package) {
            imageURL = getClass().getResource("/images/box.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Diagram) {
            imageURL = getClass().getResource("/images/diagram.png");
        } else if (((ClassyTreeItem)value).getClassyNode() instanceof Klasa) {
            imageURL = getClass().getResource("/images/class.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Enumm) {
            imageURL = getClass().getResource("/images/enum.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Interfejs) {
            imageURL = getClass().getResource("/images/interface.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Connection) {
            imageURL = getClass().getResource("/images/link2.png");
        }


        Icon icon = null;
        if(imageURL != null){
            Image img = new ImageIcon(imageURL).getImage();
            Image newImg = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            icon = new ImageIcon(newImg);
            setIcon(icon);
        }
        return this;
    }
}
