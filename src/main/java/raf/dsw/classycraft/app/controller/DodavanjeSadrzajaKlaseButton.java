package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.message.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DodavanjeSadrzajaKlaseButton extends AbstractClassyAction{
    public DodavanjeSadrzajaKlaseButton(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/writer.png"));
        putValue(NAME, "Add content");
        putValue(SHORT_DESCRIPTION, "Add content");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String kraj = "";
        JDialog.setDefaultLookAndFeelDecorated(true);
        Object[] selectionValues = {"Atribut", "Metoda", "Enum element"};
        String basicSelection = "Atribut";
        Object selection = JOptionPane.showInputDialog(null, "Koji element zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
        while(selection == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
            selection = JOptionPane.showInputDialog(null, "Koji element zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
        }
        String s = selection.toString();
        kraj += s + " ";
        if(s.equals("Atribut")){
            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValues5 = {"String", "int", "double", "float", "boolean"};
            String basicSelection5 = "String";
            Object selection5 = JOptionPane.showInputDialog(null, "Koji tip atributa zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues5, basicSelection5);
            while(selection5 == null){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                selection5 = JOptionPane.showInputDialog(null, "Koji tip atributa zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues5, basicSelection5);
            }
            String s5 = selection5.toString();
            kraj+= s5 +" ";
            JFrame f = new JFrame();
            String name = JOptionPane.showInputDialog(f, "Unesite ime atributa: ");
            while(name == null){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                name = JOptionPane.showInputDialog(f, "Unesite ime atributa: ");
            }
            kraj += name + " ";


        } else if (s.toLowerCase().equals("metoda")) {
            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValues6 = {"void", "int", "String", "boolean", "float", "double"};
            String basicSelection6 = "void";
            Object selection6 = JOptionPane.showInputDialog(null, "Koji povratni ti metode zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues6, basicSelection6);
            while(selection6 == null){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                selection6 = JOptionPane.showInputDialog(null, "Koji povratni ti metode zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues6, basicSelection6);
            }
            String s6 = selection6.toString();
            kraj += s6 + " ";
            JFrame f = new JFrame();
            String name = JOptionPane.showInputDialog(f, "Unesite ime metode: ");
            while(name == null){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                name = JOptionPane.showInputDialog(f, "Unesite ime metode: ");
            }
            kraj += name + " (";

            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValues9 = {"int", "String", "boolean", "float", "double", "Object", "nista"};
            String basicSelection9 = "int";
            Object selection9 = JOptionPane.showInputDialog(null, "Koji tip ulaznog argumenta zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues9, basicSelection9);
            while(selection9 == null || selection9 != "nista"){
                if(selection9 != null){
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Nastavite da birate tip argumenta", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                    kraj += selection9.toString() + ",";
                    selection9 = JOptionPane.showInputDialog(null, "Koji tip ulaznog argumenta zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues9, basicSelection9);
                }
                else{
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                    selection9 = JOptionPane.showInputDialog(null, "Koji tip ulaznog argumenta zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues9, basicSelection9);
                }
                }

            kraj += ") ";

        }else {
            JFrame f = new JFrame();
            String name = JOptionPane.showInputDialog(f, "Unesite ime enum elementa: ");
            while(name == null){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                name = JOptionPane.showInputDialog(f, "Unesite ime enum elementa: ");
            }
            kraj += name + " ";
        }


        ///////////////////////////////////////////////
        if(!(s.toLowerCase().equals("enum element"))){
            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValues2 = {"public", "private", "protected", "default"};
            String basicSelection2 = "public";
            Object selection2 = JOptionPane.showInputDialog(null, "Koju vidljvivost zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
            while(selection2 == null){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                selection2 = JOptionPane.showInputDialog(null, "Koju vidljvivost zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues2, basicSelection2);
            }
            String s2 = selection2.toString();
            kraj += s2 + " ";
            //////////////////////////////////////////////

            /////////////////////////////////////////////
            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValues3 = {"static", "nista"};
            String basicSelection3 = "nista";
            Object selection3 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete static?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
            while(selection3 == null){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                selection3 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete static", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues3, basicSelection3);
            }
            String s3 = selection3.toString();


            kraj += s3 + " ";


            ////////////////////////////////////////////

            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValues4 = {"apstrakt", "nista"};
            String basicSelection4 = "nista";
            Object selection4 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete abstrakt?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues4, basicSelection4);
            while(selection4 == null){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                selection4 = JOptionPane.showInputDialog(null, "Da li zelite da izaberete abstrakt?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues4, basicSelection4);
            }
            String s4 = selection4.toString();

            kraj += s4 + " ";
        }


        //System.out.println(kraj);

        MainFrame.getInstance().getPackageView().startDodavanjeSadrzajaKlase(kraj);
    }
}
