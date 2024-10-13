package bannereffectthings.util;

public class ExplosionFlags {
    static boolean PREVENT_BLOCKS = false;
    public static void setPreventBlocks() {
        PREVENT_BLOCKS = true;
    }
    public static boolean getPreventBlocks() {
        return PREVENT_BLOCKS;
    }
    public static void cancelPreventBlocks() {
        PREVENT_BLOCKS = false;
    }
}
