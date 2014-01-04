package cpburnz.minecraft.pymod;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

/**
 * The interface implemented by the Python-side of a Minecraft Mod to allow
 * the Java-side to access it easier.
 */
public interface IPythonMod {

	/**
	 * Called when the pre-initialization event occurs.
	 *
	 * *event* is the pre-initialization event.
	 */
	public void preInit(FMLPreInitializationEvent event);

	/**
	 * Called when the initialization event occurs.
	 *
	 * *event* is the initialization event.
	 */
	public void init(FMLInitializationEvent event);

	/**
	 * Called when the inter-mod communications event occurs.
	 *
	 * *event* is the inter-mod communications event.
	 */
	public void interModComms(FMLInterModComms event);

	/**
	 * Called when the post-initialization event occurs.
	 *
	 * *event* is the post-initialization event.
	 */
	public void postInit(FMLPostInitializationEvent event);

	/**
	 * Called when the server-about-to-start event occurs.
	 *
	 * *event* is the server-about-to-start event.
	 */
	public void serverAboutToStart(FMLServerAboutToStartEvent event);

	/**
	 * Called when the server-starting event occurs.
	 *
	 * *event* is the server-starting event.
	 */
	public void serverStarting(FMLServerStartingEvent event);

	/**
	 * Called when the server-stopped event occurs.
	 *
	 * *event* is the server-stopped event.
	 */
	public void serverStarted(FMLServerStartedEvent event);

	/**
	 * Called when the server-stopping event occurs.
	 *
	 * *event* is the server-stopping event.
	 */
	public void serverStopping(FMLServerStoppingEvent event);

	/**
	 * Called when the server-stopped event occurs.
	 *
	 * *event* is the server-stopped event.
	 */
	public void serverStopped(FMLServerStoppedEvent event);

}
