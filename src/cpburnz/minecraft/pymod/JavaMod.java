package cpburnz.minecraft.pymod;

import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyString;

import cpburnz.minecraft.pymod.IPythonMod;
import cpburnz.minecraft.pymod.Logger;
import cpburnz.minecraft.pymod.PyMod;
import cpw.mods.fml.common.Mod;
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
 * The base class for the Java-side of a Minecraft Mod.
 */
public abstract class JavaMod {

	/**
	 * The python mod instance.
	 */
	private IPythonMod pythonMod;

	/**
	 * The logger to use.
	 */
	private Logger log;

	/**
	 * Initializes the instance.
	 */
	public JavaMod() {
		// Create logger.
		final Mod modInfo = this.getClass().getAnnotation(Mod.class);
		final String logName = modInfo.modid();
		this.log = new Logger(logName);
	}

	/**
	 * Get the logger.
	 */
	public Logger getLog() {
		return this.log;
	}

	/**
	 * Get the name of the python mod class to use.
	 */
	public String getPythonClassName() {
		// Default to the same name as the java mod class.
		return this.getClass().getSimpleName();
	}

	/**
	 * Get the python mod instance.
	 */
	public IPythonMod getPythonMod() {
		return this.pythonMod;
	}

	/**
	 * Returns the name of the python module containing the python mod class to
	 * use.
	 */
	public String getPythonModuleName() {
		// Default to "{package}.py".
		return this.getClass().getPackage().getName() + ".py";
	}

	/**
	 * Instantiate the python mod instance and send it the pre-initialization
	 * event.
	 *
	 * *event* is the pre-initialization event.
	 */
	public void preInit(FMLPreInitializationEvent event) {
		// Instantiate python mod class.
		final String moduleName = this.getPythonModuleName();
		final String className = this.getPythonClassName();
		final PyString pyClassName = Py.newString(className);
		this.log.fine("Load %s.%s", moduleName, className);
		final PyObject pyModule = PyMod.python.importModule(moduleName);
		final PyObject pyModClass = pyModule.__getattr__(pyClassName);
		final PyObject pyModInst = pyModClass.__call__();
		this.pythonMod = (IPythonMod)pyModInst.__tojava__(IPythonMod.class);

		// Call mod pre-init.
		this.log.fine("Pre-initialization.");
		this.pythonMod.preInit(event);
	}

	/**
	 * Send the initialization event to the python mod.
	 *
	 * *event* is the initialization event.
	 */
	public void init(FMLInitializationEvent event) {
		this.log.fine("Initialization.");
		this.pythonMod.init(event);
	}

	/**
	 * Send the inter-mod communications event to the python mod.
	 *
	 * *event* is the inter-mod communications event.
	 */
	public void interModComms(FMLInterModComms event) {
		this.log.fine("Inter-mod communications.");
		this.pythonMod.interModComms(event);
	}

	/**
	 * Send the post-initialization event to the python mod.
	 *
	 * *event* is the post-initialization event.
	 */
	public void postInit(FMLPostInitializationEvent event) {
		this.log.fine("Post-initialization.");
		this.pythonMod.postInit(event);
	}

	/**
	 * Send the server-about-to-start event to the python mod.
	 *
	 * *event* is the server-about-to-start event.
	 */
	public void serverAboutToStart(FMLServerAboutToStartEvent event) {
		this.log.fine("Server about to start.");
		this.pythonMod.serverAboutToStart(event);
	}

	/**
	 * Send the server-starting event to the python mod.
	 *
	 * *event* is the server-starting event.
	 */
	public void serverStarting(FMLServerStartingEvent event) {
		this.log.fine("Server starting.");
		this.pythonMod.serverStarting(event);
	}

	/**
	 * Send the server-started event to the python mod.
	 *
	 * *event* is the server-started event.
	 */
	public void serverStarted(FMLServerStartedEvent event) {
		this.log.fine("Server started.");
		this.pythonMod.serverStarted(event);
	}

	/**
	 * Send the server-stopping event to the python mod.
	 *
	 * *event* is the server-stopping event.
	 */
	public void serverStopping(FMLServerStoppingEvent event) {
		this.log.fine("Server stopping.");
		this.pythonMod.serverStopping(event);
	}

	/**
	 * Send the server-stopped event to the python mod.
	 *
	 * *event* is the server-stopped event.
	 */
	public void serverStopped(FMLServerStoppedEvent event) {
		this.log.fine("Server stopped.");
		this.pythonMod.serverStopped(event);
	}

}
