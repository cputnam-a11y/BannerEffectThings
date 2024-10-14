package bannereffectthings.handler;

import bannereffectthings.event.AfterDeathWithShieldCallback;
import bannereffectthings.event.ShieldActionUseCallback;
import bannereffectthings.event.ShieldItemTickCallback;
import bannereffectthings.event.ShieldUseCallback;
import bannereffectthings.handler.base.ConditionalHandler;
import bannereffectthings.handler.base.Handler;
import bannereffectthings.network.ActionUsePacket;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static bannereffectthings.event.ShieldItemTickCallback.*;

public class ShieldItemHandler implements
        ShieldItemInventoryTickCallback,
        ShieldItemHandTickCallback,
        ShieldUseCallback,
        AfterDeathWithShieldCallback,
        ShieldActionUseCallback,
        ShieldItemUsageTickCallback,
        ServerTickEvents.EndTick,
        ServerTickEvents.StartTick{
    final List<ShieldItemHandTickCallback> handCallbacks = new ArrayList<>();
    final List<ShieldItemInventoryTickCallback> inventoryCallbacks = new ArrayList<>();
    final List<ShieldUseCallback> useCallbacks = new ArrayList<>();
    final List<AfterDeathWithShieldCallback> afterDeathWithShieldCallbacks = new ArrayList<>();
    final List<ShieldActionUseCallback> actionUseCallbacks = new ArrayList<>();
    final List<ShieldItemUsageTickCallback> usageTickCallbacks = new ArrayList<>();
    final List<ServerTickEvents.EndTick> endTickCallbacks = new ArrayList<>();
    final List<ServerTickEvents.StartTick> startTickCallbacks = new ArrayList<>();

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
        if (callback instanceof AfterDeathWithShieldCallback afterDeathWithShieldCallback) {
            afterDeathWithShieldCallbacks.add(afterDeathWithShieldCallback);
        }
        if (callback instanceof ShieldActionUseCallback actionUseCallback) {
            actionUseCallbacks.add(actionUseCallback);
        }
        if (callback instanceof ShieldItemUsageTickCallback usageTickCallback) {
            usageTickCallbacks.add(usageTickCallback);
        }
        if (callback instanceof ServerTickEvents.EndTick endTickCallback) {
            endTickCallbacks.add(endTickCallback);
        }
        if (callback instanceof ServerTickEvents.StartTick startTickCallback) {
            startTickCallbacks.add(startTickCallback);
        }
    }

    @Override
    public void onShieldItemHandTick(ItemStack stack, World world, Entity entity, Hand hand, int slot, boolean selected) {
        for (final var handCallback : handCallbacks) {
            ConditionalHandler.maybeApply(
                    handCallback,
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
                    inventoryCallback,
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
                    useCallback,
                    user.getStackInHand(hand),
                    ShieldUseCallback.class,
                    () -> useCallback.onShieldUse(world, user, hand)
            );
        }
    }

    @Override
    public void afterDeathWithShield(LivingEntity entity, DamageSource damageSource, Hand hand, ItemStack shield) {
        for (final var afterDeathWithShieldCallback : afterDeathWithShieldCallbacks) {
            ConditionalHandler.maybeApply(
                    afterDeathWithShieldCallback,
                    shield,
                    AfterDeathWithShieldCallback.class,
                    () -> afterDeathWithShieldCallback.afterDeathWithShield(entity, damageSource, hand, shield)
            );
        }
    }

    @Override
    public void onShieldActionUse(PlayerEntity player, Hand hand, ItemStack shield, ActionUsePacket.Action action) {
        for (final var actionUseCallback : actionUseCallbacks) {
            ConditionalHandler.maybeApply(
                    actionUseCallback,
                    shield,
                    ShieldActionUseCallback.class,
                    () -> actionUseCallback.onShieldActionUse(player, hand, shield, action)
            );
        }
    }

    @Override
    public void onShieldItemUsageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        for (final var usageTickCallback : usageTickCallbacks) {
            ConditionalHandler.maybeApply(
                    usageTickCallback,
                    stack,
                    ShieldItemUsageTickCallback.class,
                    () -> usageTickCallback.onShieldItemUsageTick(world, user, stack, remainingUseTicks)
            );
        }
    }

    @Override
    public void onEndTick(MinecraftServer server) {
        for (final var endTickCallback : endTickCallbacks) {
            endTickCallback.onEndTick(server);
        }
    }

    @Override
    public void onStartTick(MinecraftServer server) {
        for (final var startTickCallback : startTickCallbacks) {
            startTickCallback.onStartTick(server);
        }
    }
}
