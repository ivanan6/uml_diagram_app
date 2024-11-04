package raf.dsw.classycraft.app.JTree.view;

import raf.dsw.classycraft.app.JTree.controller.ClassyTreeCellEditor;
import raf.dsw.classycraft.app.JTree.controller.ClassyTreeSelectionListener;
import raf.dsw.classycraft.app.listeners.TabListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeView extends JTree {
    public ClassyTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        ClassyTreeCellRenderer classyTreeCellRenderer = new ClassyTreeCellRenderer();
        addTreeSelectionListener(new ClassyTreeSelectionListener());
        setCellEditor(new ClassyTreeCellEditor(this, classyTreeCellRenderer));
        setCellRenderer(classyTreeCellRenderer);
        addMouseListener(new TabListener());
        setEditable(true);
    }
}
