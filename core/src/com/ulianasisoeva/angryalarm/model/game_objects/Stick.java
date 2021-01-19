package com.ulianasisoeva.angryalarm.model.game_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Stick {
    private float width;
    private float height;
    private float x;
    private float y;
    private double velocity;
    private Texture texture;



    public Stick(float width, float height, float x, float y, double velocity, String imagePath) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.texture = new Texture(imagePath);

    }

    public void move() {
        x += velocity * Gdx.graphics.getDeltaTime();
    }
    public void moveFaster() {
        x += velocity * Gdx.graphics.getDeltaTime()*2;
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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
