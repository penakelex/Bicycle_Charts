package penakelex.bicycleCharts.grafics.UI.Fragments.Charts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import penakelex.bicycleCharts.grafics.Charts.Charts;

public class ChartingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new Charts(requireActivity(), requireActivity(), requireActivity().getApplication(), requireArguments().getString("firstFunction"), requireArguments().getString("secondFunction"), requireArguments().getInt("step"));
    }

    public static ChartingFragment getChartingFragment(String firstFunction, String secondFunction, int step) {
        ChartingFragment fragment = new ChartingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("firstFunction", firstFunction);
        bundle.putString("secondFunction", secondFunction);
        bundle.putInt("step", step);
        fragment.setArguments(bundle);
        return fragment;
    }
}
