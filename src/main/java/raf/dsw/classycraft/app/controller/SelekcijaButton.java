package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelekcijaButton extends AbstractClassyAction{
    public SelekcijaButton(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/selection.png"));
        putValue(NAME, "Selection");
        putValue(SHORT_DESCRIPTION, "Selection");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = null;
        MainFrame.getInstance().getPackageView().startSelekcija(s);
    }
}
