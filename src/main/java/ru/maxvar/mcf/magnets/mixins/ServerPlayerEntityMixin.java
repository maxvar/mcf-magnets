package ru.maxvar.mcf.magnets.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static ru.maxvar.mcf.magnets.MagnetKt.tryToCollect;

@SuppressWarnings("unused")
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    //ignore this
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile, @Nullable PlayerPublicKey publicKey) {
        super(world, pos, yaw, profile, publicKey);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void beforeTick(CallbackInfo callbackInfo) {
        tryToCollect(this);
    }

}
