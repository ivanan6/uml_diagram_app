package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomOutButton extends AbstractClassyAction{
    public ZoomOutButton(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/zoom-out.png"));
        putValue(NAME, "Zoom out");
        putValue(SHORT_DESCRIPTION, "Zoom out");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = null;
        MainFrame.getInstance().getPackageView().startZoomOut(s);
    }

}
