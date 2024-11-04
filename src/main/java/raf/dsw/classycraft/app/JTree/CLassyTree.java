package raf.dsw.classycraft.app.JTree;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.JTree.view.ClassyTreeView;
import raf.dsw.classycraft.app.composite.implementation.ProjectExplorer;

public interface CLassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(ClassyTreeItem parent, String ime);
    ClassyTreeItem getSelectedNode();
    void deleteChild(ClassyTreeItem parent);
}
