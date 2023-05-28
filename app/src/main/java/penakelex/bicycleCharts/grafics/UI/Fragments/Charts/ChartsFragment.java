package penakelex.bicycleCharts.grafics.UI.Fragments.Charts;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import penakelex.bicycleCharts.grafics.R;
import penakelex.bicycleCharts.grafics.UI.Fragments.MainFragmentsParent;
import penakelex.bicycleCharts.grafics.UI.MainActivity;
import penakelex.bicycleCharts.grafics.databinding.FragmentChartsBinding;

public class ChartsFragment extends MainFragmentsParent {
    private FragmentChartsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChartsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addNewFragment((byte) 2);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(binding.containerForCharting.getId(), new ChartingFragment()).commit();
    }
}