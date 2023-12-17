package com.github.L_Ender.cataclysm.entity.projectile;

import java.util.UUID;

import javax.annotation.Nullable;

import com.github.L_Ender.cataclysm.config.CMConfig;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class Ancient_Desert_Stele_Entity extends Projectile {
    private int warmupDelayTicks;
    private boolean sentSpikeEvent;
    private int lifeTicks = 70;
    private LivingEntity caster;
    private UUID casterUuid;
    private static final EntityDataAccessor<Boolean> ACTIVATE = SynchedEntityData.defineId(Ancient_Desert_Stele_Entity.class, EntityDataSerializers.BOOLEAN);

    public Ancient_Desert_Stele_Entity(EntityType<? extends Ancient_Desert_Stele_Entity> p_i50170_1_, Level p_i50170_2_) {
        super(p_i50170_1_, p_i50170_2_);
    }

    public Ancient_Desert_Stele_Entity(Level worldIn, double x, double y, double z, float p_i47276_8_, int p_i47276_9_, LivingEntity casterIn) {
        this(ModEntities.ANCIENT_DESERT_STELE.get(), worldIn);
        this.warmupDelayTicks = p_i47276_9_;
        this.setCaster(casterIn);
        this.setYRot(p_i47276_8_ * (180F / (float)Math.PI));
        this.setPos(x, y, z);
    }

    protected void defineSynchedData() {
        this.entityData.define(ACTIVATE, Boolean.valueOf(false));
    }

    public void setCaster(@Nullable LivingEntity p_190549_1_) {
        this.caster = p_190549_1_;
        this.casterUuid = p_190549_1_ == null ? null : p_190549_1_.getUUID();
    }

    @Nullable
    public LivingEntity getCaster() {
        if (this.caster == null && this.casterUuid != null && this.level instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level).getEntity(this.casterUuid);
            if (entity instanceof LivingEntity) {
                this.caster = (LivingEntity)entity;
            }
        }

        return this.caster;
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.warmupDelayTicks = compound.getInt("Warmup");
        if (compound.hasUUID("Owner")) {
            this.casterUuid = compound.getUUID("Owner");
        }

    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("Warmup", this.warmupDelayTicks);
        if (this.casterUuid != null) {
            compound.putUUID("Owner", this.casterUuid);
        }

    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();

        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        boolean flag = false;
        if (hitresult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult)hitresult).getBlockPos();
            BlockState blockstate = this.level.getBlockState(blockpos);
            if (blockstate.is(Blocks.NETHER_PORTAL)) {
                this.handleInsidePortal(blockpos);
                flag = true;
            } else if (blockstate.is(Blocks.END_GATEWAY)) {
                BlockEntity blockentity = this.level.getBlockEntity(blockpos);
                if (blockentity instanceof TheEndGatewayBlockEntity && TheEndGatewayBlockEntity.canEntityTeleport(this)) {
                    TheEndGatewayBlockEntity.teleportEntity(this.level, blockpos, blockstate, this, (TheEndGatewayBlockEntity)blockentity);
                }

                flag = true;
            }
        }

        if (hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }

        this.checkInsideBlocks();

        if (this.level.isClientSide) {
            --this.lifeTicks;

        } else if (--this.warmupDelayTicks < 0) {
            if(!this.isActivate()) {
                this.setActivate(true);
            }
            if (--this.lifeTicks < 0) {
                this.discard();
            }
        }

        Vec3 vec3 = this.getDeltaMovement();
        double d2 = this.getX() + vec3.x;
        double d0 = this.getY() + vec3.y;
        double d1 = this.getZ() + vec3.z;
        if (this.isActivate()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
            Vec3 vec31 = this.getDeltaMovement();
            this.setDeltaMovement(vec31.x, vec31.y - 0.03, vec31.z);
        }
        this.setPos(d2, d0, d1);

    }

    protected void onHit(HitResult ray) {
        super.onHit(ray);
        BlockState state = Blocks.SANDSTONE.defaultBlockState();
        SoundType soundtype = state.getSoundType(this.level, this.blockPosition(), null);
        this.playSound(soundtype.getBreakSound(), (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        if (!this.level.isClientSide) {
            ((ServerLevel) this.level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), this.getX(), this.getY() + this.getBbHeight() / 2.0, this.getZ(), 256, this.getBbWidth() / 2.0, this.getBbHeight() / 2.0, this.getBbWidth() / 2.0, 1);
            this.discard();
        }

    }

    protected void onHitEntity(EntityHitResult p_213868_1_) {
        LivingEntity shooter = this.getCaster();

        Entity entity = p_213868_1_.getEntity();
        boolean flag = false;
        if (shooter != null) {
            LivingEntity owner = shooter;
            if (owner != entity) {
                flag = entity.hurt(DamageSource.indirectMobAttack(this, owner), (float) CMConfig.AncientDesertSteledamage);
                if (flag) {
                    this.doEnchantDamageEffects(owner, entity);
                }
            }
        } else {
            flag = entity.hurt(DamageSource.MAGIC, (float) CMConfig.AncientDesertSteledamage);
        }
        if (flag && entity instanceof LivingEntity) {
            MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTCURSE_OF_DESERT.get(), 200, 0);
            ((LivingEntity)entity).addEffect(effectinstance);
        }

    }

    protected void onHitBlock(BlockHitResult p_230299_1_) {
    }

    public boolean isActivate() {
        return this.entityData.get(ACTIVATE);
    }

    public void setActivate(boolean Activate) {
        this.entityData.set(ACTIVATE, Activate);
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
