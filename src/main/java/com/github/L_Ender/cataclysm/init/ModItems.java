package com.github.L_Ender.cataclysm.init;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.entity.projectile.Void_Scatter_Arrow_Entity;
import com.github.L_Ender.cataclysm.items.Armortier;
import com.github.L_Ender.cataclysm.items.Athame;
import com.github.L_Ender.cataclysm.items.Blessed_Amethyst_Crab_Meat;
import com.github.L_Ender.cataclysm.items.Bloom_Stone_Pauldrons;
import com.github.L_Ender.cataclysm.items.Bulwark_of_the_flame;
import com.github.L_Ender.cataclysm.items.Coral_Bardiche;
import com.github.L_Ender.cataclysm.items.Coral_Spear;
import com.github.L_Ender.cataclysm.items.Gauntlet_of_Bulwark;
import com.github.L_Ender.cataclysm.items.Gauntlet_of_Guard;
import com.github.L_Ender.cataclysm.items.Ignitium_Armor;
import com.github.L_Ender.cataclysm.items.Ignitium_Elytra_ChestPlate;
import com.github.L_Ender.cataclysm.items.ItemInventoryOnly;
import com.github.L_Ender.cataclysm.items.Khopesh;
import com.github.L_Ender.cataclysm.items.Laser_Gatling;
import com.github.L_Ender.cataclysm.items.Meat_Shredder;
import com.github.L_Ender.cataclysm.items.ModFishBucket;
import com.github.L_Ender.cataclysm.items.ModItemArrow;
import com.github.L_Ender.cataclysm.items.ModItemTier;
import com.github.L_Ender.cataclysm.items.Monstrous_Helm;
import com.github.L_Ender.cataclysm.items.Necklace_Of_The_Desert;
import com.github.L_Ender.cataclysm.items.Remnant_Skull;
import com.github.L_Ender.cataclysm.items.The_Incinerator;
import com.github.L_Ender.cataclysm.items.Tidal_Claws;
import com.github.L_Ender.cataclysm.items.Void_Assault_SHoulder_Weapon;
import com.github.L_Ender.cataclysm.items.Wither_Assault_SHoulder_Weapon;
import com.github.L_Ender.cataclysm.items.final_fractal;
import com.github.L_Ender.cataclysm.items.infernal_forge;
import com.github.L_Ender.cataclysm.items.void_core;
import com.github.L_Ender.cataclysm.items.void_forge;
import com.github.L_Ender.cataclysm.items.zweiender;
import com.github.L_Ender.cataclysm.items.CuriosItem.Sandstorm_In_A_Bottle;
import com.github.L_Ender.cataclysm.items.Dungeon_Eye.AbyssEyeItem;
import com.github.L_Ender.cataclysm.items.Dungeon_Eye.DesertEyeItem;
import com.github.L_Ender.cataclysm.items.Dungeon_Eye.FlameEyeItem;
import com.github.L_Ender.cataclysm.items.Dungeon_Eye.MechEyeItem;
import com.github.L_Ender.cataclysm.items.Dungeon_Eye.MonstrousEyeItem;
import com.github.L_Ender.cataclysm.items.Dungeon_Eye.VoidEyeItem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            Cataclysm.MODID);

    public static final RegistryObject<BlockItem> ENDERITE_BLOCK = ITEMS.register("enderite_block",
            () -> new BlockItem(ModBlocks.ENDERRITE_BLOCK.get(), new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<BlockItem> WITHERITE_BLCOK = ITEMS.register("witherite_block",
            () -> new BlockItem(ModBlocks.WITHERITE_BLOCK.get(), new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<BlockItem> IGNITIUM_BLOCK = ITEMS.register("ignitium_block",
            () -> new BlockItem(ModBlocks.IGNITIUM_BLOCK.get(), new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<BlockItem> DUNGEON_BLOCK = ITEMS.register("dungeon_block",
            () -> new BlockItem(ModBlocks.DUNGEON_BLOCK.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> POLISHED_SANDSTONE = ITEMS.register("polished_sandstone",
            () -> new BlockItem(ModBlocks.POLISHED_SANDSTONE.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> POLISHED_END_STONE = ITEMS.register("polished_end_stone",
            () -> new BlockItem(ModBlocks.POLISHED_END_STONE.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> POLISHED_END_STONE_SLAB = ITEMS.register("polished_end_stone_slab",
            () -> new BlockItem(ModBlocks.POLISHED_END_STONE_SLAB.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> POLISHED_END_STONE_STAIRS = ITEMS.register("polished_end_stone_stairs",
            () -> new BlockItem(ModBlocks.POLISHED_END_STONE_STAIRS.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> CHISELED_END_STONE_BRICKS = ITEMS.register("chiseled_end_stone_bricks",
            () -> new BlockItem(ModBlocks.CHISELED_END_STONE_BRICKS.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> END_STONE_PILLAR = ITEMS.register("end_stone_pillar",
            () -> new BlockItem(ModBlocks.END_STONE_PILLAR.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> VOID_INFUSED_END_STONE_BRICKS = ITEMS.register("void_infused_end_stone_bricks",
            () -> new BlockItem(ModBlocks.VOID_INFUSED_END_STONE_BRICKS.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> VOID_STONE = ITEMS.register("void_stone",
            () -> new BlockItem(ModBlocks.VOID_STONE.get(), new Item.Properties().tab(Cataclysm.TAB).fireResistant()));

    public static final RegistryObject<BlockItem> VOID_LANTERN_BLOCK = ITEMS.register("void_lantern_block",
            () -> new BlockItem(ModBlocks.VOID_LANTERN_BLOCK.get(), new Item.Properties().tab(Cataclysm.TAB).fireResistant()));

    public static final RegistryObject<BlockItem> OBSIDIAN_BRICKS = ITEMS.register("obsidian_bricks",
            () -> new BlockItem(ModBlocks.OBSIDIAN_BRICKS.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> CHISELED_OBSIDIAN_BRICKS = ITEMS.register("chiseled_obsidian_bricks",
            () -> new BlockItem(ModBlocks.CHISELED_OBSIDIAN_BRICKS.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> OBSIDIAN_BRICK_SLAB = ITEMS.register("obsidian_brick_slab",
            () -> new BlockItem(ModBlocks.OBSIDIAN_BRICK_SLAB.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> OBSIDIAN_BRICK_STAIRS = ITEMS.register("obsidian_brick_stairs",
            () -> new BlockItem(ModBlocks.OBSIDIAN_BRICK_STAIRS.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> OBSIDIAN_BRICK_WALL = ITEMS.register("obsidian_brick_wall",
            () -> new BlockItem(ModBlocks.OBSIDIAN_BRICK_WALL.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> CHISELED_PURPUR_BLOCK = ITEMS.register("chiseled_purpur_block",
            () -> new BlockItem(ModBlocks.CHISELED_PURPUR_BLOCK.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> PURPUR_WALL = ITEMS.register("purpur_wall",
            () -> new BlockItem(ModBlocks.PURPUR_WALL.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> PURPUR_VOID_RUNE_TRAP_BLOCK = ITEMS.register("purpur_void_rune_trap_block",
            () -> new BlockItem(ModBlocks.PURPUR_VOID_RUNE_TRAP_BLOCK.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> END_STONE_TELEPORT_TRAP_BRICKS = ITEMS.register("end_stone_teleport_trap_bricks",
            () -> new BlockItem(ModBlocks.END_STONE_TELEPORT_TRAP_BRICKS.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> OBSIDIAN_EXPLOSION_TRAP_BRICKS = ITEMS.register("obsidian_explosion_trap_bricks",
            () -> new BlockItem(ModBlocks.OBSIDIAN_EXPLOSION_TRAP_BRICKS.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> SANDSTONE_POISON_DART_TRAP = ITEMS.register("sandstone_poison_dart_trap",
            () -> new BlockItem(ModBlocks.SANDSTONE_POISON_DART_TRAP.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> SANDSTONE_IGNITE_TRAP = ITEMS.register("sandstone_ignite_trap",
            () -> new BlockItem(ModBlocks.SANDSTONE_IGNITE_TRAP.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> SANDSTONE_FALLING_TRAP = ITEMS.register("sandstone_falling_trap",
            () -> new BlockItem(ModBlocks.SANDSTONE_FALLING_TRAP.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> CHORUS_STEM = ITEMS.register("chorus_stem",
            () -> new BlockItem(ModBlocks.CHORUS_STEM.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> CHORUS_PLANKS = ITEMS.register("chorus_planks",
            () -> new BlockItem(ModBlocks.CHORUS_PLANKS.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> CHORUS_SLAB = ITEMS.register("chorus_slab",
            () -> new BlockItem(ModBlocks.CHORUS_SLAB.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> CHORUS_STAIRS = ITEMS.register("chorus_stairs",
            () -> new BlockItem(ModBlocks.CHORUS_STAIRS.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> CHORUS_FENCE = ITEMS.register("chorus_fence",
            () -> new BlockItem(ModBlocks.CHORUS_FENCE.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> QUARTZ_BRICK_WALL = ITEMS.register("quartz_brick_wall",
            () -> new BlockItem(ModBlocks.QUARTZ_BRICK_WALL.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> STONE_PILLAR = ITEMS.register("stone_pillar",
            () -> new BlockItem(ModBlocks.STONE_PILLAR.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> CHISELED_STONE_BRICK_PILLAR = ITEMS.register("chiseled_stone_brick_pillar",
            () -> new BlockItem(ModBlocks.CHISELED_STONE_BRICK_PILLAR.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> STONE_TILES = ITEMS.register("stone_tiles",
            () -> new BlockItem(ModBlocks.STONE_TILES.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> STONE_TILE_SLAB = ITEMS.register("stone_tile_slab",
            () -> new BlockItem(ModBlocks.STONE_TILE_SLAB.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> STONE_TILE_STAIRS = ITEMS.register("stone_tile_stairs",
            () -> new BlockItem(ModBlocks.STONE_TILE_STAIRS.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<BlockItem> STONE_TILE_WALL = ITEMS.register("stone_tile_wall",
            () -> new BlockItem(ModBlocks.STONE_TILE_WALL.get(), new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<Item> WITHERITE_INGOT = ITEMS.register("witherite_ingot",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ENDERITE_INGOT = ITEMS.register("enderite_ingot",
            () -> new ItemInventoryOnly(new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> IGNITIUM_INGOT = ITEMS.register("ignitium_ingot",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> CHAIN_OF_SOUL_BINDING = ITEMS.register("chain_of_soul_binding",
            () -> new ItemInventoryOnly(new Item.Properties()));

    public static final RegistryObject<Item> CORAL_SPEAR = ITEMS.register("coral_spear",
            () -> new Coral_Spear((new Item.Properties().tab(Cataclysm.TAB)).durability(110)));

    public static final RegistryObject<Item> CORAL_BARDICHE = ITEMS.register("coral_bardiche",
            () -> new Coral_Bardiche((new Item.Properties().tab(Cataclysm.TAB)).durability(160)));

    public static final RegistryObject<Item> ATHAME = ITEMS.register("athame",
            () -> new Athame((new Item.Properties().tab(Cataclysm.TAB)).durability(250)));

    public static final RegistryObject<Item> KHOPESH = ITEMS.register("khopesh",
            () -> new Khopesh(Tiers.GOLD, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<Item> BULWARK_OF_THE_FLAME = ITEMS.register("bulwark_of_the_flame",
            () -> new Bulwark_of_the_flame(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> GAUNTLET_OF_GUARD = ITEMS.register("gauntlet_of_guard",
            () -> new Gauntlet_of_Guard(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> GAUNTLET_OF_BULWARK = ITEMS.register("gauntlet_of_bulwark",
            () -> new Gauntlet_of_Bulwark(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> THE_INCINERATOR = ITEMS.register("the_incinerator",
            () -> new The_Incinerator(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> MEAT_SHREDDER = ITEMS.register("meat_shredder",
            () -> new Meat_Shredder(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> LASER_GATLING = ITEMS.register("laser_gatling",
            () -> new Laser_Gatling(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).fireResistant().durability(50).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> WITHER_ASSULT_SHOULDER_WEAPON = ITEMS.register("wither_assault_shoulder_weapon",
            () -> new Wither_Assault_SHoulder_Weapon(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> VOID_ASSULT_SHOULDER_WEAPON = ITEMS.register("void_assault_shoulder_weapon",
            () -> new Void_Assault_SHoulder_Weapon(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> VOID_FORGE = ITEMS.register("void_forge",
            () -> new void_forge(Tiers.NETHERITE, new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> TIDAL_CLAWS = ITEMS.register("tidal_claws",
            () -> new Tidal_Claws(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> FINAL_FRACTAL = ITEMS.register("final_fractal",
            () -> new final_fractal(ModItemTier.TOOL_WITHERITE, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ZWEIENDER = ITEMS.register("zweiender",
            () -> new zweiender(ModItemTier.TOOL_ENDERITE, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> INFERNAL_FORGE = ITEMS.register("infernal_forge",
            () -> new infernal_forge(Tiers.NETHERITE, new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> SANDSTORM_IN_A_BOTTLE = ITEMS.register("sandstorm_in_a_bottle",
            () -> new Sandstorm_In_A_Bottle(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> REMNANT_SKULL = ITEMS.register("remnant_skull",
            () -> new Remnant_Skull(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> VOID_SCATTER_ARROW = ITEMS.register("void_scatter_arrow",
            () -> new ModItemArrow(new Item.Properties().tab(Cataclysm.TAB).fireResistant()));

    public static final RegistryObject<Item> VOID_SHARD = ITEMS.register("void_shard",
            () -> new ItemInventoryOnly(new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> BLAZING_BONE = ITEMS.register("blazing_bone",
            () -> new ItemInventoryOnly(new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> KOBOLETON_BONE = ITEMS.register("koboleton_bone",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<Item> VOID_JAW = ITEMS.register("void_jaw",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB).fireResistant()));

    public static final RegistryObject<Item> VOID_CORE = ITEMS.register("void_core",
            () -> new void_core(new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> CRYSTALLIZED_CORAL_FRAGMENTS = ITEMS.register("crystallized_coral_fragments",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<Item> CRYSTALLIZED_CORAL = ITEMS.register("crystallized_coral",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<Item> ABYSSAL_SACRIFICE = ITEMS.register("abyssal_sacrifice",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> NECKLACE_OF_THE_DESERT = ITEMS.register("necklace_of_the_desert",
            () -> new Necklace_Of_The_Desert(new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> IGNITIUM_HEAD = ITEMS.register("ignitium_helmet",
            () -> new Ignitium_Armor(Armortier.IGNITIUM, EquipmentSlot.HEAD, new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> IGNITIUM_CHESTPLATE = ITEMS.register("ignitium_chestplate",
            () -> new Ignitium_Armor(Armortier.IGNITIUM, EquipmentSlot.CHEST, new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> IGNITIUM_ELYTRA_CHESTPLATE = ITEMS.register("ignitium_elytra_chestplate",
            () -> new Ignitium_Elytra_ChestPlate(new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC), Armortier.IGNITIUM));

    public static final RegistryObject<Item> IGNITIUM_LEGGINGS = ITEMS.register("ignitium_leggings",
            () -> new Ignitium_Armor(Armortier.IGNITIUM, EquipmentSlot.LEGS, new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> IGNITIUM_BOOTS = ITEMS.register("ignitium_boots",
            () -> new Ignitium_Armor(Armortier.IGNITIUM, EquipmentSlot.FEET, new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MONSTROUS_HORN = ITEMS.register("monstrous_horn",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MONSTROUS_HELM = ITEMS.register("monstrous_helm",
            () -> new Monstrous_Helm(ArmorMaterials.NETHERITE, EquipmentSlot.HEAD, new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BLOOM_STONE_PAULDRONS = ITEMS.register("bloom_stone_pauldrons",
            () -> new Bloom_Stone_Pauldrons(Armortier.CRAB, EquipmentSlot.CHEST, new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BURNING_ASHES = ITEMS.register("burning_ashes",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB).fireResistant().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> MUSIC_DISC_NETHERITE_MONSTROSITY = ITEMS.register("music_disc_netherite_monstrosity",
            () -> new RecordItem(14, ModSounds.MONSTROSITY_MUSIC,new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant(), 79 * 20));

    public static final RegistryObject<Item> MUSIC_DISC_ENDER_GUARDIAN = ITEMS.register("music_disc_ender_guardian",
            () -> new RecordItem(14, ModSounds.ENDERGUARDIAN_MUSIC,new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant(), 148 * 20));

    public static final RegistryObject<Item> MUSIC_DISC_IGNIS = ITEMS.register("music_disc_ignis",
            () -> new RecordItem(14, ModSounds.IGNIS_MUSIC,new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant(), 128 * 20));

    public static final RegistryObject<Item> MUSIC_DISC_THE_HARBINGER = ITEMS.register("music_disc_the_harbinger",
            () -> new RecordItem(14, ModSounds.HARBINGER_MUSIC,new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant(), 207 * 20));

    public static final RegistryObject<Item> MUSIC_DISC_THE_LEVIATHAN = ITEMS.register("music_disc_the_leviathan",
            () -> new RecordItem(14, ModSounds.LEVIATHAN_MUSIC,new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant(), 200 * 20));

    public static final RegistryObject<Item> MUSIC_DISC_ANCIENT_REMNANT = ITEMS.register("music_disc_ancient_remnant",
            () -> new RecordItem(14, ModSounds.REMNANT_MUSIC,new Item.Properties().tab(Cataclysm.TAB).stacksTo(1).rarity(Rarity.EPIC).fireResistant(), 144 * 20));


    public static final RegistryObject<Item> MECH_EYE = ITEMS.register("mech_eye",
            () -> new MechEyeItem(new Item.Properties().tab(Cataclysm.TAB).fireResistant()));

    public static final RegistryObject<Item> FLAME_EYE = ITEMS.register("flame_eye",
            () -> new FlameEyeItem(new Item.Properties().tab(Cataclysm.TAB).fireResistant()));

    public static final RegistryObject<Item> VOID_EYE = ITEMS.register("void_eye",
            () -> new VoidEyeItem(new Item.Properties().tab(Cataclysm.TAB).fireResistant()));

    public static final RegistryObject<Item> MONSTROUS_EYE = ITEMS.register("monstrous_eye",
            () -> new MonstrousEyeItem(new Item.Properties().tab(Cataclysm.TAB).fireResistant()));

    public static final RegistryObject<Item> ABYSS_EYE = ITEMS.register("abyss_eye",
            () -> new AbyssEyeItem(new Item.Properties().tab(Cataclysm.TAB).fireResistant()));

    public static final RegistryObject<Item> DESERT_EYE = ITEMS.register("desert_eye",
            () -> new DesertEyeItem(new Item.Properties().tab(Cataclysm.TAB).fireResistant()));

    public static final RegistryObject<Item> LIONFISH = ITEMS.register("lionfish",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1F)
                    .effect(new MobEffectInstance(MobEffects.POISON, 300, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1.0F)
                    .build())));

    public static final RegistryObject<Item> AMETHYST_CRAB_MEAT = ITEMS.register("amethyst_crab_meat",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB).food(new FoodProperties.Builder().nutrition(6).saturationMod(1.2F)
                    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1.0F).build())));


    public static final RegistryObject<Item> BLESSED_AMETHYST_CRAB_MEAT = ITEMS.register("blessed_amethyst_crab_meat",
            () -> new Blessed_Amethyst_Crab_Meat(new Item.Properties().tab(Cataclysm.TAB).rarity(Rarity.EPIC).food(new FoodProperties.Builder().nutrition(6).saturationMod(1.2F)
                    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1.0F)
                    .effect(new MobEffectInstance(ModEffect.EFFECTBLESSING_OF_AMETHYST.get(), 1800, 0), 1.0F)
                    .alwaysEat()
                    .build())));

    public static final RegistryObject<Item> AMETHYST_CRAB_SHELL = ITEMS.register("amethyst_crab_shell",
            () -> new Item(new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<Item> LIONFISH_SPIKE = ITEMS.register("lionfish_spike",
            () -> new ItemInventoryOnly(new Item.Properties()));

    public static final RegistryObject<Item> THE_BABY_LEVIATHAN_BUCKET = ITEMS.register("the_baby_leviathan_bucket",
            () -> new ModFishBucket(ModEntities.THE_BABY_LEVIATHAN, Fluids.WATER, new Item.Properties().tab(Cataclysm.TAB).fireResistant()));

    public static final RegistryObject<SpawnEggItem> ENDER_GOLEM_SPAWN_EGG = ITEMS.register("ender_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ENDER_GOLEM, 0x2a1a42, 0xa153fe, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> NETHERITE_MONSTROSITY_SPAWN_EGG = ITEMS.register("netherite_monstrosity_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.NETHERITE_MONSTROSITY, 0x4d494d, 0xf48522, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> NAMELESS_SORCERER_SPAWN_EGG = ITEMS.register("nameless_sorcerer_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.NAMELESS_SORCERER, 9804699, 0xB92424, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> ENDER_GUARDIAN_SPAWN_EGG = ITEMS.register("ender_guardian_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ENDER_GUARDIAN, 0x2a1a42, 9725844, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> ENDERMAPTERA_SPAWN_EGG = ITEMS.register("endermaptera_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ENDERMAPTERA, 0x2a1a42, 7237230, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> IGNIS_SPAWN_EGG = ITEMS.register("ignis_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.IGNIS, 16167425, 16579584, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> IGNITED_REVENANT_SPAWN_EGG = ITEMS.register("ignited_revenant_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.IGNITED_REVENANT, 4672845, 16579584, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> THE_WATCHER_SPAWN_EGG = ITEMS.register("the_watcher_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.THE_WATCHER, 0x737c8b, 0xe83b3b, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> THE_PROWLER_SPAWN_EGG = ITEMS.register("the_prowler_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.THE_PROWLER, 0x1e2021, 0x682e22, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> THE_HARBINGER_SPAWN_EGG = ITEMS.register("the_harbinger_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.THE_HARBINGER, 0x1e2021, 0xae2334, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> THE_LEVIATHAN_SPAWN_EGG = ITEMS.register("the_leviathan_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.THE_LEVIATHAN, 0x150e1b, 0x6500ff, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> THE_BABY_LEVIATHAN_SPAWN_EGG = ITEMS.register("the_baby_leviathan_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.THE_BABY_LEVIATHAN, 0x322141, 0x8a3eff, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> DEEPLING_SPAWN_EGG = ITEMS.register("deepling_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DEEPLING, 0x182a3c, 0xbaedf4, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> DEEPLING_BRUTE_SPAWN_EGG = ITEMS.register("deepling_brute_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DEEPLING_BRUTE, 0x182a3c, 0x6500ff, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> DEEPLING_ANGLER_SPAWN_EGG = ITEMS.register("deepling_angler_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DEEPLING_ANGLER, 0x182a3c, 0x98d8e2, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> DEEPLING_PRIEST_SPAWN_EGG = ITEMS.register("deepling_priest_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DEEPLING_PRIEST, 0x182a3c, 0x082054, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> DEEPLING_WARLOCK_SPAWN_EGG = ITEMS.register("deepling_warlock_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DEEPLING_WARLOCK, 0x182a3c, 0xd66a98, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> LIONFISH_SPAWN_EGG = ITEMS.register("lionfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.LIONFISH, 0x98d8e2, 0x182a3c, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> CORALSSUS_SPAWN_EGG = ITEMS.register("coralssus_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.CORALSSUS, 0x2143a4, 0xa4222f, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> AMETHYST_CRAB_SPAWN_EGG = ITEMS.register("amethyst_crab_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.AMETHYST_CRAB, 0x646464, 0x7a5bb5, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> KOBOLETON_SPAWN_EGG = ITEMS.register("koboleton_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.KOBOLETON, 0xb7b196, 0xe18103, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> ANCIENT_REMNANT_SPAWN_EGG = ITEMS.register("ancient_remnant_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ANCIENT_REMNANT, 0xb7b196, 0x682e22, new Item.Properties().tab(Cataclysm.TAB)));

    public static final RegistryObject<SpawnEggItem> MODERN_REMNANT_SPAWN_EGG = ITEMS.register("modern_remnant_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MODERN_REMNANT, 0xb7b196, 0xdbcca7, new Item.Properties().tab(Cataclysm.TAB)));

    public static void initDispenser(){
        DispenserBlock.registerBehavior(VOID_SCATTER_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
                Void_Scatter_Arrow_Entity entityarrow = new Void_Scatter_Arrow_Entity(ModEntities.VOID_SCATTER_ARROW.get(), position.x(), position.y(), position.z(), worldIn);
                return entityarrow;
            }
        });
        DispenseItemBehavior bucketDispenseBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource blockSource, ItemStack stack) {
                DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem)stack.getItem();
                BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
                Level level = blockSource.getLevel();
                if (dispensiblecontaineritem.emptyContents((Player)null, level, blockpos, (BlockHitResult)null)) {
                    dispensiblecontaineritem.checkExtraContent((Player)null, level, stack, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultDispenseItemBehavior.dispense(blockSource, stack);
                }
            }
        };
        DispenserBlock.registerBehavior(THE_BABY_LEVIATHAN_BUCKET.get(), bucketDispenseBehavior);
    }
}


