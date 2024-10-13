package bannereffectthings;

import bannereffectthings.entity.ShieldExplodeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ShieldExplodeEntityRenderer extends EntityRenderer<ShieldExplodeEntity> {

    protected ShieldExplodeEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }


    @Override
    public Identifier getTexture(ShieldExplodeEntity entity) {
        return null;
    }
}
