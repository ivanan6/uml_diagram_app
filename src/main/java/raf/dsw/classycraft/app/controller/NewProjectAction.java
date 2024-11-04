package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.message.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

public class NewProjectAction extends AbstractClassyAction{
    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/add.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getCLassyTree().getSelectedNode();
        if(selected instanceof ClassyTreeItem){
            if(!(selected.getClassyNode() instanceof Diagram)){
                if(selected.getClassyNode() instanceof Package){
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    Object[] selectionValues = {"Package", "Diagram"};
                    String basicSelection = "Diagram";
                    Object selection = JOptionPane.showInputDialog(null, "Koji cvor zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                    while(selection == null){
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                        selection = JOptionPane.showInputDialog(null, "Koji cvor zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                    }


                    String s = selection.toString();
                    MainFrame.getInstance().getCLassyTree().addChild(selected, s);

                } else{
                    String s = null;
                    MainFrame.getInstance().getCLassyTree().addChild(selected, s);
                }
            }


        }

        else
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati cvor u stablu", MessageType.NODE_NOT_SELECTED, LocalDateTime.now());
    }

}
