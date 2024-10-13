package bannereffectthings.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

public class ShieldExplodeEntity extends ExplosiveProjectileEntity {
    private ShieldExplodeEntity(World world) {
        super(ModEntityTypes.SHIELD_EXPLODE_ENTITY, world);
    }

    public ShieldExplodeEntity(EntityType<ShieldExplodeEntity> ignored, World world) {
        super(ModEntityTypes.SHIELD_EXPLODE_ENTITY, world);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!getWorld().isClient) {
            // @FORMATTER:OFF
            this.getWorld().createExplosion(
                    this,
                    createDamageSource(),
                    createExplosionBehavior(),
                    this.getPos(),
                    1.0F,
                    true,
                    World.ExplosionSourceType.NONE
            );
            // @FORMATTER:ON
        }
        this.discard();
    }

    @Override
    protected boolean isBurning() {
        return false;
    }

    private ExplosionBehavior createExplosionBehavior() {
        return new ExplosionBehavior() {
            @Override
            public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
                return false;
            }

            @Override
            public boolean shouldDamage(Explosion explosion, Entity entity) {
                return super.shouldDamage(explosion, entity) && !(entity == ShieldExplodeEntity.this.getOwner());
            }
        };
    }

    public DamageSource createDamageSource() {
        // @FORMATTER:OFF
        RegistryEntry<DamageType> type =
                this.getWorld()
                        .getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .getEntry(DamageTypes.INDIRECT_MAGIC)
                        .get();
        // @FORMATTER:ON
        return new DamageSource(type, this, this);
    }

    @Override
    protected float getDrag() {
        return 0.99F;
    }
}
