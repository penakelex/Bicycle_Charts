package penakelex.bicycleCharts.grafics.UI.Fragments;

import static penakelex.bicycleCharts.grafics.Constants.Main_Container_ID;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;
import penakelex.bicycleCharts.grafics.R;
import penakelex.bicycleCharts.grafics.UI.Fragments.Charts.ChartsFragment;
import penakelex.bicycleCharts.grafics.UI.Fragments.Functions.FunctionsFragment;
import penakelex.bicycleCharts.grafics.ViewModel.Fragments.FragmentsViewModel;
import penakelex.bicycleCharts.grafics.databinding.ToolbarBinding;

public class MainFragmentsParent extends Fragment {

    public void addNewFragment(byte fragment) {
        FragmentsViewModel viewModel = new ViewModelProvider(requireActivity()).get(FragmentsViewModel.class);
        viewModel.initiate(requireActivity().getApplication());
        viewModel.add(new FragmentEntity(fragment));
    }
}
