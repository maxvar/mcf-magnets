package ru.maxvar.mcf.magnets

import net.minecraft.entity.Entity
import net.minecraft.entity.ExperienceOrbEntity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import ru.maxvar.mcf.magnets.config.ConfigManager

fun tryToCollect(player: PlayerEntity) {
    val config = ConfigManager.config

    if (!config.enabled) return

    val range = config.range.toDouble()
    val playerPos = player.pos
    val box = Box(
        playerPos.x + range, playerPos.y + range, playerPos.z + range,
        playerPos.x - range, playerPos.y - range, playerPos.z - range
    )
    val items: List<Entity> =
        player.world.getEntities(Entity::class.java, box) {
            when (it) {
                is ItemEntity -> !it.cannotPickup()
                is ExperienceOrbEntity -> config.collectXp
                else -> false
            }
        }
    items.forEach {
        if (config.pull) {
            val entityPos: Vec3d = it.pos
            it.addVelocity(
                (playerPos.x - entityPos.x) * 0.08,
                (playerPos.y - entityPos.y) * 0.08,
                (playerPos.z - entityPos.z) * 0.08
            )
        } else {
            it.setPos(playerPos.x, playerPos.y, playerPos.z)
        }
    }
}
