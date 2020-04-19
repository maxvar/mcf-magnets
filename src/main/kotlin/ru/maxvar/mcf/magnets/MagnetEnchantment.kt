package ru.maxvar.mcf.magnets

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot

const val MAGNET_ENCHANTMENT_NAME = "magnet_enchantment"

class MagnetEnchantment(weight: Weight?, type: EnchantmentTarget?, slotTypes: Array<out EquipmentSlot>?) :
    Enchantment(weight, type, slotTypes) {


    override fun getMaximumLevel(): Int {
        return 4 //maximum IV
    }

    override fun getMinimumPower(level: Int): Int {
        return 1 //available at any level
    }

}