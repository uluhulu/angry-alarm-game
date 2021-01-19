package com.ulianasisoeva.angryalarm.model.game_objects;

import com.badlogic.gdx.graphics.Texture;

public class GunSight {
    private float width;
    private float height;
    private float x;
    private float y;
    private Texture texture;

    public GunSight(float width, float height, float x, float y,String imagePath) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.texture = new Texture(imagePath);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
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
}
