package bannereffectthings.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

@FunctionalInterface
public interface AfterDeathWithShieldCallback extends ServerLivingEntityEvents.AfterDeath {
    @Override
    default void afterDeath(LivingEntity entity, DamageSource damageSource) {
        boolean main;
        if ((main = entity.getMainHandStack().isOf(Items.SHIELD)) || entity.getOffHandStack().isOf(Items.SHIELD)) {
            afterDeathWithShield(entity, damageSource, main ? Hand.MAIN_HAND : Hand.OFF_HAND, main ? entity.getMainHandStack() : entity.getOffHandStack());
        }
    }

    void afterDeathWithShield(LivingEntity entity, DamageSource damageSource, Hand hand, ItemStack shield);
}
