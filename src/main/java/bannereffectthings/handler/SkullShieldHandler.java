package bannereffectthings.handler;

import bannereffectthings.BannerEffectThings;
import bannereffectthings.entity.ModEntityTypes;
import bannereffectthings.entity.ShieldExplodeEntity;
import bannereffectthings.event.AfterDeathWithShieldCallback;
import bannereffectthings.event.ShieldActionUseCallback;
import bannereffectthings.handler.base.ConditionalHandler;
import bannereffectthings.network.ActionUsePacket;
import bannereffectthings.util.BannerPatternHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

import static bannereffectthings.event.ShieldItemTickCallback.*;

public class SkullShieldHandler implements ShieldItemHandTickCallback, ShieldActionUseCallback, AfterDeathWithShieldCallback, ConditionalHandler {
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

    protected final Vec3d getVectorForRotation(float pitch, float yaw) {
        float f = MathHelper.cos(-yaw * 0.017453292F - (float) Math.PI);
        float f1 = MathHelper.sin(-yaw * 0.017453292F - (float) Math.PI);
        float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        float f3 = MathHelper.sin(-pitch * 0.017453292F);
        return new Vec3d(f1 * f2, f3, f * f2);
    }

    @Override
    public void afterDeathWithShield(LivingEntity entity, DamageSource damageSource, Hand hand, ItemStack shield) {
        // @FORMATTER:OFF
        entity.getWorld().createExplosion(
                entity,
                createDamageSource(entity),
                createExplosionBehavior(),
                entity.getPos(),
                1.0F,
                true,
                World.ExplosionSourceType.NONE
        );
        // @FORMATTER:ON
    }

    private ExplosionBehavior createExplosionBehavior() {
        return new ExplosionBehavior() {
            @Override
            public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
                return false;
            }
        };
    }

    public DamageSource createDamageSource(Entity entity) {
        // @FORMATTER:OFF
        RegistryEntry<DamageType> type =
                entity.getWorld()
                        .getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .getEntry(DamageTypes.INDIRECT_MAGIC)
                        .get();
        // @FORMATTER:ON
        return new DamageSource(type, entity, entity);
    }

    @Override
    public void onShieldActionUse(PlayerEntity user, Hand hand, ItemStack shield, ActionUsePacket.Action action) {
        if (action != ActionUsePacket.Action.ACTION_ONE) {
            return;
        }
        World world = user.getWorld();
        ShieldExplodeEntity entity = new ShieldExplodeEntity(ModEntityTypes.SHIELD_EXPLODE_ENTITY, world);
        entity.setOwner(user);
        Vec3d v = getVectorForRotation(user.getPitch(), user.getYaw());
        entity.setVelocity(v.x, v.y, v.z);
        entity.setPosition(
                user.getPos()
                        .offset(Direction.UP, user.getEyeHeight(user.getPose()))
        );
        entity.setPitch(user.getPitch());
        entity.setYaw(user.getYaw());
        world.spawnEntity(entity);
    }
}
