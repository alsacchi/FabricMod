package eu.andrealeet.it.fabricmod;

import net.fabricmc.api.ModInitializer;
public final class TestInit implements ModInitializer {
	
	private static boolean initialized;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		if(initialized)
			throw new RuntimeException("Already initialized!");
		ModClient.INSTANCE.initialize();
		initialized = true;
	}
}
