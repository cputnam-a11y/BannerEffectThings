package bannereffectthings;

import bannereffectthings.entity.ModEntityTypes;
import bannereffectthings.event.ShieldActionUseCallback;
import bannereffectthings.event.ShieldItemTickCallback;
import bannereffectthings.event.ShieldUseCallback;
import bannereffectthings.handler.EnchantedGolderAppleShieldHandler;
import bannereffectthings.handler.ShieldItemHandler;
import bannereffectthings.handler.SkullShieldHandler;
import bannereffectthings.network.ModNetwork;
import bannereffectthings.util.BannerPatternHelper;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BannerEffectThings implements ModInitializer {
	public static final String MOD_ID = "bannereffectthings";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntityTypes.init();
		ModNetwork.init();
		ShieldItemHandler handler = new ShieldItemHandler();

		handler.add(new EnchantedGolderAppleShieldHandler());
		handler.add(new SkullShieldHandler());

		ShieldItemTickCallback.HAND.register(handler);
		ShieldItemTickCallback.TICK.register(handler);
		ShieldItemTickCallback.USAGE.register(handler);
		ShieldUseCallback.EVENT.register(handler);
		ServerLivingEntityEvents.AFTER_DEATH.register(handler);
		ShieldActionUseCallback.EVENT.register(handler);
	}
	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}