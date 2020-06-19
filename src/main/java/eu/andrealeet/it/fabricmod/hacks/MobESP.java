package eu.andrealeet.it.fabricmod.hacks;

import eu.andrealeet.it.fabricmod.hack.Hack;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;

public class MobESP extends Hack implements UpdateListener {
    private Iterable<Entity> entities;
    public MobESP() {
        super("MobESP", "Glowing MOBS!");
    }

    @Override
    protected void onEnable() {
            EVENTS.add(UpdateListener.class, this);
    }

    @Override
    protected void onDisable() {
        EVENTS.remove(UpdateListener.class, this);
        if(entities != null) {
            for (Entity entity : entities) {
                if(entity instanceof MobEntity) {
                    entity.setGlowing(false);
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        entities = MC.world.getEntities();
        for (Entity entity : entities) {
            if(entity instanceof MobEntity) {
                entity.setGlowing(true);
            }
        }
    }



}