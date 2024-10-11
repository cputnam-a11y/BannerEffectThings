package bannereffectthings.handler;

import bannereffectthings.event.ShieldItemTickCallback;
import bannereffectthings.event.ShieldUseCallback;
import bannereffectthings.handler.base.ConditionalHandler;
import bannereffectthings.mixin.CreeperAccessor;
import bannereffectthings.mixin.EntityAccessor;
import bannereffectthings.util.BannerPatternHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import static bannereffectthings.event.ShieldItemTickCallback.*;
public class SkullShieldHandler implements ShieldItemHandTickCallback, ShieldUseCallback, ConditionalHandler {
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

    @Override
    public void onShieldUse(World world, PlayerEntity user, Hand hand) {
        CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, world);
        creeper.setPos(user.getX(), user.getY(), user.getZ());
        creeper.setFuseSpeed(0);
        ((EntityAccessor) creeper).getDataTracker().set(CreeperAccessor.CHARGED(), true);
        world.spawnEntity(creeper);
    }
}
