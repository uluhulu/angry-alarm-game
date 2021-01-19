package com.ulianasisoeva.angryalarm.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.ulianasisoeva.angryalarm.model.game_objects.Duck;
import com.ulianasisoeva.angryalarm.model.GameModel;
import com.ulianasisoeva.angryalarm.model.game_objects.Grass;
import com.ulianasisoeva.angryalarm.model.game_objects.Water;
import com.ulianasisoeva.angryalarm.model.helper.EndGameCallback;

import static com.ulianasisoeva.angryalarm.model.GameModel.SCREEN_WIDTH;
import static com.ulianasisoeva.angryalarm.model.GameModel.SCREEN_HEIGHT;


public class AngryAlarmGame extends ApplicationAdapter implements InputProcessor {
    final EndGameCallback endGameCallback;

    SpriteBatch batch;
    Texture img;
    GameModel gameModel;
    BitmapFont bitmapFont;
    Music music;
    Sound shotgunSound;
    private float timeSeconds = 0f;
    private float period = 1f;

    private OrthographicCamera camera;

    public AngryAlarmGame(EndGameCallback endGameCallback) {
        this.endGameCallback = endGameCallback;
    }

    @Override
    public void create() {
        bitmapFont = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        gameModel = new GameModel(endGameCallback);
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        shotgunSound = Gdx.audio.newSound(Gdx.files.internal("shotgun_sound.mp3"));
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        drawBackground();
        drawClouds();
        drawTrees();

//        for (int i = 0; i < gameModel.ducksFromGround1.size(); i++) {
//            batch.begin();
//            Duck duck = gameModel.ducksFromGround1.get(i);
//            batch.draw(duck.getTexture(), duck.getX(), duck.getY(), duck.getWidth(), duck.getHeight());
//            batch.end();
//        }

        drawGrass2();
        drawWater2();

        drawRightDucks();

        drawWater1();

        drawLeftDucks();

        drawGrass1();
        drawDecoration();

        drawScoresPanel();

        drawTimerPanel();
        updateTimer();

        drawGunSight();


        gameModel.moveDucks();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        music.dispose();

    }

    public void drawScoresPanel() {
        batch.begin();
        bitmapFont.setColor(235f, 234f, 234f, 1.0f);
        bitmapFont.getData().setScale(1.5f);
        bitmapFont.draw(batch, gameModel.scoreName, 380, 780);
        batch.end();

    }

    public void drawTimerPanel() {
        batch.begin();
        bitmapFont.setColor(235f, 234f, 234f, 1.0f);
        bitmapFont.getData().setScale(1.5f);
        bitmapFont.draw(batch, gameModel.timerName, 20, 780);
        batch.end();
    }

    public void updateTimer() {
        timeSeconds += Gdx.graphics.getRawDeltaTime();
        if (timeSeconds > period) {
            timeSeconds -= period;
            gameModel.update(1);
        }
    }

    public void drawRightDucks() {
        for (int i = 0; i < gameModel.ducksRightList.size(); i++) {
            batch.begin();
            Duck duck = gameModel.ducksRightList.get(i);
            batch.draw(duck.getTexture(), duck.getX(), duck.getY(), duck.getWidth(), duck.getHeight());
            batch.end();
        }

    }

    public void drawLeftDucks() {
        for (int i = 0; i < gameModel.ducksLeftList.size(); i++) {
            batch.begin();
            Duck duck = gameModel.ducksLeftList.get(i);
            batch.draw(duck.getTexture(), duck.getX(), duck.getY(), duck.getWidth(), duck.getHeight());
            batch.end();
        }

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            endGameCallback.returnFromGame();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPosition = camera.unproject(new Vector3(screenX, screenY, 0));
        gameModel.moveGunSightOnScreen(touchPosition.x, touchPosition.y);
        drawGunSight();
        gameModel.shotDuck(touchPosition.x, touchPosition.y);
        shotgunSound.play();
        return true;
    }

    public void drawGunSight() {
        batch.begin();
        batch.draw(gameModel.gunSight.getTexture(), gameModel.gunSight.getX(), gameModel.gunSight.getY(), gameModel.gunSight.getWidth(), gameModel.gunSight.getHeight());
        batch.end();
    }

    public void drawBackground() {

        batch.begin();
        batch.draw(gameModel.woodBackground.getTexture(), gameModel.woodBackground.getX(), gameModel.woodBackground.getY(), gameModel.woodBackground.getWidth(), gameModel.woodBackground.getHeight());

        batch.end();
    }

    public void drawWater1() {

        for (int i = 0; i < gameModel.waves1.size(); i++) {
            batch.begin();
            Water water = gameModel.waves1.get(i);
            batch.draw(water.getTexture(), water.getX(), water.getY(), water.getWidth(), water.getHeight());
            batch.end();
        }
    }

    public void drawGrass1() {

        for (int i = 0; i < gameModel.grassField1.size(); i++) {
            batch.begin();
            Grass grass = gameModel.grassField1.get(i);
            batch.draw(grass.getTexture(), grass.getX(), grass.getY(), grass.getWidth(), grass.getHeight());
            batch.end();
        }
    }

    public void drawGrass2() {

        for (int i = 0; i < gameModel.grassField2.size(); i++) {
            batch.begin();
            Grass grass = gameModel.grassField2.get(i);
            batch.draw(grass.getTexture(), grass.getX(), grass.getY(), grass.getWidth(), grass.getHeight());
            batch.end();
        }
    }

    public void drawWater2() {

        for (int i = 0; i < gameModel.waves2.size(); i++) {
            batch.begin();
            Water water = gameModel.waves2.get(i);
            batch.draw(water.getTexture(), water.getX(), water.getY(), water.getWidth(), water.getHeight());
            batch.end();
        }
    }

    public void drawTrees() {

        batch.begin();
        batch.draw(gameModel.treePine.getTexture(), gameModel.treePine.getX(), gameModel.treePine.getY(), gameModel.treePine.getWidth(), gameModel.treePine.getHeight());
        batch.draw(gameModel.treeOak.getTexture(), gameModel.treeOak.getX(), gameModel.treeOak.getY(), gameModel.treeOak.getWidth(), gameModel.treeOak.getHeight());

        batch.end();
    }

    public void drawClouds() {

        batch.begin();
        batch.draw(gameModel.cloud1.getTexture(), gameModel.cloud1.getX(), gameModel.cloud1.getY(), gameModel.cloud1.getWidth(), gameModel.cloud1.getHeight());
        batch.draw(gameModel.cloud2.getTexture(), gameModel.cloud2.getX(), gameModel.cloud2.getY(), gameModel.cloud2.getWidth(), gameModel.cloud2.getHeight());

        batch.end();
    }

    public void drawDecoration() {

        batch.begin();
        batch.draw(gameModel.decoration.getTexture(), gameModel.decoration.getX(), gameModel.decoration.getY(), gameModel.decoration.getWidth(), gameModel.decoration.getHeight());

        batch.end();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
