package ru.maxvar.mcf.magnets

import net.minecraft.entity.Entity
import net.minecraft.entity.ExperienceOrbEntity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.MovementType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import ru.maxvar.mcf.magnets.config.ConfigManager
import kotlin.math.absoluteValue

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
                is ExperienceOrbEntity -> config.collectXp && it.pickupDelay == 0
                else -> false
            }
        }
    items.forEach {
        val entityPos: Vec3d = it.pos
        if (config.pull) {
            val strength = config.range.toDouble()
            it.addVelocity(
                force(playerPos.x - entityPos.x, strength),
                force(playerPos.y - entityPos.y, strength),
                force(playerPos.z - entityPos.z, strength)
            )
        } else {
            val movement = Vec3d(
                playerPos.x - entityPos.x,
                playerPos.y - entityPos.y,
                playerPos.z - entityPos.z
            )
            it.move(MovementType.SELF, movement)
        }
    }
}

private fun force(distance: Double, strength: Double): Double =
    when {
        distance.absoluteValue > strength -> distance.times(0.01f)
        distance.absoluteValue > strength.div(2) -> distance.times(0.05f)
        else -> distance.times(0.1f)
    }
