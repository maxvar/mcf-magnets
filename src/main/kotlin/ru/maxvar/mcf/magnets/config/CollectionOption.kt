package ru.maxvar.mcf.magnets.config

import net.minecraft.text.Text

enum class CollectionOption(private val intName: String) {
    PULL("mcf-magnets.config.collection.option.pull"),
    TELEPORT("mcf-magnets.config.collection.option.teleport"),
    INJECT("mcf-magnets.config.collection.option.inject");

    override fun toString(): String {
        return Text.translatable(intName).string
    }
}
