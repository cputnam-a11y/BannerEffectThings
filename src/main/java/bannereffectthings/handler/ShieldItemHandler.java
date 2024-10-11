package bannereffectthings.handler;

import bannereffectthings.event.ShieldItemTickCallback;
import bannereffectthings.event.ShieldUseCallback;
import bannereffectthings.handler.base.ConditionalHandler;
import bannereffectthings.handler.base.Handler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static bannereffectthings.event.ShieldItemTickCallback.*;

public class ShieldItemHandler implements ShieldItemInventoryTickCallback, ShieldItemHandTickCallback, ShieldUseCallback {
    final List<ShieldItemHandTickCallback> handCallbacks = new ArrayList<>();
    final List<ShieldItemInventoryTickCallback> inventoryCallbacks = new ArrayList<>();
    final List<ShieldUseCallback> useCallbacks = new ArrayList<>();

    @Override
    public void onShieldItemHandTick(ItemStack stack, World world, Entity entity, Hand hand, int slot, boolean selected) {
        for (final var handCallback : handCallbacks) {
            ConditionalHandler.maybeApply(
                    (Handler)handCallback,
                    stack,
                    ShieldItemHandTickCallback.class,
                    () -> handCallback.onShieldItemHandTick(stack, world, entity, hand, slot, selected)
            );
        }
    }

    @Override
    public void onShieldItemInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        for (final var inventoryCallback : inventoryCallbacks) {
            ConditionalHandler.maybeApply(
                    (Handler)inventoryCallback,
                    stack,
                    ShieldItemInventoryTickCallback.class,
                    () -> inventoryCallback.onShieldItemInventoryTick(stack, world, entity, slot, selected)
            );
        }
    }

    @Override
    public void onShieldUse(World world, PlayerEntity user, Hand hand) {
        for (final var useCallback : useCallbacks) {
            ConditionalHandler.maybeApply(
                    (Handler)useCallback,
                    user.getStackInHand(hand),
                    ShieldUseCallback.class,
                    () -> useCallback.onShieldUse(world, user, hand)
            );
        }
    }
    public void add(Handler callback) {
        if (callback instanceof ShieldItemTickCallback.ShieldItemHandTickCallback handCallback) {
            handCallbacks.add(handCallback);
        }
        if (callback instanceof ShieldItemTickCallback.ShieldItemInventoryTickCallback inventoryCallback) {
            inventoryCallbacks.add(inventoryCallback);
        }
        if (callback instanceof ShieldUseCallback useCallback) {
            useCallbacks.add(useCallback);
        }
    }
}
