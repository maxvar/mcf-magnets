package ru.maxvar.mcf.magnets

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW
import ru.maxvar.mcf.magnets.config.ConfigManager

@Environment(EnvType.CLIENT)
object ModClient : ClientModInitializer {

    var magnetEnabled = ConfigManager.config.enabled

    override fun onInitializeClient() {
        val keyBinding =
            KeyBinding(
                "mcf-magnets.keybinding.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_EQUAL,
                Text.translatable("mcf-magnets.config.title").string
            )

        KeyBindingHelper.registerKeyBinding(keyBinding)
        ClientTickEvents.END_CLIENT_TICK.register(
            ClientTickEvents.EndTick {
                if (keyBinding.wasPressed()) {
                    // Toggle message
                    it.player!!.sendMessage(
                        Text.translatable(if (!magnetEnabled) "mcf-magnets.msg.toggle_true" else "mcf-magnets.msg.toggle_false")
                    )
                    magnetEnabled = !magnetEnabled
                }
            }
        )
    }
}