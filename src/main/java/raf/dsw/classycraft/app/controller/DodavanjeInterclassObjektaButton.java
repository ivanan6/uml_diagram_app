package raf.dsw.classycraft.app.controller;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.message.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

public class DodavanjeInterclassObjektaButton extends AbstractClassyAction{
    public DodavanjeInterclassObjektaButton(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/recognition.png"));
        putValue(NAME, "Add object");
        putValue(SHORT_DESCRIPTION, "Add object");
    }
    @Override
    public void actionPerformed(ActionEvent e) {


            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValues = {"Klasa", "Interface", "Enum"};
            String basicSelection = "Klasa";
            Object selection = JOptionPane.showInputDialog(null, "Koji diagram element zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
            while(selection == null){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                selection = JOptionPane.showInputDialog(null, "Koji diagram element zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
            }
            String s = selection.toString();

        MainFrame.getInstance().getPackageView().startDodavanjeInterclassObjekta(s);

    }

}
