package bannereffectthings.handler;

import bannereffectthings.event.ShieldItemTickCallback;
import bannereffectthings.util.BannerPatternHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SkullShieldHandler implements ShieldItemTickCallback.ShieldItemHandTickCallback, ConditionalHandler{
    @Override
    public void onShieldItemHandTick(ItemStack stack, World world, Entity entity, Hand hand, int slot, boolean selected) {
        if (!(entity instanceof LivingEntity livingEntity))
            return;
        StatusEffectInstance e = livingEntity.getActiveStatusEffects().get(StatusEffects.SPEED);
        if (e == null || e.getAmplifier() < 1 || e.getDuration() < 10) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 40, 1, true, false, true));
        }
    }

    @Override
    public boolean shouldApply(ItemStack stack, Class<?> callbackClazz) {
        return BannerPatternHelper.hasSkull(stack);
    }
}
