package raf.dsw.classycraft.app.undo;

public abstract class AbstractCommand {
    public abstract void doCommand();
    public abstract void undoCommand();
}
