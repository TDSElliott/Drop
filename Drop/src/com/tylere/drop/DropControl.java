/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tylere.drop;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.ents.component.Required;
import com.almasb.fxgl.entity.component.PositionComponent;

/**
 *
 * @author Shane
 */
@Required(PositionComponent.class)
public class DropControl extends AbstractControl {
    
        
    private PositionComponent position;
    
    private double velocity;

    @Override
    public void onAdded(Entity entity) {
        position = entity.getComponentUnsafe(PositionComponent.class);
    }

    @Override
    public void onUpdate(Entity entity, double d) {
        velocity = d * 200;
        
        position.translateY(velocity);
    }
    
}
