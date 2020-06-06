package ru.maxvar.mcf.magnets.config

import kotlinx.serialization.Serializable

/**
 * defines the collecting behavior
 */
@Serializable
data class Config(
    /**
     * TRUE enables magnetic effect
     */
    var enabled: Boolean = true,
    /**
     * range of items collection in blocks
     */
    var range: Int = 5,
    /**
     * TRUE allows collection of XP orbs, FALSE affects items only
     */
    var collectXp: Boolean = true,
    /**
     * TRUE pulls items, FALSE "teleports" them to player
     */
    var pull: Boolean = true
)
