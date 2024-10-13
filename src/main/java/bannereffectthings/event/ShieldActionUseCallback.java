package bannereffectthings.event;

import bannereffectthings.handler.base.Handler;
import bannereffectthings.network.ActionUsePacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

@FunctionalInterface
public interface ShieldActionUseCallback extends ActionUseCallback, Handler {
    @Override
    default void onActionUse(PlayerEntity player, ActionUsePacket.Action action) {
        boolean mainHand;
        if ((mainHand = player.getMainHandStack().isOf(Items.SHIELD)) || player.getOffHandStack().isOf(Items.SHIELD)) {
            Hand hand = mainHand ? Hand.MAIN_HAND : Hand.OFF_HAND;
            onShieldActionUse(player, hand, player.getStackInHand(hand), action);
        }
    }
    void onShieldActionUse(PlayerEntity player, Hand hand, ItemStack shield, ActionUsePacket.Action action);
}
