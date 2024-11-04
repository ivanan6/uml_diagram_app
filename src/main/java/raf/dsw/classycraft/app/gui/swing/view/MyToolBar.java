package raf.dsw.classycraft.app.gui.swing.view;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.controller.AboutUs;
import raf.dsw.classycraft.app.controller.ExitAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(){

        super(HORIZONTAL);
        setFloatable(false);



        add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        add(MainFrame.getInstance().getActionManager().getDeleteNode());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());

    }
}
