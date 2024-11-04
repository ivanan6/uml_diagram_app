package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.undo.CommandManager;

public interface Gui {

    void start();
    void disableUndoAction();
    void disableRedoAction();

    void enableUndoAction();
    void enableRedoAction();

    CommandManager getCommandManager();
}
