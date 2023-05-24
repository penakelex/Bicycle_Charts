package penakelex.bicycleCharts.grafics.ViewModel.Fragments;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import penakelex.bicycleCharts.grafics.Database.DataAccessObject;
import penakelex.bicycleCharts.grafics.Database.Database;
import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;

public class FragmentsRepository {
    private final DataAccessObject dataAccessObject;
    private final LiveData<List<FragmentEntity>> allFragments;

    public FragmentsRepository(Application application) {
        this.dataAccessObject = Database.getDatabase(application).dataAccessObject();
        this.allFragments = dataAccessObject.getAllFragments();
    }

    public LiveData<List<FragmentEntity>> getAllFragments() {
        return allFragments;
    }

    public void add(FragmentEntity fragment) {
        new AddFragmentAsyncTask(dataAccessObject).execute(fragment);
    }

    public void deleteAll() {
        new DeleteAllFragmentsAsyncTask(dataAccessObject).execute();
    }

    private static class AddFragmentAsyncTask extends AsyncTask<FragmentEntity, Void, Void> {
        private final DataAccessObject dataAccessObject;

        public AddFragmentAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        @Override
        protected Void doInBackground(FragmentEntity... fragmentEntities) {
            dataAccessObject.addNewFragment(fragmentEntities[0]);
            return null;
        }
    }

    private static class DeleteAllFragmentsAsyncTask extends AsyncTask<Void, Void, Void> {
        private final DataAccessObject dataAccessObject;

        public DeleteAllFragmentsAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dataAccessObject.deleteAllFromFragmentTable();
            return null;
        }
    }
}
