package bannereffectthings.handler;

import bannereffectthings.event.ShieldItemTickCallback;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SkullShieldHandler implements ShieldItemTickCallback.ShieldItemHandTickCallback, ConditionalHandler{
    @Override
    public void onShieldItemHandTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

    }

    @Override
    public boolean shouldApply(ItemStack stack, Class<?> callbackClazz) {
        return false;
    }
}
