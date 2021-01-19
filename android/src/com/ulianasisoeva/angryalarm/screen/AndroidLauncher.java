package com.ulianasisoeva.angryalarm.screen;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ulianasisoeva.angryalarm.model.helper.EndGameCallback;
import com.ulianasisoeva.angryalarm.view.AngryAlarmGame;

public class AndroidLauncher extends AndroidApplication implements EndGameCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new AngryAlarmGame(this), config);
    }

    @Override
    public void returnFromGame() {
        getContext().startActivity(new Intent(this, TabLayoutActivity.class));
    }
}
