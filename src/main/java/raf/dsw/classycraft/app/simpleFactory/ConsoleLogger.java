package raf.dsw.classycraft.app.simpleFactory;

import raf.dsw.classycraft.app.observer.message.Logger;
import raf.dsw.classycraft.app.observer.message.Message;
import raf.dsw.classycraft.app.observer.message.MessageGenerator;
import raf.dsw.classycraft.app.observer.message.MessageGeneratorImplementation;

public class ConsoleLogger implements Logger {

    public ConsoleLogger() {
    }
    @Override
    public void update(Message message) {

        System.out.println(message);
    }
}
