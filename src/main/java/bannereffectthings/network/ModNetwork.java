package bannereffectthings.network;

import bannereffectthings.BannerEffectThings;
import bannereffectthings.event.ActionUseCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ModNetwork {
    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(ActionUsePacket.ID, (server, player, handler, buf, responseSender) -> {
            ActionUsePacket packet = ActionUsePacket.fromBytes(buf);
            server.execute(() -> ActionUseCallback.EVENT.invoker().onActionUse(player, packet.action));
        });
    }
}
