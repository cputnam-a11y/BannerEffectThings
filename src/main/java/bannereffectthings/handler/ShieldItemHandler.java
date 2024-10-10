package bannereffectthings.handler;

import bannereffectthings.event.ShieldItemTickCallback;
import bannereffectthings.event.ShieldUseCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShieldItemHandler implements ShieldItemTickCallback.ShieldItemInventoryTickCallback, ShieldItemTickCallback.ShieldItemHandTickCallback, ShieldUseCallback {
    List<ShieldItemTickCallback.ShieldItemHandTickCallback> handCallbacks = new ArrayList<>();
    List<ShieldItemTickCallback.ShieldItemInventoryTickCallback> inventoryCallbacks = new ArrayList<>();
    HashMap<ShieldUseCallback, Boolean> useCallbacks = new HashMap<>();

    @Override
    public void onShieldItemHandTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        for (final var handCallback : handCallbacks) {
            if (handCallback instanceof ConditionalHandler ch) {
                if (ch.shouldApply(stack, handCallback.getClass())) {
                    handCallback.onShieldItemHandTick(stack, world, entity, slot, selected);
                }
                continue;
            }
            handCallback.onShieldItemHandTick(stack, world, entity, slot, selected);
        }
    }

    @Override
    public void onShieldItemInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        for (final var inventoryCallback : inventoryCallbacks) {
            if (inventoryCallback instanceof ConditionalHandler ch) {
                if (ch.shouldApply(stack, inventoryCallback.getClass())) {
                    inventoryCallback.onShieldItemInventoryTick(stack, world, entity, slot, selected);
                }
                continue;
            }
            inventoryCallback.onShieldItemInventoryTick(stack, world, entity, slot, selected);
        }
    }

    @Override
    public void onShieldUse(World world, PlayerEntity user, Hand hand) {
        for (final var entry : useCallbacks.entrySet()) {
            if (entry.getKey() instanceof ConditionalHandler ch) {
                if (ch.shouldApply(user.getStackInHand(hand), entry.getKey().getClass())) {
                    entry.getKey().onShieldUse(world, user, hand);
                }
                continue;
            }
            entry.getKey().onShieldUse(world, user, hand);
        }
    }
    public void add(ShieldUseCallback callback) {
        useCallbacks.put(callback, true);
    }
    public void add(ShieldItemTickCallback.ShieldItemHandTickCallback callback) {
        handCallbacks.add(callback);
    }
    public void add(ShieldItemTickCallback.ShieldItemInventoryTickCallback callback) {
        inventoryCallbacks.add(callback);
    }
}
