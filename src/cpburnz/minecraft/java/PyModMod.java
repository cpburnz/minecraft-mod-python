package cpburnz.minecraft.java;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="pymod", useMetadata=true)
public final class PyModMod {

	// The singleton instance of this mod for Forge.
	@Instance("PyMod")
	public static PyModMod instance;

	// The singleton instance of the python interpreter.
	public static Jython jython;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		FMLLog.log("PyMod", Level.INFO, "Initialzing Jython.");
		this.jython = new Jython();
		FMLLog.log("PyMod", Level.INFO, "Jython initialized.");
	}
}
