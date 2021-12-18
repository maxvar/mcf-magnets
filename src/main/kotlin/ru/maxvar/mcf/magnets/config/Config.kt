package ru.maxvar.mcf.magnets.config

import kotlinx.serialization.Serializable

/**
 * defines the collecting behavior
 */
@Serializable
data class Config(
    /**
     * added for compatibility
     */
    var version: Int = 0,
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
     * @deprecated being replaced with collectionOption
     */
    var pull: Boolean = true,
    /**
     * Pulls items or "teleports" or injects into inventory
     */
    var collectionOption: CollectionOption = CollectionOption.PULL
)
