package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyRepository;
import raf.dsw.classycraft.app.composite.implementation.ClassyRepositoryImplementation;
import raf.dsw.classycraft.app.composite.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.SwingGui;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.message.MessageGenerator;
import raf.dsw.classycraft.app.observer.message.MessageGeneratorImplementation;
import raf.dsw.classycraft.app.simpleFactory.LoggerFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Setter
@Getter

public class ApplicationFramework {
    protected Gui gui;
    private static ApplicationFramework instance;
    private ClassyRepository classyRepository;
    private MessageGenerator messageGenerator;
    private LoggerFactory loggerFactory;
    private ApplicationFramework(){
    }
    public void initialize(Gui gui){
        clearFile();
        this.gui = gui;
        messageGenerator = new MessageGeneratorImplementation();
        loggerFactory = new LoggerFactory();

        messageGenerator.addLogger(loggerFactory.createLogger("consolelogger"));
        messageGenerator.addLogger(loggerFactory.createLogger("filelogger"));
        classyRepository = new ClassyRepositoryImplementation();
        MainFrame.getInstance().setVisible(true);
        this.gui.start();

         //classyRepository.getRoot();
    }
    public static ApplicationFramework getInstance(){
        if(instance == null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public void clearFile(){
        File file = new File("log.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,false));
            bw.flush();

            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
