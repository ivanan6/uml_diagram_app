package raf.dsw.classycraft.app.JTree.controller;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class ClassyTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        ClassyTreeItem treeItemSelected = (ClassyTreeItem) path.getLastPathComponent();
        System.out.println("Selektovani cvor: " + treeItemSelected.getClassyNode().getName());
        System.out.println("Path: " + path);
    }
}
