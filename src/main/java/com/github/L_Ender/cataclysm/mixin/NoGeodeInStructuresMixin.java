package com.github.L_Ender.cataclysm.mixin;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.init.ModTag;
import com.github.L_Ender.cataclysm.util.MixinUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.GeodeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(GeodeFeature.class)
public class NoGeodeInStructuresMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void cataclysm_noGeodeInStructures(FeaturePlaceContext<GeodeConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        if(!(context.level() instanceof WorldGenRegion)) {
            return;
        }

        SectionPos sectionPos = SectionPos.of(context.origin());
        if (context.level().getChunk(sectionPos.x(), sectionPos.z(), ChunkStatus.STRUCTURE_REFERENCES, false) == null) {
            Cataclysm.LOGGER.warn("Detected a mod with a broken Geode configuredfeature that is trying to place blocks outside the 3x3 safe chunk area for features. Find the broken mod and report to them to fix the placement of their magma feature.");
            return;
        }

        Registry<Structure> configuredStructureFeatureRegistry = context.level().registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY);
        StructureManager structureManager = ((WorldGenRegionAccessor)context.level()).getStructureManager();
        for (Holder<Structure> configuredStructureFeature : configuredStructureFeatureRegistry.getOrCreateTag(ModTag.BLOCKED_GEODE)) {
            if (MixinUtil.getStructureAt(structureManager, context.origin(),  configuredStructureFeature.value()).isValid()) {
                cir.setReturnValue(false);
            }
        }
    }
}
