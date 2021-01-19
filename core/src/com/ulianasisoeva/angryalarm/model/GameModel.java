package com.ulianasisoeva.angryalarm.model;

import com.ulianasisoeva.angryalarm.model.game_objects.Cloud;
import com.ulianasisoeva.angryalarm.model.game_objects.Decoration;
import com.ulianasisoeva.angryalarm.model.game_objects.Duck;
import com.ulianasisoeva.angryalarm.model.game_objects.Grass;
import com.ulianasisoeva.angryalarm.model.game_objects.GunSight;
import com.ulianasisoeva.angryalarm.model.game_objects.Stick;
import com.ulianasisoeva.angryalarm.model.game_objects.Tree;
import com.ulianasisoeva.angryalarm.model.game_objects.Water;
import com.ulianasisoeva.angryalarm.model.game_objects.WoodBackground;
import com.ulianasisoeva.angryalarm.model.helper.EndGameCallback;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {
    final EndGameCallback endGameCallback;

    public static int SCREEN_WIDTH = 480;
    public static int SCREEN_HEIGHT = 800;
    public int scores = 0;
    public String scoreName = "score: 0";
    public String timerName = "time: 30";
    Integer worldTimer = 31;
    float timeCount = 0;
    private boolean timeUp; // true when the world timer reaches 0
    public ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Duck> ducksLeftList = new ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Duck>();
    public ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Duck> ducksRightList = new ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Duck>();
    public ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Duck> ducksFromGround1 = new ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Duck>();
    public ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Stick> ducksLeftSticks = new ArrayList<Stick>();
    public com.ulianasisoeva.angryalarm.model.game_objects.GunSight gunSight = new GunSight(40,40,200,10,"crosshair_outline_large.png");
    public ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Water> waves1 = new ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Water>();
    public ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Water> waves2 = new ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Water>();
    public com.ulianasisoeva.angryalarm.model.game_objects.Water water1 = new com.ulianasisoeva.angryalarm.model.game_objects.Water(132, 224,0,60,"water1.png");
    public com.ulianasisoeva.angryalarm.model.game_objects.Water water2 = new com.ulianasisoeva.angryalarm.model.game_objects.Water(132, 224,0,170,"water1.png");
    public ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Grass> grassField1 = new ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Grass>();
    public com.ulianasisoeva.angryalarm.model.game_objects.Grass grass1 = new com.ulianasisoeva.angryalarm.model.game_objects.Grass(132, 200,0,-80,"grass1.png");
    public ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Grass> grassField2 = new ArrayList<com.ulianasisoeva.angryalarm.model.game_objects.Grass>();
    public com.ulianasisoeva.angryalarm.model.game_objects.Grass grass2 = new com.ulianasisoeva.angryalarm.model.game_objects.Grass(132, 200,0,230,"grass2.png");
    public com.ulianasisoeva.angryalarm.model.game_objects.Tree treeOak ;
    public com.ulianasisoeva.angryalarm.model.game_objects.Tree treePine;
    public com.ulianasisoeva.angryalarm.model.game_objects.Cloud cloud1;
    public com.ulianasisoeva.angryalarm.model.game_objects.Cloud cloud2;
    public com.ulianasisoeva.angryalarm.model.game_objects.Decoration decoration;

    public com.ulianasisoeva.angryalarm.model.game_objects.WoodBackground woodBackground = new WoodBackground(SCREEN_WIDTH,SCREEN_HEIGHT,0,0,"bg_wood.png");

    public GameModel(EndGameCallback endGameCallback) {
        this.endGameCallback = endGameCallback;
        treeOak = new com.ulianasisoeva.angryalarm.model.game_objects.Tree(119,225, 60, 390,"tree_oak.png");
        treePine = new Tree(119,225, 300, 390,"tree_pine.png");
        cloud1 = new com.ulianasisoeva.angryalarm.model.game_objects.Cloud(134,82,100,560,"cloud1.png");
        cloud2 = new Cloud(141,84,280,600,"cloud1.png");
        decoration = new Decoration(480, 100,0,710,"curtain_straight.png");
        generateLeftDucks();
        generateRightDucks();
        generateWaves2();
        generateWaves1();
        generateGrass1();
        generateGrass2();
        generateDucksFromGround();
    }

    public void generateWaves1() {
        waves1.add(water1);
        float x = 0;
        for (int i = 2; i <= 4; i++){
            if (i % 2 == 0) {
                x += 132;
                waves1.add(new com.ulianasisoeva.angryalarm.model.game_objects.Water(132, 224, x, 60, "water2.png"));
            }
            if (i % 2 != 0) {
                x += 132;
                waves1.add(new com.ulianasisoeva.angryalarm.model.game_objects.Water(132, 224, x, 60, "water1.png"));
            }
        }
    }
    public void generateGrass1() {
        grassField1.add(grass1);
        float x = 0;
        for (int i = 2; i <= 4; i++){
            if (i % 2 == 0) {
                x += 132;
                grassField1.add(new com.ulianasisoeva.angryalarm.model.game_objects.Grass(132, 218, x, -80, "grass2.png"));
            }
            if (i % 2 != 0) {
                x += 132;
                grassField1.add(new com.ulianasisoeva.angryalarm.model.game_objects.Grass(132, 200, x, -80, "grass1.png"));
            }
        }
    }
    public void generateDucksFromGround(){
        ducksFromGround1.add(new com.ulianasisoeva.angryalarm.model.game_objects.Duck(57,118, -50,350,50,"duck_outline_target_brown_with_stick.png",1));
    }
    public void generateGrass2() {
        grassField2.add(grass2);
        float x = 0;
        for (int i = 2; i <= 4; i++){
            if (i % 2 == 0) {
                x += 132;
                grassField2.add(new com.ulianasisoeva.angryalarm.model.game_objects.Grass(132, 218, x, 230, "grass2.png"));
            }
            if (i % 2 != 0) {
                x += 132;
                grassField2.add(new Grass(132, 200, x, 230, "grass1.png"));
            }
        }
    }
    public void generateWaves2() {
        waves2.add(water2);
        float x = 0;
        for (int i = 2; i <= 4; i++){
            if (i % 2 == 0) {
                x += 132;
                waves2.add(new com.ulianasisoeva.angryalarm.model.game_objects.Water(132, 224, x, 170, "water2.png"));
            }
            if (i % 2 != 0) {
                x += 132;
                waves2.add(new Water(132, 224, x, 170, "water1.png"));
            }
        }
    }

    public void generateLeftDucks() {
        Random random = new Random();
        int j = 1 + (int)(Math.random() * ((4-1)+1));
        for (int i = 0; i < j; i++) {
            float y = (float) (10 + Math.random() * ((90-10)));
            float p = (float) (200 + Math.random() * ((600-200)));
            int x = 80 ;
            if (i%2 == 0)
              ducksLeftList.add(new com.ulianasisoeva.angryalarm.model.game_objects.Duck(80, 180, 0-p , y, 50, "duck_brown_with_stick.png", 1));
            if (i%2 != 0)
                ducksLeftList.add(new com.ulianasisoeva.angryalarm.model.game_objects.Duck(80, 180, 0-p, y, 50, "duck_yellow _with_stick.png", 1));


        }
    }

    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            if (worldTimer > 0) {
                worldTimer--;
            } else {
                timeUp = true;
                checkResult();
            }
            timeCount = 0;
            timerName = "time: " + worldTimer;
        }
    }

    public void checkResult ()
    {
        if (timeUp){
            if (scores < 20){
                worldTimer = 30;
                scores = 0;
                getScores(scores);
                ducksLeftList.clear();
                ducksRightList.clear();
                generateLeftDucks();
                generateRightDucks();
            }
            else {
                endGameCallback.returnFromGame();
            }
        }
    }

    public void generateRightDucks() {
        Random random = new Random();


        int k = 1 + (int)(Math.random() * ((4-1)+1));
        for (int i = 0; i < k; i++) {
            float y = (float) (190 + Math.random() * ((240-190)));
            float p = (float) (200 + Math.random() * ((450-200)));
            if (i%2 == 0)
                ducksRightList.add(new com.ulianasisoeva.angryalarm.model.game_objects.Duck(70, 140, SCREEN_WIDTH+p , y, -50, "duck_outline_brown_with_stick.png", 1));
            if (i%2 != 0)
                ducksRightList.add(new com.ulianasisoeva.angryalarm.model.game_objects.Duck(70, 140, SCREEN_WIDTH +p, y, -50, "duck_outline_yellow_with_stick.png", 1));


        }


    }


    public void moveDucks() {
        for (int i = 0; i< ducksLeftList.size();i++) {
            com.ulianasisoeva.angryalarm.model.game_objects.Duck duck = ducksLeftList.get(i);
            if (duck.getX() > SCREEN_WIDTH + duck.getWidth()) {
                ducksLeftList.remove(duck);
                if (ducksLeftList.isEmpty()){
                    generateLeftDucks();
                }
            }
            duck.move();

        }
        for (int i = 0; i< ducksRightList.size();i++)  {
            com.ulianasisoeva.angryalarm.model.game_objects.Duck duck = ducksRightList.get(i);
            if (duck.getX() < 0 - duck.getWidth()) {
                ducksRightList.remove(duck);
                if (ducksRightList.isEmpty()){
                    generateRightDucks();
                }
            }
            duck.moveFaster();
        }
        for (int i = 0; i< ducksFromGround1.size();i++) {
            com.ulianasisoeva.angryalarm.model.game_objects.Duck duck = ducksFromGround1.get(i);
            if (duck.getX() > SCREEN_WIDTH + duck.getWidth()) {
                ducksFromGround1.remove(duck);
                if (ducksFromGround1.isEmpty()) {
                    generateLeftDucks();
                }
            }
            duck.moveSin();
        }

    }



    public void getScores (int scores)
    {
        scoreName = "score: " + scores;
        System.out.println(this.scores);
    }

    public void shotDuck(float touchX, float touchY) {
        for (int i = 0; i < ducksLeftList.size(); i++) {
            com.ulianasisoeva.angryalarm.model.game_objects.Duck duck = ducksLeftList.get(i);
            if (checkHit(duck, touchX, touchY)) {
                duck.setLifes(duck.getLifes() - 1);
                if (!duck.isAlive()) {
                    ducksLeftList.remove(duck);
                    getScores (++scores);
                    if (ducksLeftList.isEmpty()){
                        generateLeftDucks();
                    }
                }
            }
        }
        for (int i = 0; i < ducksRightList.size(); i++) {
            com.ulianasisoeva.angryalarm.model.game_objects.Duck duck = ducksRightList.get(i);
            if (checkHit(duck, touchX, touchY)) {
                duck.setLifes(duck.getLifes() - 1);
                if (!duck.isAlive()) {
                    ducksRightList.remove(duck);
                    getScores (++scores);
                    if (ducksRightList.isEmpty()){
                        generateRightDucks();
                    }
                }
            }

        }
    }

    private boolean checkHit(Duck duck, float touchX, float touchY) {
        return touchX >= duck.getX() && touchX <= duck.getX() + duck.getWidth() &&
                touchY >= duck.getY() && touchY < duck.getY() + duck.getHeight();
    }

    public void moveGunSightOnScreen(float touchX, float touchY)
    {
        gunSight.setX(touchX);
        gunSight.setY(touchY);
    }
}
