package eu.andrealeet.it.test.hack;

import eu.andrealeet.it.test.Feature;

public abstract class Hack extends Feature {

    private final String name;
    private final String description;


    private boolean enabled = false;

    public Hack(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public final String getName() {
        return (isEnabled() == true ? "§a" : "§f") + name;
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