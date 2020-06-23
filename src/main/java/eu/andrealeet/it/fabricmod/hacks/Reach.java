package eu.andrealeet.it.fabricmod.hacks;

import eu.andrealeet.it.fabricmod.hack.Hack;
import eu.andrealeet.it.fabricmod.mixininterfaces.IClientPlayerInteractionManager;

public class Reach extends Hack {
    
    public Reach() {
        super("Reach Hack", "Reach at longer distances!");
    }

    @Override
    public void onEnable() {
        IClientPlayerInteractionManager imc = IMC.getInteractionManager();
        if(!(imc == null))  
            imc.setOverrideReach(true);
    }

    @Override
    public void onDisable() {
        IClientPlayerInteractionManager imc = IMC.getInteractionManager();
        if(!(imc == null))  
            imc.setOverrideReach(false);
    }

}