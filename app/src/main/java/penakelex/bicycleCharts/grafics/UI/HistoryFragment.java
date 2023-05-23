package penakelex.bicycleCharts.grafics.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import penakelex.bicycleCharts.grafics.Databases.FragmentsDatabase.FragmentDatabaseHelper;
import penakelex.bicycleCharts.grafics.R;
import penakelex.bicycleCharts.grafics.databinding.FragmentHistoryBinding;

public class HistoryFragment extends Fragment {
    private FragmentHistoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentDatabaseHelper.addNewFragment((byte) 1, requireActivity().getApplicationContext());
    }
}