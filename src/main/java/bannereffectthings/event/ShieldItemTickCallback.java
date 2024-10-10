package bannereffectthings.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ShieldItemTickCallback {
    public static final Event<ShieldItemInventoryTickCallback> TICK = EventFactory.createArrayBacked(
            ShieldItemInventoryTickCallback.class,
            (listeners) -> (ItemStack stack, World world, Entity entity, int slot, boolean selected) -> {
                for (final var listener : listeners) {
                    listener.onShieldItemInventoryTick(stack, world, entity, slot, selected);
                }
            });
    public static final Event<ShieldItemHandTickCallback> HAND = EventFactory.createArrayBacked(
            ShieldItemHandTickCallback.class,
            (listeners) -> (ItemStack stack, World world, Entity entity, int slot, boolean selected) -> {
                for (final var listener : listeners) {
                    listener.onShieldItemHandTick(stack, world, entity, slot, selected);
                }
            });
    @FunctionalInterface
    public interface ShieldItemInventoryTickCallback {
        void onShieldItemInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected);
    }

    public interface ShieldItemHandTickCallback {
        void onShieldItemHandTick(ItemStack stack, World world, Entity entity, int slot, boolean selected);
    }
}
