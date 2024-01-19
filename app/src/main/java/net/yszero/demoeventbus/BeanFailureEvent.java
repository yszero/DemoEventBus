package net.yszero.demoeventbus;

public class BeanFailureEvent {

    public final String message;

    public BeanFailureEvent(String message) {
        this.message = message;
    }

}
