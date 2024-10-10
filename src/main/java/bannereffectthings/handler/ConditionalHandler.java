package bannereffectthings.handler;

import net.minecraft.item.ItemStack;

public interface ConditionalHandler {
    boolean shouldApply(ItemStack stack, Class<?> callbackClazz);
}
