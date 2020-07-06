package eu.andrealeet.it.fabricmod.listeners;

import java.util.ArrayList;

import eu.andrealeet.it.fabricmod.listener.CancellableEvent;

public class SetOpaqueCubeEvent extends CancellableEvent<SetOpaqueCubeListener> {

    @Override
		public void fire(ArrayList<SetOpaqueCubeListener> listeners) {
			for(SetOpaqueCubeListener listener : listeners) {
				listener.onSetOpaqueCube(this);
				
				if(isCancelled())
					break;
			}
		}
		
		@Override
		public Class<SetOpaqueCubeListener> getListenerType() {
			return SetOpaqueCubeListener.class;
		}

    
}