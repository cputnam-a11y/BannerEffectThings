package bannereffectthings;

import bannereffectthings.entity.ModEntityTypes;
import bannereffectthings.event.ShieldItemTooltipCallback;
import bannereffectthings.tooltip.ModToolTipHandler;
import bannereffectthings.util.BannerPatternHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class BannerEffectThingsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRendererRegistry.register(ModEntityTypes.SHIELD_EXPLODE_ENTITY, ShieldExplodeEntityRenderer::new);
		ModKeyBinds.init();
		ShieldItemTooltipCallback.EVENT.register(new ModToolTipHandler());
	}
}