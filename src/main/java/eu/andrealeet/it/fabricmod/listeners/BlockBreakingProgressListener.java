package eu.andrealeet.it.fabricmod.listeners;

import eu.andrealeet.it.fabricmod.listener.Listener;

public interface BlockBreakingProgressListener extends Listener {

    public void onBlockBreakingProgress(BlockBreakingProgressEvent event);
    
}