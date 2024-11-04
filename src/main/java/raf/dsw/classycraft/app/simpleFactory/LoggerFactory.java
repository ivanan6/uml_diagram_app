package raf.dsw.classycraft.app.simpleFactory;

import raf.dsw.classycraft.app.observer.message.Logger;
import raf.dsw.classycraft.app.observer.message.MessageGenerator;
import raf.dsw.classycraft.app.observer.message.MessageGeneratorImplementation;

public class LoggerFactory {
    public LoggerFactory() {
    }

    public Logger createLogger(String s){
        if(s.toUpperCase().equals("FILELOGGER")){
            return new FileLogger();
        }
        if(s.toUpperCase().equals("CONSOLELOGGER")){
            return new ConsoleLogger();
        }
        return null;
    }
}
