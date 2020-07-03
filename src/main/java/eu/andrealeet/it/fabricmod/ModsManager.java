package eu.andrealeet.it.fabricmod;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;


import eu.andrealeet.it.fabricmod.mods.ChangeMessage;

import eu.andrealeet.it.fabricmod.mods.FastBreak;

import eu.andrealeet.it.fabricmod.mods.FlyJump;

import eu.andrealeet.it.fabricmod.mods.GiveTest;

import eu.andrealeet.it.fabricmod.mod.Mod;

import eu.andrealeet.it.fabricmod.mods.MobESP;

import eu.andrealeet.it.fabricmod.mods.PlayerESP;

import eu.andrealeet.it.fabricmod.mods.Reach;

import eu.andrealeet.it.fabricmod.mods.SignBooks;

import eu.andrealeet.it.fabricmod.mods.Test;

import eu.andrealeet.it.fabricmod.mods.Timer;

public class ModsManager {
    
	public final PlayerESP playerESPMod = new PlayerESP();
	public final MobESP mobESPMod = new MobESP();
	public final GiveTest giveTestMod = new GiveTest();
	public final Test testMod = new Test();
	public final ChangeMessage changeMessageMod = new ChangeMessage();
	public final SignBooks signBooksMod = new SignBooks();
	public final Timer timerMod = new Timer();
	public final FastBreak fastBreakMod = new FastBreak();
	public final Reach reachMod = new Reach();
	public final FlyJump flyJumpMod = new FlyJump();
	
    private final TreeMap<String, Mod> mods = new TreeMap<>((o1, o2) -> o1.compareToIgnoreCase(o2));
    
    public ModsManager() {
        try {
			for(Field field : ModsManager.class.getDeclaredFields()) {
				if(!field.getName().endsWith("Mod"))
					continue;
				
				Mod Mod = (Mod)field.get(this);
				mods.put(Mod.getName(), Mod);
				System.out.println(Mod.getName());
			}
			
		} catch(Exception e) {
            System.out.println("Error when initializing Mods!");
		}
		
	}

	public Mod getModByName(String name) {
		return mods.get(name);
	}

	public int countMods() {
		return mods.size();
	}

	public Collection<Mod> getMods() {
		return Collections.unmodifiableCollection(mods.values());
	}
    

    
}