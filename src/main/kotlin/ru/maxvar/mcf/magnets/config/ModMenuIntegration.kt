package ru.maxvar.mcf.magnets.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import me.shedaniel.clothconfig2.api.ConfigBuilder
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.TranslatableText

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
                .setTitle(TranslatableText("mcf-magnets.config.title"))
                .apply {
                    getOrCreateCategory(TranslatableText("mcf-magnets.config.category.general"))
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startBooleanToggle(
                                    TranslatableText("mcf-magnets.config.enabled"), config.enabled
                                )
                                .setSaveConsumer { config.enabled = it }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startIntField(
                                    TranslatableText("mcf-magnets.config.collection.range"), config.range
                                )
                                .setMin(3).setMax(50)
                                .setSaveConsumer { config.range = it }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startBooleanToggle(TranslatableText("mcf-magnets.config.collect.xp"), config.collectXp)
                                .setSaveConsumer { config.collectXp = it }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startEnumSelector(
                                    TranslatableText("mcf-magnets.config.collection.option"),
                                    CollectionOption::class.java,
                                    config.collectionOption
                                )
                                .setTooltip(
                                    TranslatableText("mcf-magnets.config.collection.option.pull"),
                                    TranslatableText("mcf-magnets.config.collection.option.teleport"),
                                    TranslatableText("mcf-magnets.config.collection.option.inject")
                                )
                                .setSaveConsumer { config.collectionOption = it }.build()
                        )
                    setSavingRunnable {
                        ConfigManager.config = config
                        ConfigManager.save()
                    }
                }
        return builder.build()
    }
}
