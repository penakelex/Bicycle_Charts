package penakelex.bicycleCharts.grafics.UI.Fragments;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;
import penakelex.bicycleCharts.grafics.ViewModel.Fragments.FragmentsViewModel;

public class MainFragmentsParent extends Fragment {
    private FragmentsViewModel viewModel;

    public void addNewFragment(byte fragment) {
        viewModel = new ViewModelProvider(requireActivity()).get(FragmentsViewModel.class);
        viewModel.initiate(requireActivity().getApplication());
        viewModel.add(new FragmentEntity(fragment));
    }
}
