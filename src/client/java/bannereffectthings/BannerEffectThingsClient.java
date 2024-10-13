package bannereffectthings;

import bannereffectthings.entity.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class BannerEffectThingsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRendererRegistry.register(ModEntityTypes.SHIELD_EXPLODE_ENTITY, ShieldExplodeEntityRenderer::new);
		ModKeyBinds.init();
	}
}