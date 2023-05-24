package penakelex.bicycleCharts.grafics.ViewModel.Fragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;

public class FragmentsViewModel extends ViewModel {
    private FragmentsRepository repository = null;
    private LiveData<List<FragmentEntity>> allFragments = null;

    public void initiate(@NonNull Application application) {
        this.repository = new FragmentsRepository(application);
        this.allFragments = repository.getAllFragments();
    }

    public void add(FragmentEntity fragment) {
        repository.add(fragment);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<FragmentEntity>> getAllFragments() {
        return allFragments;
    }
}
