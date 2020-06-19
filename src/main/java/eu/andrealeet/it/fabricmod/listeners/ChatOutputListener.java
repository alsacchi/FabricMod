package eu.andrealeet.it.fabricmod.listeners;

import eu.andrealeet.it.fabricmod.listener.Listener;

public interface ChatOutputListener extends Listener {

    public void onSentMessage(ChatOutputEvent event);

}