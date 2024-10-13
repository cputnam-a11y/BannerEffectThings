package bannereffectthings.event;

import bannereffectthings.handler.base.Handler;
import bannereffectthings.network.ActionUsePacket;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
@FunctionalInterface
public interface ActionUseCallback extends Handler {
    Event<ActionUseCallback> EVENT =
            EventFactory.createArrayBacked(ActionUseCallback.class, (listeners) -> (player, action) -> {
                for (ActionUseCallback listener : listeners) {
                    listener.onActionUse(player, action);
                }
            });
    void onActionUse(PlayerEntity player, ActionUsePacket.Action action);
}
