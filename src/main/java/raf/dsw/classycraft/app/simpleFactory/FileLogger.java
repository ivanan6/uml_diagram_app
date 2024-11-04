package raf.dsw.classycraft.app.simpleFactory;

import raf.dsw.classycraft.app.observer.message.Logger;
import raf.dsw.classycraft.app.observer.message.Message;
import raf.dsw.classycraft.app.observer.message.MessageGenerator;
import raf.dsw.classycraft.app.observer.message.MessageGeneratorImplementation;

import java.io.*;

public class FileLogger implements Logger {

    public FileLogger() {
    }
    @Override
    public void update(Message message) {

        File file = new File("log.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
            bw.write(String.valueOf(message + "\n"));

            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
