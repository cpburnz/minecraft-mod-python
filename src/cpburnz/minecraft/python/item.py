# coding: utf-8
"""
This module contains the python implementation of a Minecraft Item.
"""

__author__ = "Caleb P. Burns"
__created__ = "2013-07-30"
__modified__ = "2013-08-03"

import java.lang
import net.minecraft.item

class Item(net.minecraft.item.Item):

	def __init__(self, itemId, icon):
		super(Item, self).__init__(itemId)
		self.icon = icon

	'''
	def __tojava__(self):
		return super(Item, self).__tojava__(net.minecraft.item.Item.__class__)
	'''

	def func_111208_A(self):
		print "<<<<<<<< HERE"
		print "inside func_111208: " + self.icon
		return self.icon
