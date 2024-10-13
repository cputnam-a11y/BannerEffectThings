package bannereffectthings.mixin;

import bannereffectthings.BannerEffectThings;
import bannereffectthings.event.ShieldItemTickCallback;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
@Debug(export = true)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract boolean isBlocking();

    @Shadow
    public abstract ItemStack getMainHandStack();

    @Shadow
    public abstract ItemStack getOffHandStack();

    @Shadow
    public abstract Hand getActiveHand();

    @Shadow
    public abstract ItemStack getStackInHand(Hand hand);

    @Shadow
    public abstract int getItemUseTimeLeft();

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (this.getWorld().isClient()) return;
        if (this.isBlocking() && (this.getMainHandStack().isOf(Items.SHIELD) || this.getOffHandStack().isOf(Items.SHIELD))) {
            ShieldItemTickCallback.USAGE.invoker().onShieldItemUsageTick(this.getWorld(), (LivingEntity) (Object) this, this.getStackInHand(this.getActiveHand()), this.getItemUseTimeLeft());
        }
    }
}
