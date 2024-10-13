package bannereffectthings.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class ModEntityTypes {
    static final EntityType<ShieldExplodeEntity> SHIELD_EXPLODE_ENTITY = EntityType.Builder.create(ShieldExplodeEntity::new, SpawnGroup.MISC).build(null);
}
