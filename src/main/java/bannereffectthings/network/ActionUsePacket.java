package bannereffectthings.network;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class ActionUsePacket {
    public static final Identifier ID = new Identifier("bannereffectthings", "action_use");
    public final Action action;
    public ActionUsePacket(Action action) {
        this.action = action;
    }
    public void writeBytes(PacketByteBuf buf) {
        buf.writeEnumConstant(this.action);
    }
    public PacketByteBuf toBytes() {
        PacketByteBuf buf = PacketByteBufs.create();
        this.writeBytes(buf);
        return buf;
    }
    public static ActionUsePacket fromBytes(PacketByteBuf buf) {
        return new ActionUsePacket(buf.readEnumConstant(Action.class));
    }
    public enum Action {
        ACTION_ONE,
        ACTION_TWO
    }
}
