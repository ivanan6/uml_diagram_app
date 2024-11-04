package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.message.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

public class DodavanjeVezaButton extends AbstractClassyAction{
    public DodavanjeVezaButton(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/link.png"));
        putValue(NAME, "Add connection");
        putValue(SHORT_DESCRIPTION, "Add connection");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String kraj = "";
        JDialog.setDefaultLookAndFeelDecorated(true);
        Object[] selectionValues = {"Agregacija", "Kompozicija", "Zavisnost", "Generalizacija"};
        String basicSelection = "Agregacija";
        Object selection = JOptionPane.showInputDialog(null, "Koji tip veze zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
        while(selection == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
            selection = JOptionPane.showInputDialog(null, "Koji tip veze zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
        }
        kraj += selection.toString() + " ";
        String s = selection.toString();
        if(s.equals("Agregacija") || s.equals("Kompozicija")){
            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValues2 = {"0..1", "0..*"};
            String basicSelection2 = "0..1";
            Object selection2 = JOptionPane.showInputDialog(null, "Izaberite kardinalnost", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
            while(selection2 == null){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                selection2 = JOptionPane.showInputDialog(null, "Izaberite kardinalnost", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
            }
            kraj += selection2 + " ";
            JFrame f = new JFrame();
            String name = JOptionPane.showInputDialog(f, "Unosite ime promenljive: ");
            while (name.isEmpty()|| name == null) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                name = JOptionPane.showInputDialog(f, "Unosite ime promenljive: ");
            }
            kraj += name;
        } else if (s.equals("Zavisnost")) {
            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValues3 = {"Call", "Instantiate"};
            String basicSelection3 = "Call";
            Object selection3 = JOptionPane.showInputDialog(null, "Izaberite kardinalnost", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
            while(selection3 == null){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                selection3 = JOptionPane.showInputDialog(null, "Izaberite kardinalnost", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
            }
            kraj += selection3.toString();
        }

        MainFrame.getInstance().getPackageView().startDodavanjeVeze(kraj);
    }
}
