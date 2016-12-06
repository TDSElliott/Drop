/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tylere.drop;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

/**
 *
 * @author Shane
 */
public class Drop extends GameApplication {
    
    private BucketControl bucketControl;

    @Override
    protected void initSettings(GameSettings gs) {
        gs.setWidth(480);
        gs.setHeight(800);
        gs.setCloseConfirmation(false);
        gs.setIntroEnabled(false);
        gs.setMenuEnabled(false);
        gs.setProfilingEnabled(false);
    }
    
    @Override
    protected void initInput() {
        Input input = getInput();
        
        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                bucketControl.left();
            }
        }, KeyCode.A);
        
        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                bucketControl.right();
            }
        }, KeyCode.D);
    }
    
    @Override
    protected void initAssets() {
        
    }

    @Override
    protected void initGame() {
        Entity bucket = EntityFactory.newBucket(getWidth() / 2, getHeight() - 200);
        
        bucketControl = bucket.getControlUnsafe(BucketControl.class);
        
        getGameWorld().addEntity(bucket);
        
        getMasterTimer().runAtInterval(() -> {
            Entity droplet = EntityFactory.newDroplet(Math.random() * getWidth() - 64, 0);
            
            getGameWorld().addEntity(droplet);
        }, Duration.seconds(1));
    }

    @Override
    protected void initPhysics() {
       PhysicsWorld physicsWorld = getPhysicsWorld();
       
       physicsWorld.addCollisionHandler(new CollisionHandler(EntityFactory.EntityType.DROPLET, EntityFactory.EntityType.BUCKET) {
           @Override
           protected void onCollisionBegin(Entity droplet, Entity bucket) {
               droplet.removeFromWorld();
               
               System.out.println("Caught droplet");
           }
           
       });
    }

    @Override
    protected void initUI() {
       
    }

    @Override
    protected void onUpdate(double d) {
       
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
