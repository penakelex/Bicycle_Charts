package penakelex.bicycleCharts.grafics.UI;

import static penakelex.bicycleCharts.grafics.Constants.Main_Container_ID;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import penakelex.bicycleCharts.grafics.Databases.FragmentsDatabase.FragmentDatabaseHelper;
import penakelex.bicycleCharts.grafics.R;
import penakelex.bicycleCharts.grafics.databinding.FragmentStartingBinding;

public class StartingFragment extends Fragment {
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
        FragmentDatabaseHelper.addNewFragment((byte) 0, requireActivity().getApplicationContext());
        binding.start.setOnClickListener(listener -> settingChartsFragment());
        binding.history.setOnClickListener(listener -> settingHistory());
    }

    private void settingHistory() {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(Main_Container_ID, new HistoryFragment()).commit();
    }

    private void settingChartsFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(Main_Container_ID, new ChartsFragment()).commit();
    }
}