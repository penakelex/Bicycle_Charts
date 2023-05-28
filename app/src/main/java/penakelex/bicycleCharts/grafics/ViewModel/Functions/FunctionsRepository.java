package penakelex.bicycleCharts.grafics.ViewModel.Functions;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import penakelex.bicycleCharts.grafics.Database.DataAccessObject;
import penakelex.bicycleCharts.grafics.Database.Database;
import penakelex.bicycleCharts.grafics.Database.FunctionsTable.FunctionsEntity;

public class FunctionsRepository {
    private final DataAccessObject dataAccessObject;
    private final LiveData<List<FunctionsEntity>> allFunctions;

    public FunctionsRepository(Application application) {
        dataAccessObject = Database.getDatabase(application).dataAccessObject();
        allFunctions = dataAccessObject.getAllFunctions();
    }

    public void add(FunctionsEntity functions) {
        new AddFunctionsAsyncTask(dataAccessObject).execute(functions);
    }

    public void delete(FunctionsEntity functions) {
        new DeleteFunctionsAsyncTask(dataAccessObject).execute(functions);
    }

    public void deleteOldVersion(List<String> functions) {
        new DeleteOldVersionAsyncTask(dataAccessObject).execute(functions);
    }

    public void deleteAll() {
        new DeleteAllFunctionsAsyncTask(dataAccessObject).execute();
    }

    public void updateAreas(String areas, String[] functions) {
        HashMap<String[], String> hashMap = new HashMap<>();
        hashMap.put(functions, areas);
        new UpdateAreasAsyncTask(dataAccessObject).execute(hashMap);
    }


    public LiveData<List<FunctionsEntity>> getAllFunctions() {
        return allFunctions;
    }

    private static class UpdateAreasAsyncTask extends AsyncTask<HashMap<String[], String>, Void, Void> {
        private final DataAccessObject dataAccessObject;

        public UpdateAreasAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        @Override
        protected Void doInBackground(HashMap<String[], String>... hashMaps) {
            for (String[] functions : hashMaps[0].keySet()) {
                dataAccessObject.updateAreas(hashMaps[0].get(functions), functions[0], functions[1]);
                break;
            }
            return null;
        }
    }

    private static class DeleteOldVersionAsyncTask extends AsyncTask<List<String>, Void, Void> {
        private final DataAccessObject dataAccessObject;

        public DeleteOldVersionAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<String>... lists) {
            dataAccessObject.deleteOldVersion(lists[0].get(0), lists[0].get(1));
            return null;
        }
    }

    private static class AddFunctionsAsyncTask extends AsyncTask<FunctionsEntity, Void, Void> {
        private final DataAccessObject dataAccessObject;

        public AddFunctionsAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        @Override
        protected Void doInBackground(FunctionsEntity... functionsEntities) {
            dataAccessObject.addNewFunction(functionsEntities[0]);
            return null;
        }
    }

    private static class DeleteFunctionsAsyncTask extends AsyncTask<FunctionsEntity, Void, Void> {
        private final DataAccessObject dataAccessObject;

        public DeleteFunctionsAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        @Override
        protected Void doInBackground(FunctionsEntity... functionsEntities) {
            dataAccessObject.deleteFunction(functionsEntities[0]);
            return null;
        }
    }

    private static class DeleteAllFunctionsAsyncTask extends AsyncTask<Void, Void, Void> {
        private final DataAccessObject dataAccessObject;

        public DeleteAllFunctionsAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dataAccessObject.deleteAllFromFunctionsTable();
            return null;
        }
    }
}
