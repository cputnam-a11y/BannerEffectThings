package bannereffectthings.handler;

import bannereffectthings.BannerEffectThings;
import bannereffectthings.event.ShieldItemTickCallback;
import bannereffectthings.handler.base.ConditionalHandler;
import bannereffectthings.util.BannerPatternHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EnchantedGolderAppleShieldHandler implements ShieldItemTickCallback.ShieldItemHandTickCallback,ShieldItemTickCallback.ShieldItemUsageTickCallback, ConditionalHandler {
    public boolean shouldApply(ItemStack stack, Class<?> callbackClazz) {
        return BannerPatternHelper.hasEnchantedGoldenApple(stack);
    }

    @Override
    public void onShieldItemHandTick(ItemStack stack, World world, Entity entity, Hand hand, int slot, boolean selected) {
//        if (!(entity instanceof LivingEntity livingEntity))
//            return;
//        StatusEffectInstance e = livingEntity.getActiveStatusEffects().get(StatusEffects.REGENERATION);
//        if (e == null || e.getAmplifier() < 1 || e.getDuration() < 10) {
//            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 5, true, false, true));
//        }
//        e = livingEntity.getActiveStatusEffects().get(StatusEffects.ABSORPTION);
//        if (e == null || e.getAmplifier() < 1 || e.getDuration() < 10) {
//            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 40, 5, true, false, true));
//        }
//        e = livingEntity.getActiveStatusEffects().get(StatusEffects.RESISTANCE);
//        if (e == null || e.getAmplifier() < 1 || e.getDuration() < 10) {
//            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 40, 5, true, false, true));
//        }
//        e = livingEntity.getActiveStatusEffects().get(StatusEffects.FIRE_RESISTANCE);
//        if (e == null || e.getAmplifier() < 1 || e.getDuration() < 10) {
//            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 40, 5, true, false, true));
//        }
    }

    @Override
    public void onShieldItemUsageTick(World world, LivingEntity livingEntity, ItemStack stack, int remainingUseTicks) {
        StatusEffectInstance e = livingEntity.getActiveStatusEffects().get(StatusEffects.REGENERATION);
        if (e == null || e.getAmplifier() < 5 || e.getDuration() < 10) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 5, true, false, true));
        }
        e = livingEntity.getActiveStatusEffects().get(StatusEffects.ABSORPTION);
        if (e == null || e.getAmplifier() < 5 || e.getDuration() < 10) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 40, 5, true, false, true));
        }
        e = livingEntity.getActiveStatusEffects().get(StatusEffects.RESISTANCE);
        if (e == null || e.getAmplifier() < 5 || e.getDuration() < 10) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 40, 5, true, false, true));
        }
        e = livingEntity.getActiveStatusEffects().get(StatusEffects.FIRE_RESISTANCE);
        if (e == null || e.getAmplifier() < 5 || e.getDuration() < 10) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 40, 5, true, false, true));
        }
    }
}
