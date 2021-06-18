package ru.maxvar.mcf.magnets

import net.fabricmc.api.ModInitializer
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.apache.logging.log4j.LogManager
import ru.maxvar.mcf.magnets.config.ConfigManager


object Mod : ModInitializer {
    const val MOD_ID = "mcf-magnets"
    val LOGGER = LogManager.getFormatterLogger(MOD_ID)!!

    override fun onInitialize() {
        ConfigManager.init()
        LOGGER.info("$MOD_ID init done")
    }
}
