package penakelex.bicycleCharts.grafics.UI.Fragments;

import static penakelex.bicycleCharts.grafics.Constants.Main_Container_ID;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import penakelex.bicycleCharts.grafics.UI.Fragments.Charts.ChartsSettingsFragment;
import penakelex.bicycleCharts.grafics.databinding.FragmentStartingBinding;

public class StartingFragment extends MainFragmentsParent {
    private FragmentStartingBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStartingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addNewFragment((byte) 0);
        binding.start.setOnClickListener(listener -> settingChartsFragment());
        //binding.history.setOnClickListener(listener -> settingFunctionsFragment());
    }

    //private void settingFunctionsFragment() {
        //requireActivity().getSupportFragmentManager().beginTransaction().replace(Main_Container_ID, new FunctionsFragment()).commit();
    //}

    private void settingChartsFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(Main_Container_ID, new ChartsSettingsFragment()).commit();
    }
}