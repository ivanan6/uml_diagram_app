package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DuplicateButton extends AbstractClassyAction{
    public DuplicateButton() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/duplicate.png"));
        putValue(NAME, "Duplicate");
        putValue(SHORT_DESCRIPTION, "Duplicate");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startDuplicate();
    }
}
