package net.example.client.mixin;

import net.example.client.ModuleManager;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;

        if (ModuleManager.isEnabled("Flight")) {
            player.getAbilities().flying = true;
            player.getAbilities().setFlySpeed(0.05f); 
        } else if (!player.isCreative() && !player.isSpectator()) {
            player.getAbilities().flying = false;
        }
    }
}
