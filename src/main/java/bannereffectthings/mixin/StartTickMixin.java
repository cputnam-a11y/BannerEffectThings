package bannereffectthings.mixin;

import bannereffectthings.handler.base.Handler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerTickEvents.StartTick.class)
public interface StartTickMixin extends Handler {
}
