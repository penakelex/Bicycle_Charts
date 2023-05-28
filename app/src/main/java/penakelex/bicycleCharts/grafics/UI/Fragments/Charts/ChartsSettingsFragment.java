package penakelex.bicycleCharts.grafics.UI.Fragments.Charts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import penakelex.bicycleCharts.grafics.R;
import penakelex.bicycleCharts.grafics.UI.Fragments.MainFragmentsParent;
import penakelex.bicycleCharts.grafics.databinding.FragmentChartsSettingsBinding;

public class ChartsSettingsFragment extends MainFragmentsParent {
    private FragmentChartsSettingsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChartsSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addNewFragment((byte) 3);
        binding.make.setOnClickListener(listener -> settingCharts());
    }

    private void settingCharts() {
        if (binding.firstFunction.getText().toString().length() == 0 || binding.secondFunction.getText().toString().length() == 0 || binding.step.getText().toString().length() == 0) {
            Toast.makeText(requireActivity(), requireActivity().getResources().getString(R.string.fill_all), Toast.LENGTH_SHORT).show();
        } else {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, ChartsFragment.getChartsFragment(binding.firstFunction.getText().toString(), binding.secondFunction.getText().toString(), Integer.parseInt(binding.step.getText().toString()))).commit();
        }
    }
}
