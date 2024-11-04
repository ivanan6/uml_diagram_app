package raf.dsw.classycraft.app.gui.swing;

import raf.dsw.classycraft.app.core.Gui;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.undo.CommandManager;

public class SwingGui implements Gui {
    private MainFrame mainFrame;
    private CommandManager commandManager;
    public void start() {
        commandManager = new CommandManager();
        disableRedoAction();
        disableUndoAction();
        mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }





    public SwingGui() {
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);

    }




    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);

    }
    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);


    }
    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);

    }
}
