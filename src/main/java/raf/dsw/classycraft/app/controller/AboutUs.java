package raf.dsw.classycraft.app.controller;



import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MyMenuBar;
import raf.dsw.classycraft.app.gui.swing.view.MyToolBar;
import raf.dsw.classycraft.app.gui.swing.view.ScenaZaAboutUs;
import raf.dsw.classycraft.app.observer.message.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

public class AboutUs extends AbstractClassyAction{
    private ScenaZaAboutUs scenaZaAboutUs;
    public AboutUs(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/user.png"));
        putValue(NAME, "About us");
        putValue(SHORT_DESCRIPTION, "About us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ScenaZaAboutUs sc = new ScenaZaAboutUs();
        sc.setVisible(true);
    }
}
