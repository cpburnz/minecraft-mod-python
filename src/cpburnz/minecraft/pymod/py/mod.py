# coding: utf-8
"""
This module contains the base implementation of the Python-side of a
Minecraft Mod which must be sub-classed by mods wanting to create a
Minecraft Mod written in Python.
"""

from cpburnz.minecraft.pymod import IPythonMod

class PythonMod(IPythonMod):
	"""
	The ``PythonMod`` class must be sub-classed by the Python-side of a
	Minecraft Mod.
	"""

	def preInit(self, event):
		"""
		Called when the pre-initialization event occurs.

		*event* (``cpw.mods.fml.common.event.FMLPreInitializationEvent``) is
		the pre-initialization event.
		"""
		pass

	def init(self, event):
		"""
		Called when the initialization event occurs.

		*event* (``cpw.mods.fml.common.event.FMLInitializationEvent``) is
		the initialization event.
		"""
		pass

	def interModComms(self, event):
		"""
		Called when the inter-mod communications event occurs.

		*event* (``cpw.mods.fml.common.event.FMLInterModComms``) is the
		inter-mod communications event.
		"""
		pass

	def postInit(self, event):
		"""
		Called when the post-initialization event occurs.

		*event* (``cpw.mods.fml.common.event.FMLPostInitializationEvent``)
		is the post-initialization event.
		"""
		pass

	def serverAboutToStart(self, event):
		"""
		Called when the server-about-to-start event occurs.

		*event* (``cpw.mods.fml.common.event.FMLServerAboutToStartEvent``)
		is the server-about-to-start event.
		"""
		pass

	def serverStarting(self, event):
		"""
		Called when the server-starting event occurs.

		*event* (``cpw.mods.fml.common.event.FMLServerStartingEvent``) is
		the server-starting event.
		"""
		pass

	def serverStarted(self, event):
		"""
		Called when the server-started event occurs.

		*event* (``cpw.mods.fml.common.event.FMLServerStartedEvent``) is the
		server-started event.
		"""
		pass

	def serverStopping(self, event):
		"""
		Called when the server-stopping event occurs.

		*event* (``cpw.mods.fml.common.event.FMLServerStoppingEvent``) is
		the server-stopping event.
		"""
		pass

	def serverStopped(self, event):
		"""
		Called when the server-stopped event occurs.

		*event* (``cpw.mods.fml.common.event.FMLServerStoppedEvent``) is the
		server-stopped event.
		"""
		pass
