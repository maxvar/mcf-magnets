package ru.maxvar.mcf.magnets.config

import io.github.prospector.modmenu.api.ConfigScreenFactory
import io.github.prospector.modmenu.api.ModMenuApi
import me.shedaniel.clothconfig2.api.ConfigBuilder
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder
import net.minecraft.client.gui.screen.Screen
import ru.maxvar.mcf.magnets.Mod.MOD_ID

@Suppress("unused")
class ModMenuIntegration() : ModMenuApi {
    override fun getModId(): String = MOD_ID

    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return MyConfigScreenFactory()
    }


}

class MyConfigScreenFactory() : ConfigScreenFactory<Screen> {
    override fun create(parent: Screen?): Screen {
        val config = ConfigManager.config
        val builder =
            ConfigBuilder.create().setParentScreen(parent)
                .setTitle("config.$MOD_ID.title")
                .apply {
                    getOrCreateCategory("general")
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startBooleanToggle("Enabled", config.enabled)
                                .setDefaultValue(true)
                                .setSaveConsumer { config.enabled = it }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startIntField("Collection range", config.range)
                                .setDefaultValue(5)
                                .setSaveConsumer { config.range = it ?: 5 }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startBooleanToggle("Collect XP orbs", config.collectXp)
                                .setDefaultValue(true)
                                .setSaveConsumer { config.collectXp }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startBooleanToggle("Pull towards player (disabled = teleport to player)", config.pull)
                                .setDefaultValue(true)
                                .setSaveConsumer { config.pull = it }.build()
                        )
                    setSavingRunnable {
                        ConfigManager.config = config
                        ConfigManager.save()
                    }
                }
        return builder.build()
    }
}
