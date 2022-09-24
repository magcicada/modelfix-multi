package net.mehvahdjukaar.modelfix.mixins;

import net.mehvahdjukaar.modelfix.ModelFix;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(TextureAtlasSprite.class)
public abstract class TextureAtlasSpriteMixin {

    @Shadow public abstract TextureAtlas atlas();

    @Shadow protected abstract float atlasSize();

    @Inject(method = "uvShrinkRatio", at = @At("HEAD"), cancellable = true)
    public void cancelShrink(CallbackInfoReturnable<Float> cir) {
        var newS = ModelFix.getShrinkRatio(this.atlas(), 4.0F / this.atlasSize(), cir.getReturnValueF());
        if(newS != -1) cir.setReturnValue(newS);
    }


}