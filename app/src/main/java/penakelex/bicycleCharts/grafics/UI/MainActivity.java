
package penakelex.bicycleCharts.grafics.UI;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import penakelex.bicycleCharts.grafics.FragmentsAccounting;
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
        FragmentsAccounting accounting = new FragmentsAccounting();
        accounting.setFragments(getApplicationContext());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (accounting.getLastFragment()) {
            case 0:
                transaction.replace(binding.mainContainer.getId(), new StartingFragment()).commit();
                break;
            case 1:
                transaction.replace(binding.mainContainer.getId(), new HistoryFragment()).commit();
                break;
            case 2:
                transaction.replace(binding.mainContainer.getId(), new ChartsFragment()).commit();
            default:
                finishAffinity();
        }
    }
}