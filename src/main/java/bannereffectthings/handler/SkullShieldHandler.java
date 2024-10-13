package bannereffectthings.handler;

import bannereffectthings.util.ExplosionFlags;
import bannereffectthings.event.AfterDeathWithShieldCallback;
import bannereffectthings.event.ShieldUseCallback;
import bannereffectthings.handler.base.ConditionalHandler;
import bannereffectthings.util.BannerPatternHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import static bannereffectthings.event.ShieldItemTickCallback.*;
public class SkullShieldHandler implements ShieldItemHandTickCallback, ShieldUseCallback, AfterDeathWithShieldCallback, ConditionalHandler {
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
//        CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, world);
//        creeper.setPos(user.getX(), user.getY(), user.getZ());
//        creeper.setFuseSpeed(0);
//        ((EntityAccessor) creeper).getDataTracker().set(CreeperAccessor.CHARGED(), true);
//        world.spawnEntity(creeper);
//        ExplosionFlags.setPreventBlocks();
        WitherSkullEntity skull = new WitherSkullEntity(EntityType.WITHER_SKULL, world);
        skull.setOwner(user);
        Vec3d v = getVectorForRotation(user.getPitch(), user.getYaw());
        skull.setVelocity(v.x, v.y, v.z);
        skull.setPosition(user.getPos().offset(Direction.UP, 1).add(v.normalize()));
        skull.setPitch(user.getPitch());
        skull.setYaw(user.getYaw());
        world.spawnEntity(skull);
//        user.getWorld().createExplosion(
//                user,
//                user.getX(), user.getY(), user.getZ(),
//                4f,
//                true,
//                World.ExplosionSourceType.MOB
//        );
    }
    protected final Vec3d getVectorForRotation(float pitch, float yaw)
    {
        float f = MathHelper.cos(-yaw * 0.017453292F - (float)Math.PI);
        float f1 = MathHelper.sin(-yaw * 0.017453292F - (float)Math.PI);
        float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        float f3 = MathHelper.sin(-pitch * 0.017453292F);
        return new Vec3d(f1 * f2, f3, f * f2);
    }
    @Override
    public void afterDeathWithShield(LivingEntity entity, DamageSource damageSource, Hand hand, ItemStack shield) {
        ExplosionFlags.setPreventBlocks();
        entity.getWorld().createExplosion(
                entity,
                entity.getX(), entity.getY(), entity.getZ(),
                4f,
                true,
                World.ExplosionSourceType.MOB
        );
    }
}
