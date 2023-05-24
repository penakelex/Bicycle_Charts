package penakelex.bicycleCharts.grafics.ViewModel.Functions;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

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

    public void deleteAll() {
        new DeleteAllFunctionsAsyncTask(dataAccessObject).execute();
    }


    public LiveData<List<FunctionsEntity>> getAllFunctions() {
        return allFunctions;
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
