package com.bodkasoft.buildinglevel.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bodkasoft.buildinglevel.R;
import com.bodkasoft.buildinglevel.data.SensorRepository;
import com.bodkasoft.buildinglevel.databinding.FragmentLineLevelBinding;
import com.bodkasoft.buildinglevel.ui.viewmodel.LevelViewModel;

public class LineLevelFragment extends Fragment {
    private SensorRepository repository;
    private FragmentLineLevelBinding binding;
    private LevelViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLineLevelBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(LevelViewModel.class);
        repository = new SensorRepository(requireContext());

        binding.angleText.setTextSize(24f);
        binding.angleText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));

        setupObservers();
    }

    private void setupObservers() {
        viewModel.getPitch().observe(requireActivity(), pitch -> {
            binding.angleText.setText(pitch + "°");
            binding.levelLinearLayout.setRotation(pitch);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        repository.startListening((axisZ) -> viewModel.onSensorData(axisZ));
    }

    @Override
    public void onPause() {
        super.onPause();
        repository.stopListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}