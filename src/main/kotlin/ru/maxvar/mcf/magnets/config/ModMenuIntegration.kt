package ru.maxvar.mcf.magnets.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import me.shedaniel.clothconfig2.api.ConfigBuilder
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text
import ru.maxvar.mcf.magnets.Mod.MOD_ID

@Suppress("unused")
class ModMenuIntegration : ModMenuApi {

    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return MyConfigScreenFactory()
    }
}

class MyConfigScreenFactory() : ConfigScreenFactory<Screen> {
    override fun create(parent: Screen?): Screen {
        val config = ConfigManager.config
        val builder =
            ConfigBuilder.create().setParentScreen(parent)
                .setTitle(Text.of("config.$MOD_ID.title"))
                .apply {
                    getOrCreateCategory(Text.of("general"))
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startBooleanToggle(Text.of("Enabled"), config.enabled)
                                .setSaveConsumer { config.enabled = it }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startIntField(Text.of("Collection range"), config.range)
                                .setMin(3).setMax(50)
                                .setSaveConsumer { config.range = it }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startBooleanToggle(Text.of("Collect XP orbs"), config.collectXp)
                                .setSaveConsumer { config.collectXp = it }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startBooleanToggle(Text.of("Pull towards player (disabled = teleport to player)"), config.pull)
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
