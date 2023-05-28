package penakelex.bicycleCharts.grafics.ViewModel.Functions;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FunctionsTable.FunctionsEntity;
import penakelex.bicycleCharts.grafics.ViewModel.Fragments.FragmentsRepository;

public class FunctionsViewModel extends ViewModel {
    private FunctionsRepository repository;
    private LiveData<List<FunctionsEntity>> allFunctions;

    public void initiate(@NonNull Application application) {
        this.repository = new FunctionsRepository(application);
        this.allFunctions = repository.getAllFunctions();
    }

    public void deleteOldVersion(List<String> functions) {
        repository.deleteOldVersion(functions);
    }

    public void updateAreas(String areas, ArrayList<String> functions) {
        repository.updateAreas(areas, (String[]) functions.toArray());
    }

    public void add(FunctionsEntity functions) {
        repository.add(functions);
    }

    public void delete(FunctionsEntity functions) {
        repository.delete(functions);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<FunctionsEntity>> getAllFunctions() {
        return allFunctions;
    }
}
