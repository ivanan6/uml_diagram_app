package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractClassyAction {
    public UndoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/turn-left.png"));
        putValue(NAME, "Change content");
        putValue(SHORT_DESCRIPTION, "Change content");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ApplicationFramework.getInstance().getGui().getCommandManager().undoCommand();
    }
}
