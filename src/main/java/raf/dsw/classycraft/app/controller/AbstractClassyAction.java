package raf.dsw.classycraft.app.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public abstract class AbstractClassyAction extends AbstractAction{
    public Icon loadIcon(String fileName){

            Icon icon = null;
            URL ImageURL = getClass().getResource(fileName);
            if(ImageURL != null){
                Image img = new ImageIcon(ImageURL).getImage();
                Image newImg = img.getScaledInstance(35,30,Image.SCALE_DEFAULT);
                icon = new ImageIcon(newImg);
            }
            else{
                System.err.println("Resource not found: " + fileName);
            }
            return icon;
    }

    public abstract void actionPerformed(ActionEvent e);
}
