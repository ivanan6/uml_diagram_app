package raf.dsw.classycraft.app.undo;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;

import javax.swing.*;

public class AddChildCommand extends AbstractCommand{
    private ClassyTreeItem parent;
    private ClassyTreeItem child;

    public AddChildCommand(ClassyTreeItem parent, ClassyTreeItem child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() {
        if(child == null || parent == null) return;
        parent.add(child);
        ((ClassyNodeComposite)parent.getClassyNode()).addChild(child.getClassyNode());
    }

    @Override
    public void undoCommand() {
        if(child == null || parent == null) return;
        child.removeFromParent();
        ((ClassyNodeComposite)(parent.getClassyNode())).deleteChild(child.getClassyNode());
    }
}