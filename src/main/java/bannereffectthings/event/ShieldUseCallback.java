package bannereffectthings.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@FunctionalInterface
public interface ShieldUseCallback {
    Event<ShieldUseCallback> EVENT = EventFactory.createArrayBacked(
            ShieldUseCallback.class,
            (listeners) -> (world, user, hand) -> {
                for (ShieldUseCallback listener : listeners) {
                    listener.onShieldUse(world, user, hand);
                }
            });

    void onShieldUse(World world, PlayerEntity user, Hand hand);
}
