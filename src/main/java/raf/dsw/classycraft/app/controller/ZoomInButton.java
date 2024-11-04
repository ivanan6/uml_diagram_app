package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomInButton extends AbstractClassyAction{
    public ZoomInButton(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/zoom-in.png"));
        putValue(NAME, "Zoom in");
        putValue(SHORT_DESCRIPTION, "Zoom in");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s= null;
        MainFrame.getInstance().getPackageView().startZoomIn(s);
    }
}
