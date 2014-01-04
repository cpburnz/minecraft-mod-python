package cpburnz.minecraft.pymod;

import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;

import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import cpw.mods.fml.common.FMLLog;

/**
 * A wrapper around the Python interpreter.
 */
public class Python {

	/**
	 * The Python system state which is also a the *sys* module.
	 */
	private PySystemState sys;

	/**
	 * A reference to *__import__()*.
	 */
	private PyObject importer;

	/**
	 * The Python interpreter.
	 */
	private PythonInterpreter interpreter;

	/**
	 * The logger to use.
	 */
	private Logger log;

	/**
	 * Initializes the ``Python`` instance.
	 */
	public Python() {
		// Create logger.
		this.log = Logger("pymod:" + this.getClass().getSimpleName();

		// Create python system state.
		this.log.fine("Create system state.");
		this.sys = new PySystemState();

		// Create python interpreter.
		this.log.fine("Create interpreter.");
		this.interpreter = new PythonInterpreter(null, this.sys);

		// Add java class loader path to python import path.
		// - NOTE: This is only needed if importing python modules from the
		//   file system (not from JARs).
		/*
		FMLLog.log("PyMod", Level.INFO, "Get class loader path."); // XXX
		final URL resource = this.getClass().getClassLoader().getResource(".");
		if (resource != null) {
			FMLLog.log("PyMod", Level.INFO, "Add class loader path."); // XXX
			this.state.path.insert(0, new PyString(resource.getPath()));
		}
		*/

		// Initialize python environment.
		this.log.fine("Initialize python environment.");
		final String pyModuleName = Py.newString(this.getClass().getPackage().getName() + ".py");
		final String PyInitName = Py.newString("__init__");
		final PyObject pyModule = this.importModule(pyModuleName);
		final PyObject pyInitFunc = pyModule.__getattr__(PyInitName);
		pyInitFunc.__call__();

		// Get importer.
		this.log.fine("Get importer.");
		final String pyImportName = Py.newString("__import__");
		this.importer = this.sys.getBuiltins().__getitem__(pyImportName);
	}

	/**
	 * Helper method to import a python module.
	 *
	 * *moduleName* is the python module to import.
	 *
	 * Returns the python module.w
	 */
	public PyObject importModule(String moduleName) {
		// Import the python module.
		// - NOTE: The importer returns the top-level module so grab the imported
		//   module from *sys.modules*.
		final PyString pyModuleName = Py.newString(moduleName);
		this.importer.__call__(pyModuleName);
		return this.sys.modules.__getitem__(pyModuleName);
	}
}
