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

        fun load(): Config {
            Files.newBufferedReader(configPath).use { reader: BufferedReader? ->
                return GSON.fromJson(reader, Config::class.java)
            }
        }

        fun save() {
            LOGGER.info("Saving config to $configPath...")
            GSON.toJson(config, Files.newBufferedWriter(configPath))
            LOGGER.info("Saving done")
        }

        fun init() {
            if (!Files.exists(configPath)) {
                LOGGER.info("Creating config file (${configPath})")
                save()
            }
            config = load()
        }
    }
}
