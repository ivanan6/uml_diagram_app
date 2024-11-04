package raf.dsw.classycraft.app.observer.message;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter

public class Message {
    private String text;
    private MessageType type;
    private LocalDateTime vreme;

    public Message(String text, MessageType type, LocalDateTime vreme) {
        this.text = text;
        this.type = type;
        this.vreme = vreme;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return type + " " + vreme.format(formatter) + " " + text;
    }
}
