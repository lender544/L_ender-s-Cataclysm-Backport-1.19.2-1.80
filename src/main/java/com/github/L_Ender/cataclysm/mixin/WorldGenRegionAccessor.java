package com.github.L_Ender.cataclysm.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.StructureManager;

@Mixin(WorldGenRegion.class)
public interface WorldGenRegionAccessor {
    @Accessor("structureManager")
    StructureManager getStructureManager();
}
