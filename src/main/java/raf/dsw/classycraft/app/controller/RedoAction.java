package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractClassyAction{
    public RedoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/redo.png"));
        putValue(NAME, "Redo action");
        putValue(SHORT_DESCRIPTION, "Redo action");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ApplicationFramework.getInstance().getGui().getCommandManager().doComand();
    }
}
