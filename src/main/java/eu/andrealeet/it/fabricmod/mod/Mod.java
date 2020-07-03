package eu.andrealeet.it.fabricmod.mod;

import eu.andrealeet.it.fabricmod.Feature;

public abstract class Mod extends Feature {

    private final String name;
    private final String description;


    private boolean enabled = false;

    public Mod(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public final String getName() {
        return (isEnabled() == true ? SECTION_SIGN + "a" : SECTION_SIGN + "f") + name;
    }

    public final String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        
        if(this.enabled == enabled) {
            return;
        }
        this.enabled = enabled;
        if(enabled)
            onEnable();
        else
            onDisable();
    } 

    protected void onEnable() {

    }   
    
    protected void onDisable() {

    }
}