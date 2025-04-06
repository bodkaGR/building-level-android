package com.bodkasoft.buildinglevel.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bodkasoft.buildinglevel.R;
import com.bodkasoft.buildinglevel.data.SensorRepository;
import com.bodkasoft.buildinglevel.ui.view.LevelView;
import com.bodkasoft.buildinglevel.ui.viewmodel.LevelViewModel;

public class MainActivity extends AppCompatActivity {
    private SensorRepository repository;
    private LevelView levelView;
    private TextView angleText;

    //
    private TextView XText;
    private TextView YText;
    //

    private LevelViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        levelView = new LevelView(this);
        angleText = new TextView(this);

        //
        XText = new TextView(this);
        YText = new TextView(this);
        //

        angleText.setTextSize(24f);
        angleText.setTextColor(getColor(R.color.white));

        //
        XText.setTextSize(24f);
        XText.setTextColor(getColor(R.color.white));

        YText.setTextSize(24f);
        YText.setTextColor(getColor(R.color.white));
        //

        FrameLayout layout = new FrameLayout(this);
        layout.addView(levelView);
        layout.addView(angleText);

        layout.addView(XText);
        layout.addView(YText);
        setContentView(layout);

        viewModel = new ViewModelProvider(this).get(LevelViewModel.class);
        repository = new SensorRepository(this);

        setupObservers();
    }

    private void setupObservers() {
        viewModel.getPitch().observe(this, pitch -> {
            angleText.setText("Кут нахилу: " + pitch + "°");
            levelView.setAngle(pitch);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        repository.startListening((x, y) -> {
            viewModel.onSensorData(x, y);

            XText.setText("\nX: " + x);
            YText.setText("\n\nY: " + y);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        repository.stopListening();
    }
}