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
        //ConfigBuilder.create().setParentScreen(parent).setTitle()
        val config = ConfigManager.config
        val builder =
            ConfigBuilder.create().setParentScreen(parent)
                .setTitle("config.$MOD_ID.title")
                .apply {
                    getOrCreateCategory("general")
                        .addEntry(
                            ConfigEntryBuilder.create().startBooleanToggle("Ignore Empty Slots", config.enabled)
                                .setDefaultValue(true).setSaveConsumer { config.enabled = it ?: true }.build()
                        )
                        .addEntry(
                            ConfigEntryBuilder.create().startIntField("Effect range", config.range)
                                .setDefaultValue(5).setSaveConsumer { config.range = it ?: 5 }.build()
                        )
//                    .addEntry(
//                        ConfigEntryBuilder.create()
//                            .startKeyCodeField("Scroll Hotbar Vertically", config.singleColumn)
//                            .setDefaultValue(InputUtil.fromName("key.keyboard.left.shift")).setAllowMouse(false)
//                            .setSaveConsumer { b: KeyCode ->
//                                config.singleColumn = b.keyCode
//                            }.build()
//                    )
//                    .addEntry(ConfigEntryBuilder.create()
//                        .startKeyCodeField("Scroll Hotbar Horizontally", config.getRow())
//                        .setDefaultValue(InputUtil.fromName("key.keyboard.left.control")).setAllowMouse(false)
//                        .setSaveConsumer { b: KeyCode -> config.row = b.keyCode }
//                        .build()
//                    )
//                    .addEntry(
//                        ConfigEntryBuilder.create()
//                            .startKeyCodeField("Scroll Entire Hotbar Vertically", config.getAllColumn())
//                            .setDefaultValue(InputUtil.fromName("key.keyboard.left.alt")).setAllowMouse(false)
//                            .setSaveConsumer { b: KeyCode ->
//                                config.allColumn = b.keyCode
//                            }.build()
//                    )
                }
//    builder.setSavingRunnable(Config::save)
        builder.setSavingRunnable {  }
        return builder.build()
    }

}
