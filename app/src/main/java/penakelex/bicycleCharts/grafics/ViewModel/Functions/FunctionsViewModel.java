package penakelex.bicycleCharts.grafics.ViewModel.Functions;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FunctionsTable.FunctionsEntity;

public class FunctionsViewModel extends AndroidViewModel {
    private final FunctionsRepository repository;
    private final LiveData<List<FunctionsEntity>> allFunctions;

    public FunctionsViewModel(@NonNull Application application) {
        super(application);
        repository = new FunctionsRepository(application);
        allFunctions = repository.getAllFunctions();
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
