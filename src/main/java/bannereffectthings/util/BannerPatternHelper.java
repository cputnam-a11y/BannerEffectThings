package bannereffectthings.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class BannerPatternHelper {
    public static boolean hasEnchantedGoldenApple(ItemStack stack) {
        NbtCompound tag = stack.getSubNbt("BlockEntityTag");
        if (tag == null) {
            return false;
        }
        return tag.contains("Patterns") && tag.getList("Patterns", 10).stream().anyMatch((pattern) -> Objects.equals(((NbtCompound) pattern).getString("Pattern"), "moj"));
    }
    public static boolean hasSkull(ItemStack stack) {
        NbtCompound tag = stack.getSubNbt("BlockEntityTag");
        if (tag == null) {
            return false;
        }
        return tag.contains("Patterns") && tag.getList("Patterns", 10).stream().anyMatch((pattern) -> Objects.equals(((NbtCompound) pattern).getString("Pattern"), "sku"));
    }
}
