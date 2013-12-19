# coding: utf-8
"""
This module contains the base implementation of a Python Minecraft Mod
which must be sub-classed by mods wanting to create a Minecraft Mod
written in Python.
"""

__author__ = "Caleb P. Burns"
__created__ = "2013-07-23"
__modified__ = "2013-09-28"

from cpburnz.minecraft.java import IPyMod

class PyMod(IPyMod):

	def preInit(self, event):
		pass

	def init(self, event):
		pass

	def interModComms(self, event):
		pass

	def postInit(self, event):
		pass

	def serverAboutToStart(self, event):
		pass

	def serverStarting(self, event):
		pass

	def serverStarted(self, event):
		pass

	def serverStopping(self, event):
		pass

	def serverStopped(self, event):
		pass
