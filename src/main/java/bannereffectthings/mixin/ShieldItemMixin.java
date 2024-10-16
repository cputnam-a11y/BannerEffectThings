package bannereffectthings.mixin;

import bannereffectthings.event.ShieldItemTickCallback;
import bannereffectthings.event.ShieldItemTooltipCallback;
import bannereffectthings.event.ShieldUseCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ShieldItem.class)
public class ShieldItemMixin extends ItemMixin {
    @Inject(method = "use", at = @At("HEAD"))
    private void use$Inject$HEAD(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ShieldUseCallback.EVENT.invoker().onShieldUse(world, user, hand);
    }

    @Override
    protected void inventoryTick$Inject$HEAD(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (entity instanceof LivingEntity entity1) {
            boolean mainHand;
            if ((mainHand = entity1.getMainHandStack() == stack) || entity1.getOffHandStack() == stack) {
                ShieldItemTickCallback.HAND.invoker().onShieldItemHandTick(stack, world, entity, mainHand ? Hand.MAIN_HAND : Hand.OFF_HAND, slot, selected);
            }
        }
        ShieldItemTickCallback.TICK.invoker().onShieldItemInventoryTick(stack, world, entity, slot, selected);
    }
    @Inject(method = "appendTooltip", at = @At("TAIL"))
    private void appendTooltip$Inject$TAIL(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        ShieldItemTooltipCallback.EVENT.invoker().onShieldItemTooltip(stack, world, context, tooltip);
    }
}
