package bannereffectthings.tooltip;

import bannereffectthings.event.ShieldItemTooltipCallback;
import bannereffectthings.handler.base.ConditionalHandler;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class ModToolTipHandler implements ShieldItemTooltipCallback {
    private final List<ShieldItemTooltipCallback> handlers = List.of(
            new EnchantedGoldenAppleTooltipHandler()
    );

    @Override
    public void onShieldItemTooltip(ItemStack stack, World world, TooltipContext context, List<Text> tooltip) {
        for (ShieldItemTooltipCallback handler : handlers) {
            ConditionalHandler.maybeApply(
                    handler,
                    stack,
                    ShieldItemTooltipCallback.class,
                    () -> handler.onShieldItemTooltip(stack, world, context, tooltip)
            );
        }
    }
}
