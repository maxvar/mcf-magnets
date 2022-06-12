package ru.maxvar.mcf.magnets

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory
import ru.maxvar.mcf.magnets.config.ConfigManager


object Mod : ModInitializer {
    const val MOD_ID = "mcf-magnets"
    val LOGGER = LoggerFactory.getLogger(MOD_ID)!!

    override fun onInitialize() {
        ConfigManager.init()
        LOGGER.info("$MOD_ID init done")
    }
}
