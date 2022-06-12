package ru.maxvar.mcf.magnets.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import me.shedaniel.clothconfig2.api.ConfigBuilder
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

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
                .setTitle(Text.translatable("mcf-magnets.config.title"))
                .apply {
                    getOrCreateCategory(Text.translatable("mcf-magnets.config.category.general"))
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startBooleanToggle(
                                    Text.translatable("mcf-magnets.config.enabled"), config.enabled
                                )
                                .setSaveConsumer { config.enabled = it }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startIntField(
                                    Text.translatable("mcf-magnets.config.collection.range"), config.range
                                )
                                .setMin(3).setMax(50)
                                .setSaveConsumer { config.range = it }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startBooleanToggle(
                                    Text.translatable("mcf-magnets.config.collect.xp"),
                                    config.collectXp
                                )
                                .setSaveConsumer { config.collectXp = it }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create()
                                .startEnumSelector(
                                    Text.translatable("mcf-magnets.config.collection.option"),
                                    CollectionOption::class.java,
                                    config.collectionOption
                                )
                                .setTooltip(
                                    Text.translatable("mcf-magnets.config.collection.option.pull"),
                                    Text.translatable("mcf-magnets.config.collection.option.teleport"),
                                    Text.translatable("mcf-magnets.config.collection.option.inject")
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
