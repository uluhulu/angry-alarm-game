package com.ulianasisoeva.angryalarm.screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulianasisoeva.angryalarm.R;

public class DemoFragment extends Fragment {
    View view;
    ImageView gameImage;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    Button startDemoButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_demo, container, false);
        gameImage = view.findViewById(R.id.game_screen_shot);
        textView1 = view.findViewById(R.id.descriptionView1);
        textView2 = view.findViewById(R.id.descriptionView2);
        textView3 = view.findViewById(R.id.descriptionView3);
        startDemoButton = view.findViewById(R.id.start_demo_button);

        startDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DemoFragment.this.getContext(), AndroidLauncher.class));
            }
        });
        return view;
    }
}
