package raf.dsw.classycraft.app.observer;

public interface Publisher {
    void addSubscriber(Subscriber var1);
    void removeSubscriber(Subscriber var1);
    void notifySubscriber(Object var1, String tekst);
}
