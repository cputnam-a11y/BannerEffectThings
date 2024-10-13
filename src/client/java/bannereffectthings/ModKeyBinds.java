package bannereffectthings;

import bannereffectthings.network.ActionUsePacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class ModKeyBinds {
    public static final KeyBinding ACTION_ONE = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.bannereffectthings.action_one", GLFW.GLFW_KEY_J, "key.categories.bannereffectthings"));
    public static final KeyBinding ACTION_TWO = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.bannereffectthings.action_two", GLFW.GLFW_KEY_K, "key.categories.bannereffectthings"));

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world == null) {
                return;
            }
            if (ACTION_ONE.wasPressed()) {
                ClientPlayNetworking.send(ActionUsePacket.ID, new ActionUsePacket(ActionUsePacket.Action.ACTION_ONE).toBytes());
                //noinspection StatementWithEmptyBody
                while (ACTION_ONE.wasPressed()) {
                }
            }
            if (ACTION_TWO.wasPressed()) {
                ClientPlayNetworking.send(ActionUsePacket.ID, new ActionUsePacket(ActionUsePacket.Action.ACTION_TWO).toBytes());
                //noinspection StatementWithEmptyBody
                while (ACTION_TWO.wasPressed()) {
                }
            }
        });
    }
}
