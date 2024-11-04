package raf.dsw.classycraft.app.gui.swing.view;


import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.JTree.CLassyTree;
import raf.dsw.classycraft.app.JTree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.observer.message.Logger;
import raf.dsw.classycraft.app.observer.message.Message;
import raf.dsw.classycraft.app.observer.message.MessageType;

import javax.swing.*;
import java.awt.*;
@Getter
@Setter
public class MainFrame extends JFrame implements Logger {
    private PackageView packageView;

    private static MainFrame insance;
    private ActionManager actionManager;
    private CLassyTree cLassyTree;
    private JTree jTree;

    //buduca polja za sve komponente view-a na glavnom prozoru
    private MainFrame(){
        actionManager = new ActionManager();
        ApplicationFramework.getInstance().getMessageGenerator().addLogger(this);
        cLassyTree = new ClassyTreeImplementation();

    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize((int) (screenWidth / 1.5), (int) (screenHeight / 1.5));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");



        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);



        jTree = cLassyTree.generateTree(ApplicationFramework.getInstance().getClassyRepository().getRoot());

        packageView = new PackageView(new FlowLayout());


        JScrollPane jScrollPane = new JScrollPane(jTree);
        jScrollPane.setMinimumSize(new Dimension(200, 150));
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollPane, packageView);
        getContentPane().add(split, BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);




    }
    public static MainFrame getInstance(){
        if(insance == null){
            insance = new MainFrame();
            insance.initialize();
        }
        return insance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    @Override
    public void update(Message message) {
        JOptionPane jOptionPane = new JOptionPane();
        JFrame f = new JFrame();
        if(message.getType()== MessageType.ERROR){
            jOptionPane.showMessageDialog(f,message.toString(),String.valueOf(MessageType.ERROR),jOptionPane.ERROR_MESSAGE);
        }
        if(message.getType() == MessageType.CANNOT_DELETE_PROJECT_EXPLORER){
            jOptionPane.showMessageDialog(f,message.toString(),String.valueOf(MessageType.CANNOT_DELETE_PROJECT_EXPLORER),jOptionPane.WARNING_MESSAGE);
        }
        if(message.getType() == MessageType.CANNOT_ADD_CHILD){
            jOptionPane.showMessageDialog(f,message.toString(),String.valueOf(MessageType.CANNOT_ADD_CHILD),jOptionPane.INFORMATION_MESSAGE);
        }
        if(message.getType() == MessageType.NODE_NOT_SELECTED){
            jOptionPane.showMessageDialog(f,message.toString(),String.valueOf(MessageType.NODE_NOT_SELECTED),jOptionPane.INFORMATION_MESSAGE);
        }
        if(message.getType() == MessageType.MUST_INSERT_NAME){
            jOptionPane.showMessageDialog(f,message.toString(),String.valueOf(MessageType.MUST_INSERT_NAME),jOptionPane.INFORMATION_MESSAGE);
        }
        if(message.getType() == MessageType.CANNOT_DELETE_FILE){
            jOptionPane.showMessageDialog(f,message.toString(),String.valueOf(MessageType.CANNOT_DELETE_FILE),jOptionPane.INFORMATION_MESSAGE);
        }
        if(message.getType() == MessageType.RESOURCE_NOT_FOUND){
            jOptionPane.showMessageDialog(f,message.toString(),String.valueOf(MessageType.RESOURCE_NOT_FOUND),jOptionPane.INFORMATION_MESSAGE);
        }
        if(message.getType() == MessageType.COMPONENT_NOT_SELECTED){
            jOptionPane.showMessageDialog(f,message.toString(),String.valueOf(MessageType.COMPONENT_NOT_SELECTED),jOptionPane.INFORMATION_MESSAGE);
        }
        if(message.getType() == MessageType.NAME_ALREADY_TAKEN){
            jOptionPane.showMessageDialog(f,message.toString(),String.valueOf(MessageType.NAME_ALREADY_TAKEN),jOptionPane.INFORMATION_MESSAGE);
        }
    }

}
