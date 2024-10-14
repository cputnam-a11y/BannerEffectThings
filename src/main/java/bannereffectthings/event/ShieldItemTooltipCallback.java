package bannereffectthings.event;

import bannereffectthings.handler.base.Handler;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

public interface ShieldItemTooltipCallback extends Handler {
    Event<ShieldItemTooltipCallback> EVENT = EventFactory.createArrayBacked(
            ShieldItemTooltipCallback.class,
            (listeners) -> (stack, world, context, tooltip) -> {
                for (ShieldItemTooltipCallback listener : listeners) {
                    listener.onShieldItemTooltip(stack, world, context, tooltip);
                }
            });
    void onShieldItemTooltip(ItemStack stack, World world, TooltipContext context, List<Text> tooltip);
}
