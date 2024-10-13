package bannereffectthings.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static bannereffectthings.BannerEffectThings.id;

public class ModEntityTypes {
    public static final EntityType<ShieldExplodeEntity> SHIELD_EXPLODE_ENTITY = Registry.register(Registries.ENTITY_TYPE, id("explode_entity"), EntityType.Builder.create(ShieldExplodeEntity::new, SpawnGroup.MISC).build());
    public static void init() {}
}
