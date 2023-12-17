package com.github.L_Ender.cataclysm.client.event;

import java.util.Random;

import com.github.L_Ender.cataclysm.capabilities.Bloom_Stone_PauldronsCapability;
import com.github.L_Ender.cataclysm.capabilities.Gone_With_SandstormCapability;
import com.github.L_Ender.cataclysm.client.model.entity.Model_PlayerSandstorm;
import com.github.L_Ender.cataclysm.client.render.CMItemstackRenderer;
import com.github.L_Ender.cataclysm.client.render.CMRenderTypes;
import com.github.L_Ender.cataclysm.client.render.etc.LavaVisionFluidRenderer;
import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.init.ModCapabilities;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.alexthe666.citadel.client.event.EventGetFluidRenderType;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FogType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ClientEvent {
    public static final ResourceLocation FLAME_STRIKE = new ResourceLocation("cataclysm:textures/entity/soul_flame_strike_sigil.png");
    private boolean previousLavaVision = false;
    private LiquidBlockRenderer previousFluidRenderer;
    private static final ResourceLocation SANDSTORM_ICON = new ResourceLocation("cataclysm:textures/gui/sandstorm_icons.png");
    private static final ResourceLocation EFFECT_HEART = new ResourceLocation("cataclysm:textures/gui/effect_heart.png");
    private static final ResourceLocation SANDSTORM_TEXTURE = new ResourceLocation("cataclysm:textures/entity/ancient_remnant/sandstorm.png");
    private static final Model_PlayerSandstorm SANDSTORM_MODEL = new Model_PlayerSandstorm();
    private final Random random = new Random();
    private int lastHealth;
    private int displayHealth;
    private long lastHealthTime;
    private long healthBlinkTime;

    @SubscribeEvent
    public void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        Player player = Minecraft.getInstance().player;
        float delta = Minecraft.getInstance().getFrameTime();
        float ticksExistedDelta = player.tickCount + delta;
        if (CMConfig.ScreenShake && !Minecraft.getInstance().isPaused()) {
            if (player != null) {
                float shakeAmplitude = 0;
                for (ScreenShake_Entity ScreenShake : player.level.getEntitiesOfClass(ScreenShake_Entity.class, player.getBoundingBox().inflate(20, 20, 20))) {
                    if (ScreenShake.distanceTo(player) < ScreenShake.getRadius()) {
                        shakeAmplitude += ScreenShake.getShakeAmount(player, delta);
                    }
                }
                if (shakeAmplitude > 1.0f) shakeAmplitude = 1.0f;
                event.setPitch((float) (event.getPitch() + shakeAmplitude * Math.cos(ticksExistedDelta * 3 + 2) * 25));
                event.setYaw((float) (event.getYaw() + shakeAmplitude * Math.cos(ticksExistedDelta * 5 + 1) * 25));
                event.setRoll((float) (event.getRoll() + shakeAmplitude * Math.cos(ticksExistedDelta * 4) * 25));
            }

            if (Minecraft.getInstance().player.getEffect(ModEffect.EFFECTSTUN.get()) != null) {
                MobEffectInstance effectinstance1 = Minecraft.getInstance().player.getEffect(ModEffect.EFFECTSTUN.get());
                float shakeAmplitude = (float) ((1 + effectinstance1.getAmplifier()) * 0.01);
                event.setPitch((float) (event.getPitch() + shakeAmplitude * Math.cos(ticksExistedDelta * 3 + 2) * 25));
                event.setYaw((float) (event.getYaw() + shakeAmplitude * Math.cos(ticksExistedDelta * 5 + 1) * 25));
                event.setRoll((float) (event.getRoll() + shakeAmplitude * Math.cos(ticksExistedDelta * 4) * 25));
            }
        }

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onFogDensity(ViewportEvent.RenderFog event) {
        FogType fogType = event.getCamera().getFluidInCamera();
        ItemStack itemstack = Minecraft.getInstance().player.getInventory().getArmor(3);
        if (itemstack.is(ModItems.IGNITIUM_HEAD.get()) && fogType == FogType.LAVA) {
            RenderSystem.setShaderFogStart(-8.0F);
            RenderSystem.setShaderFogEnd(50.0F);
        }

    }

    @SubscribeEvent
    public void MovementInput(MovementInputUpdateEvent event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.hasEffect(ModEffect.EFFECTCURSE_OF_DESERT.get())) {
                if (Minecraft.getInstance().options.keyDown.isDown()) {
                    event.getInput().forwardImpulse += 2F;
                }
                if (Minecraft.getInstance().options.keyLeft.isDown()) {
                    event.getInput().leftImpulse -= 2F;
                }
                if (Minecraft.getInstance().options.keyRight.isDown()) {
                    event.getInput().leftImpulse += 2F;
                }
                if (Minecraft.getInstance().options.keyUp.isDown()) {
                    event.getInput().forwardImpulse -= 2F;
                }
            }
        }
    }

    @SubscribeEvent
    public void onPreRenderHUD(RenderGuiOverlayEvent.Pre event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            Minecraft mc = Minecraft.getInstance();
            ForgeGui gui = (ForgeGui)mc.gui;
            if (event.getOverlay() == VanillaGuiOverlay.PLAYER_HEALTH.type() && !mc.options.hideGui && gui.shouldDrawSurvivalElements()) {
                if (player.hasEffect(ModEffect.EFFECTABYSSAL_BURN.get()) || player.hasEffect(ModEffect.EFFECTABYSSAL_CURSE.get())) {
                    CustomHealth(event, 25);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPostRenderHUD(RenderGuiOverlayEvent.Post event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            Minecraft mc = Minecraft.getInstance();
            ForgeGui gui = (ForgeGui)mc.gui;
            if (event.getOverlay() == VanillaGuiOverlay.AIR_LEVEL.type() && !mc.options.hideGui && gui.shouldDrawSurvivalElements()) {
                renderSandstormOverlay(event);
            }
        }
    }


    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onPreRenderEntity(RenderLivingEvent.Pre event) {
        LivingEntity player = (LivingEntity) event.getEntity();
        boolean usingIncinerator = player.isUsingItem() && player.getUseItem().is(ModItems.THE_INCINERATOR.get());
        Gone_With_SandstormCapability.IGone_With_SandstormCapability SandstormCapability = ModCapabilities.getCapability(player, ModCapabilities.GONE_WITH_SANDSTORM_CAPABILITY);
        if (SandstormCapability != null) {
            if(SandstormCapability.isSandstorm()){
                event.setCanceled(true);
                event.getPoseStack().pushPose();
                float limbSwing = event.getEntity().animationPosition - event.getEntity().animationSpeed * (1.0F - event.getPartialTick());
                float limbSwingAmount = event.getEntity().animationSpeedOld + (event.getEntity().animationSpeed - event.getEntity().animationSpeedOld) * event.getPackedLight();
                VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(event.getMultiBufferSource(), RenderType.armorCutoutNoCull(SANDSTORM_TEXTURE), false, event.getEntity().getItemBySlot(EquipmentSlot.CHEST).hasFoil());
                event.getPoseStack().translate(0.0D, event.getEntity().getBbHeight(), 0.0D);
                event.getPoseStack().mulPose(Vector3f.ZP.rotationDegrees(180.0F));
                SANDSTORM_MODEL.setupAnim(event.getEntity(), limbSwing, limbSwingAmount, event.getEntity().tickCount + event.getPartialTick(), 0, 0);
                SANDSTORM_MODEL.renderToBuffer(event.getPoseStack(), vertexconsumer, event.getPackedLight(), OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                event.getPoseStack().popPose();
                MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post(event.getEntity(), event.getRenderer(), event.getPartialTick(), event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight()));
                return;
            }
        }
        if(usingIncinerator){
            int i = player.getTicksUsingItem();
            float f2 = (float) player.tickCount + event.getPartialTick();
            PoseStack matrixStackIn = event.getPoseStack();
            float f3 = Mth.clamp(i, 1, 60);
            matrixStackIn.pushPose();
            VertexConsumer ivertexbuilder = ItemRenderer.getArmorFoilBuffer(event.getMultiBufferSource(),CMRenderTypes.getGlowingEffect(FLAME_STRIKE),false, true);
            matrixStackIn.translate(0.0D, 0.001, 0.0D);
            matrixStackIn.scale(f3 * 0.05f, f3 * 0.05f, f3 * 0.05f);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F + f2));
            PoseStack.Pose lvt_19_1_ = matrixStackIn.last();
            Matrix4f lvt_20_1_ = lvt_19_1_.pose();
            Matrix3f lvt_21_1_ = lvt_19_1_.normal();
            this.drawVertex(lvt_20_1_, lvt_21_1_, ivertexbuilder, -1, 0, -1, 0, 0, 1, 0, 1, 240);
            this.drawVertex(lvt_20_1_, lvt_21_1_, ivertexbuilder, -1, 0, 1, 0, 1, 1, 0, 1, 240);
            this.drawVertex(lvt_20_1_, lvt_21_1_, ivertexbuilder, 1, 0, 1, 1, 1, 1, 0, 1, 240);
            this.drawVertex(lvt_20_1_, lvt_21_1_, ivertexbuilder, 1, 0, -1, 1, 0, 1, 0, 1, 240);
            matrixStackIn.popPose();
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onPreRenderPlayer(RenderPlayerEvent.Pre event) {
        Player player = event.getEntity();
        Bloom_Stone_PauldronsCapability.IBloom_Stone_PauldronsCapability chargeCapability = ModCapabilities.getCapability(player, ModCapabilities.BLOOM_STONE_PAULDRONS_CAPABILITY_CAPABILITY);
        Gone_With_SandstormCapability.IGone_With_SandstormCapability SandstormCapability = ModCapabilities.getCapability(player, ModCapabilities.GONE_WITH_SANDSTORM_CAPABILITY);
        if (SandstormCapability != null) {
            if (SandstormCapability.isSandstorm()) {
                return;
            }
        }
        if (chargeCapability != null) {
            if(chargeCapability.isBurrow()) {
                event.getPoseStack().pushPose();
                event.getPoseStack().translate(0.0D, -event.getEntity().getBbHeight(), 0.0D);
            }
        }

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onPostRenderPlayer(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        Bloom_Stone_PauldronsCapability.IBloom_Stone_PauldronsCapability chargeCapability = ModCapabilities.getCapability(player, ModCapabilities.BLOOM_STONE_PAULDRONS_CAPABILITY_CAPABILITY);
        if (chargeCapability != null) {
            if(chargeCapability.isBurrow()) {
                event.getPoseStack().popPose();
            }
        }
    }

    public void drawVertex(Matrix4f p_229039_1_, Matrix3f p_229039_2_, VertexConsumer p_229039_3_, int p_229039_4_, int p_229039_5_, int p_229039_6_, float p_229039_7_, float p_229039_8_, int p_229039_9_, int p_229039_10_, int p_229039_11_, int p_229039_12_) {
        p_229039_3_.vertex(p_229039_1_, (float) p_229039_4_, (float) p_229039_5_, (float) p_229039_6_).color(255, 255, 255, 255).uv(p_229039_7_, p_229039_8_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_229039_12_).normal(p_229039_2_, (float) p_229039_9_, (float) p_229039_11_, (float) p_229039_10_).endVertex();
    }

    @SubscribeEvent
    public void clientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            CMItemstackRenderer.incrementTick();
        }

    }

    private void updateAllChunks() {
        if (Minecraft.getInstance().levelRenderer.viewArea != null) {
            int length = Minecraft.getInstance().levelRenderer.viewArea.chunks.length;
            for (int i = 0; i < length; i++) {
                Minecraft.getInstance().levelRenderer.viewArea.chunks[i].dirty = true;
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onRenderWorldLastEvent(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            if (!CMConfig.shadersCompat) {
                ItemStack itemstack = Minecraft.getInstance().player.getInventory().getArmor(3);
                if (itemstack.is(ModItems.IGNITIUM_HEAD.get())) {
                    if (!previousLavaVision) {
                        previousFluidRenderer = Minecraft.getInstance().getBlockRenderer().liquidBlockRenderer;
                        Minecraft.getInstance().getBlockRenderer().liquidBlockRenderer = new LavaVisionFluidRenderer();
                        updateAllChunks();
                    }
                } else {
                    if (previousLavaVision) {
                        if (previousFluidRenderer != null) {
                            Minecraft.getInstance().getBlockRenderer().liquidBlockRenderer = previousFluidRenderer;
                        }
                        updateAllChunks();
                    }
                }
                previousLavaVision = itemstack.is(ModItems.IGNITIUM_HEAD.get());
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onGetFluidRenderType(EventGetFluidRenderType event) {
        if (Minecraft.getInstance().player.getInventory().getArmor(3).is(ModItems.IGNITIUM_HEAD.get()) && (event.getFluidState().is(Fluids.LAVA) || event.getFluidState().is(Fluids.FLOWING_LAVA))) {
            event.setRenderType(RenderType.translucent());
            event.setResult(Event.Result.ALLOW);
        }
    }

    private void CustomHealth(RenderGuiOverlayEvent.Pre event,int back){
        Player player = Minecraft.getInstance().player;
        Minecraft mc = Minecraft.getInstance();
        ForgeGui gui = (ForgeGui)mc.gui;
        PoseStack stack = event.getPoseStack();
        gui.setupOverlayRenderState(true, false);
        int width = event.getWindow().getGuiScaledWidth();
        int height = event.getWindow().getGuiScaledHeight();
        event.setCanceled(true);
        RenderSystem.setShaderTexture(0, EFFECT_HEART);
        RenderSystem.enableBlend();
        int health = Mth.ceil(player.getHealth());
        int tickCount = gui.getGuiTicks();
        boolean highlight = this.healthBlinkTime > (long) tickCount && (this.healthBlinkTime - (long) tickCount) / 3L % 2L == 1L;
        if (health < this.lastHealth && player.invulnerableTime > 0) {
            this.lastHealthTime = Util.getMillis();
            this.healthBlinkTime = (long) (tickCount + 20);
        } else if (health > this.lastHealth && player.invulnerableTime > 0) {
            this.lastHealthTime = Util.getMillis();
            this.healthBlinkTime = (long) (tickCount + 10);
        }

        if (Util.getMillis() - this.lastHealthTime > 1000L) {
            this.lastHealth = health;
            this.displayHealth = health;
            this.lastHealthTime = Util.getMillis();
        }

        this.lastHealth = health;
        int healthLast = this.displayHealth;
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        float healthMax = (float) maxHealth.getValue();
        int absorbtion = Mth.ceil(player.getAbsorptionAmount());
        int healthRows = Mth.ceil((healthMax + (float) absorbtion) / 2.0F / 10.0F);
        int rowHeight = Math.max(10 - (healthRows - 2), 3);
        this.random.setSeed((long) (tickCount * 312871L));
        int left = width / 2 - 91;
        int top = height - gui.leftHeight;
        gui.leftHeight += healthRows * rowHeight;
        if (rowHeight != 10) {
            gui.leftHeight += 10 - rowHeight;
        }

        int regen = -1;
        if (player.hasEffect(MobEffects.REGENERATION)) {
            regen = tickCount % Mth.ceil(healthMax + 5.0F);
        }

        int TOP = player.level.getLevelData().isHardcore() ? 9 : 0;
        int BACKGROUND = highlight ? back : 16;
        int margin = 34;
        float absorbtionRemaining = (float) absorbtion;

        for (int i = Mth.ceil((healthMax + (float) absorbtion) / 2.0F) - 1; i >= 0; --i) {
            int row = Mth.ceil((float) (i + 1) / 10.0F) - 1;
            int x = left + i % 10 * 8;
            int y = top - row * rowHeight;
            if (health <= 4) {
                y += this.random.nextInt(2);
            }

            if (i == regen) {
                y -= 2;
            }
            
            gui.blit(stack, x, y, BACKGROUND, TOP, 9, 9);
            if (highlight) {
                if (i * 2 + 1 < healthLast) {
                	gui.blit(stack, x, y, margin, TOP, 9, 9);
                } else if (i * 2 + 1 == healthLast) {
                	gui.blit(stack, x, y, margin + 9, TOP, 9, 9);
                }
            }

            if (absorbtionRemaining > 0.0F) {
                if (absorbtionRemaining == (float) absorbtion && (float) absorbtion % 2.0F == 1.0F) {
                	gui.blit(stack, x, y, margin + 9, TOP, 9, 9);
                    --absorbtionRemaining;
                } else {
                	gui.blit(stack, x, y, margin, TOP, 9, 9);
                    absorbtionRemaining -= 2.0F;
                }
            } else if (i * 2 + 1 < health) {
            	gui.blit(stack, x, y, margin, TOP, 9, 9);
            } else if (i * 2 + 1 == health) {
            	gui.blit(stack, x, y, margin + 9, TOP, 9, 9);
            }
        }

        RenderSystem.disableBlend();
        RenderSystem.setShaderTexture(0, EFFECT_HEART);
    }

    private void renderSandstormOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        Minecraft mc = Minecraft.getInstance();
        ForgeGui gui = (ForgeGui) mc.gui;
        PoseStack stack = event.getPoseStack();
        gui.setupOverlayRenderState(true, false);
        int width = event.getWindow().getGuiScaledWidth();
        int height = event.getWindow().getGuiScaledHeight();
        if ((minecraft.getCameraEntity() instanceof LivingEntity player)) {
            Gone_With_SandstormCapability.IGone_With_SandstormCapability SandstormCapability = ModCapabilities.getCapability(player, ModCapabilities.GONE_WITH_SANDSTORM_CAPABILITY);
            if (SandstormCapability != null) {
                int left = width / 2 + 91;
                int top = height - gui.rightHeight;

                int flytime = Math.abs(SandstormCapability.getSandstormTimer());
                int maxProgressTime = CMConfig.Sandstorm_In_A_Bottle_Timer;

                if (flytime == 0) {
                    return;
                }

                float progress = 1 - flytime / (float) maxProgressTime;

                int full = Mth.ceil((progress - 2D / maxProgressTime) * 10);
                int partial = Mth.ceil(progress * 10) - full;

                for (int i = 0; i < full + partial; ++i) {
                	RenderSystem.setShaderTexture(0, SANDSTORM_ICON);
                    GuiComponent.blit(stack, left - i * 8 - 9, top, -90, (i < full ? 0 : 9), 0, 9, 9, 32, 16);
                }
                gui.rightHeight += 10;

                RenderSystem.disableBlend();
            }
        }
    }
}
