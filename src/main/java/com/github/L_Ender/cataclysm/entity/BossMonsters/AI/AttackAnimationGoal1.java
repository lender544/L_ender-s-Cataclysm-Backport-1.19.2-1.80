package com.github.L_Ender.cataclysm.entity.BossMonsters.AI;

import java.util.EnumSet;

import com.github.L_Ender.cataclysm.entity.AnimationMonster.Animation_Monster;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;

import net.minecraft.world.entity.LivingEntity;

public class AttackAnimationGoal1<T extends Animation_Monster & IAnimatedEntity> extends SimpleAnimationGoal<T> {
    private final int look1;
    private final boolean see;
    public AttackAnimationGoal1(T entity, Animation animation, int look1, boolean see) {
        super(entity, animation);
        this.look1 = look1;
        this.see = see;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
    }
    public void tick() {
        LivingEntity target = entity.getTarget();
        if(see) {
            if (entity.getAnimationTick() < look1 && target != null) {
                entity.getLookControl().setLookAt(target, 30.0F, 30.0F);
            } else {
                entity.setYRot(entity.yRotO);
            }
        }else{
            if (entity.getAnimationTick() > look1 && target != null) {
                entity.getLookControl().setLookAt(target, 30.0F, 30.0F);
            } else {
                entity.setYRot(entity.yRotO);
            }
        }
        entity.setDeltaMovement(0, entity.getDeltaMovement().y, 0);
    }
}
