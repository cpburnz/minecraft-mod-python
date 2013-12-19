# coding: utf-8
"""
This module initializes the Jython environment.
"""

__author__ = "Caleb P. Burns"
__created__ = "2013-07-24"
__modified__ = "2013-11-03"

import __builtin__
import imp
import os.path
import sys
import types
from org.python.core import PyJavaPackage

#: The python module type and java package type.
MODULE_TYPES = (types.ModuleType, PyJavaPackage)

#: The built-in jython module importer.
builtin_import = __import__

def jython_import(name, globals=None, locals=None, fromlist=None, level=None):
	"""
	Overrides the default jython importer. This implements the default
	python import behavior (with only absolute imports however), and falls
	back to the default jython behavior.

	*name* (``str``) is the full dotted name of the module to import.

	*globals* (``dict``) is ignored.

	*locals* (``dict``) is ignored.

	*fromlist* (**iterable**) optionally contains the names of attributes
	or sub-modules that should be importetd from the module specified by
	*name*. Default is ``None`` for an empty from-list.

	*level* (``int``) is ignored. It is always treated as `0` for absolute
	imports.

	Returns the root package/module (``module``) specified by *name* if
	*fromlist* is empty; otherwise, the tail module (``module``) specified
	by *name* containing the attributes and sub-modules listed in
	*fromlist*.
	"""
	#print "IMPORT", name, fromlist; sys.stdout.flush() # XXX
	if not name:
		raise ValueError("Empty module name.")
	elif name.startswith('cpburns'):
		raise ValueError("Cannot import {}, it's 'cpburnz' not 'cpburns' idiot.".format(name))

	if isinstance(fromlist, basestring):
		raise ValueError("formlist:{!r} must contain strings, but must not be a string itself.".format(fromlist))

	# First, import desired module (we can worry about the from-list
	# later).
	pkg = None
	pkg_prefix = ''
	pkg_relpath = ''
	for mod_tailname in name.split('.'):
		mod_fullname = pkg_prefix + mod_tailname
		#print "IMPORT", mod_fullname; sys.stdout.flush() # XXX

		if mod_fullname in sys.modules:
			# The module for this segment is already imported.
			mod = sys.modules[mod_fullname]
			#print "SYS", mod; sys.stdout.flush() # XXX

		else:
			# Import the module for this segment.
			#print "FIND", mod_tailname, pkg_relpath; sys.stdout.flush() # XXX
			try:
				fh, path, desc = imp.find_module(mod_tailname, getattr(pkg, '__path__', None) or [os.path.join(path, pkg_relpath) for path in sys.path])
			except ImportError:
				#print "NOT FOUND", mod_fullname; sys.stdout.flush() # XXX
				#print "BUILTIN IMPORT", mod_fullname; sys.stdout.flush() # XXX
				builtin_import(mod_fullname)
				mod = sys.modules[mod_fullname]
			else:
				try:
					if pkg is not None and hasattr(pkg, mod_tailname):
						# The package has a conflicting attribute hiding the sub
						# module, raise an error.
						raise ImportError("{} attribute {!r} is set to {!r}, sub-module {} cannot be imported.".format(pkg.__name__, mod_tailname, getattr(pkg, mod_tailname), mod_fullname))
					# We found a compatible module, load it.
					#print "LOAD", mod_fullname, path, desc; sys.stdout.flush() # XXX
					mod = imp.load_module(mod_fullname, fh, path, desc)
				finally:
					if fh:
						fh.close()

		# Ensure the package has a reference to the module.
		if pkg is not None:
			# - NOTE: Jython only allows module attribute assignment via its
			#   internal dictionary.
			pkg.__dict__[mod_tailname] = mod

		# Prepare package variables for next iteration.
		pkg = mod
		pkg_prefix += mod_tailname + '.'
		pkg_relpath = os.path.join(pkg_relpath, mod_tailname)

	# Second, handle from-list.
	# - If we only have attributes its easy because we can just return the
	#   the tail module.
	# - If we have sub-modules listed, we have to import those before we
	#   can return the tail module.
	if fromlist:
		#print "FROM", fromlist; sys.stdout.flush() # XXX
		#print mod.__dict__ # XXX

		# Make sure each attribute in the from-list is in the tail module.
		# - NOTE: *mod* will already be the tail module from the first step
		#   above.
		for fromname in fromlist:
			if hasattr(mod, fromname):
				# The from-name already exists, do nothing.
				#print "ATTR", fromname; sys.stdout.flush() # XXX
				pass
			elif isinstance(mod, MODULE_TYPES):
				# Since the tail module is a package, import the from-name as a
				# sub-module.
				jython_import(name + '.' + fromname)
			else:
				# The tail module is not a package and thus there are no
				# potential sub-modules to import, raise an import error.
				raise ImportError("Cannot import name {} from {}.".format(fromname, name))

		# Return the tail module
		#print "RETURN", mod; sys.stdout.flush() # XXX
		#print mod.__dict__; sys.stdout.flush()
		return mod

	# Since the from-list is empty, return the root package/module.
	mod = sys.modules[name.split('.', 1)[0]]
	#print "RETURN", mod; sys.stdout.flush() # XXX
	#print mod.__dict__
	return mod

def override_import():
	"""
	Overrides the default importer with our custom importer.
	"""
	# Override built-in import function.
	__builtin__.__import__ = jython_import
	assert __import__ == jython_import, "Failed to override __import__:{!r} with jython_import:{!r}.".format(builtin_import, jython_import)
	assert sys.builtins['__import__'] == jython_import, "Failed to override sys.builtins['__import__']:{!r} with jython_import:{!r}.".format(sys.builtins['__import__'], jython_import)

def main():
	# override_import()
	pass

if __name__ == '__main__':
	main()
