package eu.andrealeet.it.fabricmod.listeners;

import java.util.ArrayList;
import java.util.Objects;

import eu.andrealeet.it.fabricmod.listener.CancellableEvent;

public class ChatOutputEvent extends CancellableEvent<ChatOutputListener> {
    private final String originalMessage;
    private String message;

    public ChatOutputEvent(String message) {
        this.message = Objects.requireNonNull(message);
        originalMessage = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public boolean isModified() {
        return !(originalMessage.equals(message));
    }

    @Override
    public void fire(ArrayList<ChatOutputListener> listeners) {
        for (ChatOutputListener listener : listeners) {
            listener.onSentMessage(this);

            if (isCancelled())
                break;
        }
    }

    @Override
    public Class<ChatOutputListener> getListenerType() {
        return ChatOutputListener.class;
    }
}