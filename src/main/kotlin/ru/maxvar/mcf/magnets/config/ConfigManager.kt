package ru.maxvar.mcf.magnets.config

import kotlinx.serialization.json.Json
import net.fabricmc.loader.api.FabricLoader
import ru.maxvar.mcf.magnets.Mod.LOGGER
import ru.maxvar.mcf.magnets.Mod.MOD_ID
import java.nio.file.Files

private val configPath = FabricLoader.getInstance().configDir.resolve("${MOD_ID}.json")

class ConfigManager {

    companion object {
        var config: Config = Config()

        private fun load(): Config {
            LOGGER.info("Loading config from $configPath...")
            val configFromJson = Json.decodeFromString(Config.serializer(), Files.readString(configPath))
            //upgrade version, match option with older toggle
            if (configFromJson.version != 2) {
                LOGGER.info("Upgrading config version...")
                configFromJson.collectionOption =
                    if (configFromJson.pull) CollectionOption.PULL else CollectionOption.TELEPORT
                configFromJson.version = 2
            }
            return configFromJson
        }

        fun save() {
            LOGGER.info("Saving $MOD_ID config to $configPath...")
            Files.writeString(configPath, Json.encodeToString(Config.serializer(), config))
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
