package bannereffectthings.event;

import bannereffectthings.handler.base.Handler;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
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
            (listeners) -> (ItemStack stack, World world, Entity entity, Hand hand, int slot, boolean selected) -> {
                for (final var listener : listeners) {
                    listener.onShieldItemHandTick(stack, world, entity, hand, slot, selected);
                }
            });
    public static final Event<ShieldItemUsageTickCallback> USAGE = EventFactory.createArrayBacked(
            ShieldItemUsageTickCallback.class,
            (listeners) -> (World world, LivingEntity user, ItemStack stack, int remainingUseTicks) -> {
                for (final var listener : listeners) {
                    listener.onShieldItemUsageTick(world, user, stack, remainingUseTicks);
                }
            });
    @FunctionalInterface
    public interface ShieldItemInventoryTickCallback extends Handler {
        void onShieldItemInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected);
    }

    @FunctionalInterface
    public interface ShieldItemHandTickCallback extends Handler {
        void onShieldItemHandTick(ItemStack stack, World world, Entity entity, Hand hand, int slot, boolean selected);
    }

    @FunctionalInterface
    public interface ShieldItemUsageTickCallback extends Handler {
        void onShieldItemUsageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks);
    }
}
