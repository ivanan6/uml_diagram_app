package raf.dsw.classycraft.app.undo;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager{
    private List<AbstractCommand> commands = new ArrayList<>();
    private int currentCommand = 0;
    private DiagramView diagramView;

    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size()){
            commands.remove(currentCommand);
        }
        commands.add(command);
        doComand();
    }

    public void doComand() {
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
            ApplicationFramework.getInstance().getGui().enableUndoAction();
        }
        if(currentCommand == commands.size()){
            ApplicationFramework.getInstance().getGui().disableRedoAction();
        }
    }
    public void undoCommand() {
        if(currentCommand > 0){
            ApplicationFramework.getInstance().getGui().enableRedoAction();
            commands.get(--currentCommand).undoCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
        }
        if(currentCommand == 0){
            ApplicationFramework.getInstance().getGui().disableUndoAction();
        }
    }

}
