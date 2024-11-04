package raf.dsw.classycraft.app.listeners;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.Project;
import raf.dsw.classycraft.app.composite.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TabListener extends MouseAdapter {
    private List<Package> packageList = new ArrayList<>();

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            ClassyTreeItem selected = MainFrame.getInstance().getCLassyTree().getSelectedNode();
            if(selected != null && selected.getClassyNode() instanceof Package && !(packageList.contains(selected.getClassyNode()))){
                Package p = (Package) selected.getClassyNode();
                Package pNova = p;
                while(!(pNova.getParent() instanceof Project)){
                    pNova = (Package) pNova.getParent();
                }
                MainFrame.getInstance().getPackageView().setClassyTreeItem(selected);
                Project p1 = (Project) pNova.getParent();
                MainFrame.getInstance().getPackageView().dodajLabele(p1.getImeAutora(), p1.getName());
                MainFrame.getInstance().getPackageView().setaPackage(p, p1, (ProjectExplorer) p1.getParent());
                packageList.add(p);

                MainFrame.getInstance().getPackageView().revalidate();
                MainFrame.getInstance().getPackageView().repaint();
            } else if (packageList.contains(selected.getClassyNode())) {
                Package p = (Package) selected.getClassyNode();
                Package pNova = p;
                while(!(pNova.getParent() instanceof Project)){
                    pNova = (Package) pNova.getParent();
                }
                Project p1 = (Project) pNova.getParent();
                MainFrame.getInstance().getPackageView().dodajLabele(p1.getImeAutora(), p1.getName());
            }
        }
    }
}
