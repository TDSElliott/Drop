/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tylere.drop;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.ServiceType;
import com.almasb.fxgl.asset.AssetLoader;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.CollidableComponent;

/**
 *
 * @author Shane
 */
public class EntityFactory {
    
    public enum EntityType {
        DROPLET, BUCKET
    }
    
    private static AssetLoader assetLoader;
    
    static {
        assetLoader = FXGL.getService(ServiceType.ASSET_LOADER);
    }
    
    public static Entity newDroplet(double x, double y) {
        GameEntity droplet = new GameEntity();
        droplet.getTypeComponent().setValue(EntityType.DROPLET);
        droplet.getPositionComponent().setValue(x, y);
        droplet.getMainViewComponent().setView(new EntityView(assetLoader.loadTexture("droplet.png")), true);
        
        droplet.addComponent(new CollidableComponent(true));
        droplet.addControl(new DropControl());
        
        return droplet;
    }
    
    public static Entity newBucket(double x, double y) {
        GameEntity bucket = new GameEntity();
        bucket.getTypeComponent().setValue(EntityType.BUCKET);
        bucket.getPositionComponent().setValue(x, y);
        bucket.getMainViewComponent().setView(new EntityView(assetLoader.loadTexture("bucket.png")), true);
        
        bucket.addComponent(new CollidableComponent(true));
        bucket.addControl(new BucketControl());
        
        return bucket;
    }
}
