package bannereffectthings.mixin;

import bannereffectthings.util.ExplosionFlags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Set;

@Mixin(Explosion.class)
public class ExplosionMixin {
    @SuppressWarnings("MixinExtrasOperationParameters")
    @WrapOperation(method = "collectBlocksAndDamageEntities", at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z"))
    private <E> boolean wrapAdd(Set<E> instance, E e, Operation<Boolean> original) {
        if (ExplosionFlags.getPreventBlocks()) {
            ExplosionFlags.cancelPreventBlocks();
            return false;
        }
        return original.call(instance, e);
    }
}
