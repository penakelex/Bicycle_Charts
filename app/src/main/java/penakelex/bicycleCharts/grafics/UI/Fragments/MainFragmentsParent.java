package penakelex.bicycleCharts.grafics.UI.Fragments;

import static penakelex.bicycleCharts.grafics.Constants.Main_Container_ID;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;
import penakelex.bicycleCharts.grafics.UI.Fragments.Charts.ChartsFragment;
import penakelex.bicycleCharts.grafics.UI.Fragments.Functions.FunctionsFragment;
import penakelex.bicycleCharts.grafics.UI.MainActivity;
import penakelex.bicycleCharts.grafics.ViewModel.Fragments.FragmentsViewModel;
import penakelex.bicycleCharts.grafics.databinding.ToolbarBinding;

public class MainFragmentsParent extends Fragment {
    private ToolbarBinding binding;
    private FragmentsViewModel viewModel;

    public void addNewFragment(byte fragment) {
        viewModel = new ViewModelProvider(requireActivity()).get(FragmentsViewModel.class);
        viewModel.initiate(requireActivity().getApplication());
        viewModel.add(new FragmentEntity(fragment));
    }

    public Toolbar toolbar() {
        binding = ToolbarBinding.inflate(getLayoutInflater());
        Toolbar toolbar = binding.getRoot().findViewById(binding.toolbar.getId());
        toolbar.setNavigationOnClickListener(listener -> onNavigationClicked());
        return toolbar;
    }

    private void onNavigationClicked() {
        viewModel = new ViewModelProvider(this).get(FragmentsViewModel.class);
        viewModel.initiate(requireActivity().getApplication());
        LiveData<List<FragmentEntity>> liveData = viewModel.getAllFragments();
        liveData.observe(this, fragmentEntities -> {
            liveData.removeObservers(requireActivity());
            byte fragment;
            int size = fragmentEntities.size();
            if (size >= 2) fragment = fragmentEntities.get(size - 2).getID();
            else if (size == 1) fragment = fragmentEntities.get(0).getID();
            else fragment = -1;
            if (size >= 1) {
                if (fragmentEntities.get(size - 1).getID() == 0) fragment = -1;
            }
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            switch (fragment) {
                case 0:
                    transaction.replace(Main_Container_ID, new StartingFragment()).commit();
                    break;
                case 1:
                    transaction.replace(Main_Container_ID, new FunctionsFragment()).commit();
                    break;
                case 2:
                    transaction.replace(Main_Container_ID, new ChartsFragment()).commit();
                    break;
                default:
                    viewModel.deleteAll();
                    requireActivity().finish();
                    break;
            }
        });
    }
}
