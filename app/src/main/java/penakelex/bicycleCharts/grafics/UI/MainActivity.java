package penakelex.bicycleCharts.grafics.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;
import penakelex.bicycleCharts.grafics.R;
import penakelex.bicycleCharts.grafics.UI.Fragments.Charts.ChartsFragment;
import penakelex.bicycleCharts.grafics.UI.Fragments.Charts.ChartsSettingsFragment;
import penakelex.bicycleCharts.grafics.UI.Fragments.StartingFragment;
import penakelex.bicycleCharts.grafics.ViewModel.Fragments.FragmentsViewModel;
import penakelex.bicycleCharts.grafics.databinding.ActivityMainBinding;
import penakelex.bicycleCharts.grafics.databinding.ToolbarBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ToolbarBinding toolbarBinding = ToolbarBinding.bind(binding.activityToolbar.toolbar);
        setSupportActionBar(toolbarBinding.toolbar);
        getSupportFragmentManager().beginTransaction().replace(binding.mainContainer.getId(), new StartingFragment()).commit();
        handlingToolbar(toolbarBinding.toolbar);
    }

    private void handlingToolbar(Toolbar toolbar) {
        if (viewModel == null)
            viewModel = new ViewModelProvider(this).get(FragmentsViewModel.class);
        viewModel.initiate(getApplication());
        LiveData<List<FragmentEntity>> liveData = viewModel.getAllFragments();
        liveData.observe(this, fragmentEntities -> {
            switch (fragmentEntities.get(fragmentEntities.size() - 1).getID()) {
                case 0 -> {
                    toolbar.setNavigationIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.menu_icon));
                    toolbar.setNavigationOnClickListener(listener -> {
                    });
                    toolbar.setTitle("");
                }
                case 1 -> {
                    toolbar.setTitle(getApplicationContext().getResources().getString(R.string.functions));
                    toolbar.setNavigationIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.arrow_back_icon));
                    toolbar.setNavigationOnClickListener(listener -> onBackPressed());
                }
                case 2, 3 -> {
                    toolbar.setTitle(getApplicationContext().getResources().getString(R.string.charts));
                    toolbar.setNavigationIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.arrow_back_icon));
                    toolbar.setNavigationOnClickListener(listener -> onBackPressed());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewModel == null)
            viewModel = new ViewModelProvider(this).get(FragmentsViewModel.class);
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
                if (fragmentEntities.get(size - 2).getID() == 3) fragment = 0;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (fragment) {
                case 0 ->
                        transaction.replace(binding.mainContainer.getId(), new StartingFragment()).commit();
                //case 1 -> transaction.replace(binding.mainContainer.getId(), new FunctionsFragment()).commit();
                case 2 ->
                        transaction.replace(binding.mainContainer.getId(), new ChartsFragment()).commit();
                case 3 ->
                        transaction.replace(binding.mainContainer.getId(), new ChartsSettingsFragment()).commit();
                default -> {
                    viewModel.deleteAll();
                    finish();
                }
            }
        });
    }
}