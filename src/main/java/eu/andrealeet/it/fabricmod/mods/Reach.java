package eu.andrealeet.it.fabricmod.mods;


import eu.andrealeet.it.fabricmod.mod.Mod;
import eu.andrealeet.it.fabricmod.mixininterfaces.IClientPlayerInteractionManager;

public class Reach extends Mod {
    
    public Reach() {
        super("Reach Mod", "Reach at longer distances!");
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