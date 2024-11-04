package raf.dsw.classycraft.app.JTree.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;

import javax.swing.tree.DefaultMutableTreeNode;
@Getter
@Setter

public class ClassyTreeItem extends DefaultMutableTreeNode {
    private ClassyNode classyNode;

    public ClassyTreeItem(ClassyNode classyNode){
        this.classyNode = classyNode;
    }

    @Override
    public String toString() {
        return classyNode.getName();
    }
    public void setName(String name){
        this.classyNode.setName(name);
    }
}
