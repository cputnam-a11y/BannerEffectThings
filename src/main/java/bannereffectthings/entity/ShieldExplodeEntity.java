package bannereffectthings.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ShieldExplodeEntity extends ProjectileEntity {
    private ShieldExplodeEntity(World world) {
        super(ModEntityTypes.SHIELD_EXPLODE_ENTITY, world);
    }
    public ShieldExplodeEntity(EntityType<ShieldExplodeEntity> entityType, World world) {
        super(ModEntityTypes.SHIELD_EXPLODE_ENTITY, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }
}
