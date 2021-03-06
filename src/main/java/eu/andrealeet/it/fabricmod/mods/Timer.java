package eu.andrealeet.it.fabricmod.mods;


import eu.andrealeet.it.fabricmod.mod.Mod;

public class Timer extends Mod {
    
    public Timer() {
        super("Double Time", "Speeds up almost everything!");
    }

    public float getTimerSpeed() {
        return isEnabled() ? 2f : 1;
    }
}