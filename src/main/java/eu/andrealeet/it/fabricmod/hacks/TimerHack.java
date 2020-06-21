package eu.andrealeet.it.fabricmod.hacks;

import eu.andrealeet.it.fabricmod.hack.Hack;

public class TimerHack extends Hack {
    public TimerHack() {
        super("Timer Hack", "Speeds up almost everything!");
    }

    public float getTimerSpeed() {
        return isEnabled() ? 2f : 1;
    }
}