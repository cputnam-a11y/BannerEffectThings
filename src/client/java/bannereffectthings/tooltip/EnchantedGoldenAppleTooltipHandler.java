package bannereffectthings.tooltip;

import bannereffectthings.event.ShieldItemTooltipCallback;
import bannereffectthings.handler.base.ConditionalHandler;
import bannereffectthings.util.BannerPatternHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class EnchantedGoldenAppleTooltipHandler implements ShieldItemTooltipCallback, ConditionalHandler {
    private static final String[] effects = new String[]{
            "REGENERATION V",
            "ABSORPTION V",
            "RESISTANCE V",
            "FIRE_RESISTANCE V"
    };
    @Override
    public boolean shouldApply(ItemStack stack, Class<?> callbackClazz) {
        return callbackClazz == ShieldItemTooltipCallback.class && BannerPatternHelper.hasEnchantedGoldenApple(stack);
    }

    @Override
    public void onShieldItemTooltip(ItemStack stack, World world, TooltipContext context, List<Text> tooltip) {
        tooltip.add(
                Text.empty()
                        .append(
                                Text.literal(
                                        "PASSIVE:"
                                ).formatted(
                                        Formatting.GOLD
                                )
                        )
        );
        tooltip.add(
                Text.empty()
                        .append(
                                Text.literal(
                                        "  - "
                                ).formatted(
                                        Formatting.GRAY
                                )
                        )
                        .append(
                                Text.literal(
                                        "Grant the following effects while in hand:"
                                ).formatted(
                                        Formatting.WHITE
                                )
                        )
        );
        for (String effect : effects) {
            tooltip.add(
                    Text.empty()
                            .append(
                                    Text.literal(
                                            "    - "
                                    ).formatted(
                                            Formatting.GRAY
                                    )
                            )
                            .append(
                                    Text.literal(
                                            effect
                                    ).formatted(
                                            Formatting.WHITE
                                    )
                            )
            );
        }
    }
}
