package cpburnz.minecraft.java;

import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;

import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import cpw.mods.fml.common.FMLLog;

public class Jython {
	public PySystemState state;
	public PyObject importer;
	public PythonInterpreter interpreter;

	public Jython() {
		FMLLog.log("PyMod", Level.INFO, "Create state."); // XXX
		this.state = new PySystemState();
		FMLLog.log("PyMod", Level.INFO, "Create interpreter."); // XXX
		this.interpreter = new PythonInterpreter(null, this.state);

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

		// Execute initialization script.
		FMLLog.log("PyMod", Level.INFO, "Get init script.");
		final String scriptName = "jython/__init__.py";
		final InputStream scriptStream = this.getClass().getResourceAsStream(scriptName);
		if (scriptStream == null) {
			FMLLog.log("PyMod", Level.SEVERE, "Failed to find jython initialzation script.");
		}

		FMLLog.log("PyMod", Level.INFO, "Execute init script.");
		this.interpreter.execfile(scriptStream, scriptName);
		FMLLog.log("PyMod", Level.INFO, "Init script complete.");

		// Get importer.
		this.importer = this.state.getBuiltins().__getitem__(new PyString("__import__"));
	}

	public PyObject importModule(String moduleName) {
		final PyString pyModuleName = new PyString(moduleName);
		this.importer.__call__(pyModuleName);
		return this.state.modules.__getitem__(pyModuleName);
	}

	public PyObject importClass(String moduleName, String className) {
		return this.importModule(moduleName).__getattr__(Py.newString(className));
	}

	public PyObject callClass(String moduleName, String className) {
		return this.importClass(moduleName,  className).__call__();
	}

	public PyObject callClass(String moduleName, String className, Object[] args, String[] keywords) {
		PyObject pyargs[] = new PyObject[args.length];
		for (int i = 0; i < args.length; ++i) {
			pyargs[i] = Py.java2py(args[i]);
		}
		return this.importClass(moduleName, className).__call__(pyargs, keywords);
	}
}
