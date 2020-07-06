package eu.andrealeet.it.fabricmod.listeners;

import eu.andrealeet.it.fabricmod.listener.Listener;

public interface RenderBlockEntityListener extends Listener {

    public void onRenderBlockEntity(RenderBlockEntityEvent event);

}