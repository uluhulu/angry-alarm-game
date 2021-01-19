package com.ulianasisoeva.angryalarm.model.game_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Duck {
    private float width;
    private float height;
    private float x;
    private float y;
    private double velocity;
    private Texture texture;
    private int lifes;

    private boolean isAlive;


    public Duck(float width, float height, float x, float y, double velocity, String imagePath, int lifes) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.texture = new Texture(imagePath);
        this.lifes = lifes;
        if (lifes >= 1) {
            isAlive = true;
        } else {
            isAlive = false;
        }
    }

    public void move() {
        x += velocity * Gdx.graphics.getDeltaTime()*2;

    }

    public void moveSin() {
        x += velocity * Gdx.graphics.getDeltaTime();
//        while (y!=400)
//            y += Math.sin(velocity * Gdx.graphics.getDeltaTime()) * 0.5;
//
//        while (y!= 300)
//            y -= Math.sin(velocity * Gdx.graphics.getDeltaTime()) * 0.5;


    y += Math.sin( x * Math.PI/180)*(2) + 1;
            //Math.sin(velocity * Gdx.graphics.getDeltaTime()) * 100;

// if (y >= 410)
//     if(y != 300)



        //if (y >= 500 && y > 350 )
           // y -= Math.sin(velocity * Gdx.graphics.getDeltaTime()) * 2;
    }
    public void moveDown() {
        y -= velocity * Gdx.graphics.getDeltaTime();
    }
    public void moveFaster() {
        x += velocity * Gdx.graphics.getDeltaTime()*3;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }


    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public boolean isAlive() {
        return lifes >= 1;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
