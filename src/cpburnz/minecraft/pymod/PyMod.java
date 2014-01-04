package cpburnz.minecraft.pymod;

import cpburnz.minecraft.pymod.Logger;
import cpburnz.minecraft.pymod.Python;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * PyMod for Minecraft Forge.
 */
@Mod(modid="pymod", useMetadata=true)
public final class PyMod {

	/**
	 * The singleton instance of this mod for Forge.
	 */
	@Instance("pymod")
	public static PyMod instance;

	/**
	 * The singleton instance of the python interpreter.
	 */
	public static Python python;

	/**
	 * The logger to use.
	 */
	private Logger log;

	/**
	 * Initializes the instance.
	 */
	public PyMod() {
		// Create logger.
		final String logName = "pymod:" + this.getClass().getSimpleName();
		this.log = Logger(logName);
	}

	/**
	 * Called during pre-initialization. This is where you can read your config,
	 * create blocks, items, etc., and register them with the ``GameRegistry``.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		this.log.info("Begin python initialization.");
		this.python = new Python();
		this.log.info("Finished python initialization.");
	}
}
