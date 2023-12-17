package com.github.L_Ender.cataclysm.init;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.structures.Burning_Arena_Structure;
import com.github.L_Ender.cataclysm.structures.Cursed_Pyramid_Structure;
import com.github.L_Ender.cataclysm.structures.RuinedCitadelStructure;
import com.github.L_Ender.cataclysm.structures.SoulBlackSmithStructure;
import com.github.L_Ender.cataclysm.structures.Sunken_City_Structure;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_DEF_REG = DeferredRegister.create(Registry.STRUCTURE_PIECE_REGISTRY, Cataclysm.MODID);
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE_DEF_REG = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, Cataclysm.MODID);

    public static final RegistryObject<StructureType<SoulBlackSmithStructure>> SOUL_BLACK_SMITH = STRUCTURE_TYPE_DEF_REG.register("soul_black_smith", () -> () -> SoulBlackSmithStructure.CODEC);
    public static final RegistryObject<StructureType<RuinedCitadelStructure>> RUINED_CITADEL = STRUCTURE_TYPE_DEF_REG.register("ruined_citadel", () -> () ->
            RuinedCitadelStructure.CODEC);
    public static final RegistryObject<StructureType<Burning_Arena_Structure>> BURNING_ARENA = STRUCTURE_TYPE_DEF_REG.register("burning_arena", () -> () ->
            Burning_Arena_Structure.CODEC);

    public static final RegistryObject<StructureType<Sunken_City_Structure>> SUNKEN_CITY = STRUCTURE_TYPE_DEF_REG.register("sunken_city", () -> () ->
            Sunken_City_Structure.CODEC);

    public static final RegistryObject<StructureType<Cursed_Pyramid_Structure>> CURSED_PYRAMID = STRUCTURE_TYPE_DEF_REG.register("cursed_pyramid", () -> () ->
            Cursed_Pyramid_Structure.CODEC);

    public static final RegistryObject<StructurePieceType> SBSP = STRUCTURE_PIECE_DEF_REG.register("soul_black_smith", () -> SoulBlackSmithStructure.Piece::new);
    public static final RegistryObject<StructurePieceType> RCP = STRUCTURE_PIECE_DEF_REG.register("ruined_citadel", () -> RuinedCitadelStructure.Piece::new);
    public static final RegistryObject<StructurePieceType> BAP = STRUCTURE_PIECE_DEF_REG.register("burning_arena", () -> Burning_Arena_Structure.Piece::new);
    public static final RegistryObject<StructurePieceType> SCP = STRUCTURE_PIECE_DEF_REG.register("sunken_city", () -> Sunken_City_Structure.Piece::new);
    public static final RegistryObject<StructurePieceType> CPD = STRUCTURE_PIECE_DEF_REG.register("cursed_pyramid", () -> Cursed_Pyramid_Structure.Piece::new);


    public static final ResourceKey<Structure> SOUL_BLACK_SMITH_KEY = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(Cataclysm.MODID, "soul_black_smith"));
    public static final ResourceKey<Structure> RUINED_CITADEL_KEY = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(Cataclysm.MODID, "ruined_citadel"));
    public static final ResourceKey<Structure> BURNING_ARENA_KEY = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(Cataclysm.MODID, "burning_arena"));
    public static final ResourceKey<Structure> ANCIENT_FACTORY_KEY = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(Cataclysm.MODID, "ancient_factory"));
    public static final ResourceKey<Structure> CURSED_PYRAMID_KEY = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(Cataclysm.MODID, "cursed_pyramid"));
}
