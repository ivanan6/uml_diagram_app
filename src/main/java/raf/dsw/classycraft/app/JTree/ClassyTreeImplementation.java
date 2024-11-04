package raf.dsw.classycraft.app.JTree;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.JTree.view.ClassyTreeView;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.composite.factory.ClassyNodeFactory;
import raf.dsw.classycraft.app.composite.factory.FactoryUtil;
import raf.dsw.classycraft.app.composite.factory.ProjectExplorerFactory;
import raf.dsw.classycraft.app.composite.factory.ProjectFactory;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.undo.AbstractCommand;
import raf.dsw.classycraft.app.undo.AddChildCommand;
import raf.dsw.classycraft.app.undo.DeleteChildCommand;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeImplementation implements CLassyTree{
    private ClassyTreeView classyTreeView;
    private DefaultTreeModel treeModel;
    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        classyTreeView = new ClassyTreeView(treeModel);
        return classyTreeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent, String ime) {
        if(!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;
        ClassyNode child = createChild(parent.getClassyNode(), ime);
        ClassyTreeItem dete = new ClassyTreeItem(child);

            AbstractCommand command = new AddChildCommand(parent, dete);
            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);


        parent.add(dete);
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        classyTreeView.expandPath(classyTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(classyTreeView);

    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) classyTreeView.getLastSelectedPathComponent();
    }

    public ClassyNode createChild(ClassyNode parent, String naziv){
        ClassyNodeFactory factory = FactoryUtil.createFactory(parent);
        return factory.getChild(naziv);

    }

    @Override
    public void deleteChild(ClassyTreeItem selected) {
        ClassyNode dete = selected.getClassyNode();
        ClassyNodeComposite  roditelj = (ClassyNodeComposite) dete.getParent();
        AbstractCommand command = new DeleteChildCommand((ClassyTreeItem) selected.getParent(), selected);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
        roditelj.deleteChild(dete);
        selected.removeFromParent();
        SwingUtilities.updateComponentTreeUI(classyTreeView);
    }




}
