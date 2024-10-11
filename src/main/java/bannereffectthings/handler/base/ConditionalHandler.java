package bannereffectthings.handler.base;

import net.minecraft.item.ItemStack;

public interface ConditionalHandler extends Handler {
    boolean shouldApply(ItemStack stack, Class<?> callbackClazz);

    static void maybeApply(Handler h, ItemStack stack, Class<?> callbackClazz, Runnable r) {
        if (h instanceof ConditionalHandler ch) {
            if (ch.shouldApply(stack, callbackClazz)) {
                r.run();
            }
            return;
        }
        r.run();
    }
}
