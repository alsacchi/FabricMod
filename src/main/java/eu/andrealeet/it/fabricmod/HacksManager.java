package eu.andrealeet.it.fabricmod;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;

import eu.andrealeet.it.fabricmod.hacks.GiveTest;
import eu.andrealeet.it.fabricmod.hack.Hack;
import eu.andrealeet.it.fabricmod.hacks.MobESP;
import eu.andrealeet.it.fabricmod.hacks.PlayerESP;
import eu.andrealeet.it.fabricmod.hacks.Test;

public class HacksManager {
    
	public final PlayerESP playerESPHack = new PlayerESP();
	public final MobESP mobESPHack = new MobESP();
	public final GiveTest giveTestHack = new GiveTest();
	public final Test testHack = new Test();
	
    private final TreeMap<String, Hack> hax = new TreeMap<>((o1, o2) -> o1.compareToIgnoreCase(o2));
    
    public HacksManager() {
        try {
			for(Field field : HacksManager.class.getDeclaredFields()) {
				if(!field.getName().endsWith("Hack"))
					continue;
				
				Hack hack = (Hack)field.get(this);
				hax.put(hack.getName(), hack);
				System.out.println(hack.getName());
			}
			
		} catch(Exception e) {
            System.out.println("Error when initializing Hacks!");
		}
		
	}

	public Hack getHackByName(String name) {
		return hax.get(name);
	}

	public int countHax() {
		return hax.size();
	}

	public Collection<Hack> getHacks() {
		return Collections.unmodifiableCollection(hax.values());
	}
    

    
}