package ru.maxvar.mcf.magnets.config

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

import net.fabricmc.loader.api.FabricLoader
import ru.maxvar.mcf.magnets.Mod.LOGGER
import ru.maxvar.mcf.magnets.Mod.MOD_ID
import java.io.BufferedReader
import java.nio.file.Files

private val configPath = FabricLoader.getInstance().configDirectory.toPath().resolve("${MOD_ID}.json")

private val GSON =
    GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create()

class ConfigManager {

    companion object {
        var config: Config = Config()

        private fun load(): Config {
            LOGGER.info("Loading config from $configPath...")
            Files.newBufferedReader(configPath).use { reader: BufferedReader? ->
                return GSON.fromJson(reader, Config::class.java)
            }
        }

        fun save() {
            LOGGER.info("Saving $MOD_ID config to $configPath...")
            LOGGER.debug("Config is $config")
            Files.newBufferedWriter(configPath).use {
                GSON.toJson(config, it)
            }
        }

        fun init() {
            if (!Files.exists(configPath)) {
                LOGGER.info("Creating $MOD_ID config file (${configPath})")
                save()
            }
            config = load()
            LOGGER.info("Loaded $MOD_ID config: $config")
        }
    }
}
