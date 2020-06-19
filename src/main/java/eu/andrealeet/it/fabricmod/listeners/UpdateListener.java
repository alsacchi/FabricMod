package eu.andrealeet.it.fabricmod.listeners;

import java.util.ArrayList;

import eu.andrealeet.it.fabricmod.listener.Event;
import eu.andrealeet.it.fabricmod.listener.Listener;

public interface UpdateListener extends Listener {
    
    public void onUpdate();

    public static class UpdateEvent extends Event<UpdateListener> {

        public static final UpdateEvent INSTANCE = new UpdateEvent();

        @Override
        public void fire(ArrayList<UpdateListener> listeners) {
            for(UpdateListener listener : listeners) {
                listener.onUpdate();
            }
        }

        @Override
        public Class<UpdateListener> getListenerType() {
            return UpdateListener.class;
        }

    }


}