package com.bodkasoft.buildinglevel.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bodkasoft.buildinglevel.R;
import com.bodkasoft.buildinglevel.data.SensorRepository;
import com.bodkasoft.buildinglevel.databinding.ActivityMainBinding;
import com.bodkasoft.buildinglevel.ui.fragment.LineLevelFragment;
import com.bodkasoft.buildinglevel.ui.viewmodel.LevelViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LineLevelFragment lineLevelFragment = new LineLevelFragment();

        setupFragment(lineLevelFragment, R.id.levelLayoutFrame);
    }

    private void setupFragment(Fragment fragment, int id) {
        Fragment existingFragment = getSupportFragmentManager().findFragmentById(id);

        if (existingFragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(id, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}