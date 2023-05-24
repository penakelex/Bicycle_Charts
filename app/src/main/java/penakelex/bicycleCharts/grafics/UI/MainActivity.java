
package penakelex.bicycleCharts.grafics.UI;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;
import penakelex.bicycleCharts.grafics.UI.Fragments.ChartsFragment;
import penakelex.bicycleCharts.grafics.UI.Fragments.Functions.FunctionsFragment;
import penakelex.bicycleCharts.grafics.UI.Fragments.StartingFragment;
import penakelex.bicycleCharts.grafics.ViewModel.Fragments.FragmentsViewModel;
import penakelex.bicycleCharts.grafics.databinding.ActivityMainBinding;

public class MainActivity extends FragmentActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(binding.mainContainer.getId(), new StartingFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        FragmentsViewModel viewModel = new ViewModelProvider(this).get(FragmentsViewModel.class);
        viewModel.initiate(getApplication());
        LiveData<List<FragmentEntity>> liveData = viewModel.getAllFragments();
        liveData.observe(this, fragmentEntities -> {
            liveData.removeObservers(MainActivity.this);
            byte fragment;
            int size = fragmentEntities.size();
            if (size >= 2) fragment = fragmentEntities.get(size - 2).getID();
            else if (size == 1) fragment = fragmentEntities.get(0).getID();
            else fragment = -1;
            if (size >= 1) {
                if (fragmentEntities.get(size - 1).getID() == 0) fragment = -1;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (fragment) {
                case 0:
                    transaction.replace(binding.mainContainer.getId(), new StartingFragment()).commit();
                    break;
                case 1:
                    transaction.replace(binding.mainContainer.getId(), new FunctionsFragment()).commit();
                    break;
                case 2:
                    transaction.replace(binding.mainContainer.getId(), new ChartsFragment()).commit();
                    break;
                default:
                    viewModel.deleteAll();
                    finish();
                    break;
            }
        });
    }
}