package eu.andrealeet.it.test.listeners;

import java.util.ArrayList;

import eu.andrealeet.it.test.listener.Event;
import eu.andrealeet.it.test.listener.Listener;

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