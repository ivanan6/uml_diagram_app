package raf.dsw.classycraft.app.observer.message;

import java.time.LocalDateTime;

public interface MessageGenerator {
    void addLogger(Logger logger);
    void removeLogger(Logger logger);
    void notifyLoggers(Message message);
    void generateMessage(String text, MessageType type, LocalDateTime vreme);
}
