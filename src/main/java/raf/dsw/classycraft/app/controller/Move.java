package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Move extends AbstractClassyAction{
    public Move(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/move.png"));
        putValue(NAME, "Move object");
        putValue(SHORT_DESCRIPTION, "Move object");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = null;
        MainFrame.getInstance().getPackageView().startMove(s);
    }
}
