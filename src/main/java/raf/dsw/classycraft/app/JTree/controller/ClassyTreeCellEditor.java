package raf.dsw.classycraft.app.JTree.controller;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor {
    private Object clickedOn =null;
    private JTextField edit = null;
    public ClassyTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if(event instanceof MouseEvent){
            if(((MouseEvent) event).getClickCount() == 3){
                return true;
            }
        }
        return false;
    }

    @Override
    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean isSelected, boolean expanded, boolean leaf, int row) {
        clickedOn = arg1;
        edit  = new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(clickedOn instanceof ClassyTreeItem)){
            return;
        }
        ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
        clicked.setName(e.getActionCommand());
    }
}
