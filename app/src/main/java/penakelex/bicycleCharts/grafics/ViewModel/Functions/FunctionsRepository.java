package penakelex.bicycleCharts.grafics.ViewModel.Functions;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.HashMap;
import java.util.List;

import penakelex.bicycleCharts.grafics.Database.DataAccessObject;
import penakelex.bicycleCharts.grafics.Database.Database;
import penakelex.bicycleCharts.grafics.Database.FunctionsTable.FunctionsEntity;

/** FunctionsRepository
 *      Репозиторий с функциями
 * */
public class FunctionsRepository {
    private final DataAccessObject dataAccessObject; //Объект доступа к данным
    private final LiveData<List<FunctionsEntity>> allFunctions; //LiveData список со всеми функциями

    /** FunctionsRepository
     *      Конструктор класса
     *  Вход:
     *      Application application
     * */
    public FunctionsRepository(Application application) {
        dataAccessObject = Database.getDatabase(application).dataAccessObject();
        allFunctions = dataAccessObject.getAllFunctions();
    }

    /** add - процедура
     *      Добавление новой функции в базу данных в отдельном потоке
     *  Вход:
     *      FunctionsEntity functions - функция
     * */
    public void add(FunctionsEntity functions) {
        new AddFunctionsAsyncTask(dataAccessObject).execute(functions);
    }

    /** delete - процедура
     *      Удаление функции из базы данных в отдельном потоке
     *  Вход:
     *      FunctionsEntity functions - функция
     * */
    public void delete(FunctionsEntity functions) {
        new DeleteFunctionsAsyncTask(dataAccessObject).execute(functions);
    }

    /** deleteOldVersion - процедура
     *      Удаление старой версии функции из базы данных в отдельном потоке
     *  Вход:
     *      List<String> functions - список с функциями
     * */
    public void deleteOldVersion(List<String> functions) {
        new DeleteOldVersionAsyncTask(dataAccessObject).execute(functions);
    }

    /** deleteAll - процедура
     *      Удаление всех функций из базы данных
     * */
    public void deleteAll() {
        new DeleteAllFunctionsAsyncTask(dataAccessObject).execute();
    }

    /** updateAreas - процедура
     *      Обновление значений площадей в базе данных
     *  Вход:
     *      String areas - строка со значениями площадей,
     *      String[] functions - функции
     * */
    public void updateAreas(String areas, String[] functions) {
        HashMap<String[], String> hashMap = new HashMap<>();
        hashMap.put(functions, areas);
        new UpdateAreasAsyncTask(dataAccessObject).execute(hashMap);
    }

    /** getAllFunctions - геттер
     *      Получение LiveData списка со всеми функциями
     *  Выход:
     *      LiveData<List<FunctionsEntity>> allFunctions
     * */
    public LiveData<List<FunctionsEntity>> getAllFunctions() {
        return allFunctions;
    }

    /** UpdateAreasAsyncTask
     *      Класс для обновления значений площадей в базе данных в отдельном потоке
     * */
    private static class UpdateAreasAsyncTask extends AsyncTask<HashMap<String[], String>, Void, Void> {
        private final DataAccessObject dataAccessObject; //Объект доступа к данным

        /** UpdateAreasAsyncTask
         *      Конструктор класса
         *  Вход:
         *      DataAccessObject dataAccessObject - объект доступа к данным
         * */
        public UpdateAreasAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        /** doInBackground - процедура
         *      Обновление значений площадей в базе данных
         *  Вход:
         *      HashMap<String[], String>... hashMaps
         * */
        @Override
        protected Void doInBackground(HashMap<String[], String>... hashMaps) {
            for (String[] functions : hashMaps[0].keySet()) {
                dataAccessObject.updateAreas(hashMaps[0].get(functions), functions[0], functions[1]);
                break;
            }
            return null;
        }
    }

    /** DeleteOldVersionAsyncTask
     *      Класс для удаление старой версии "функции" из базы данных в отдельном потоке
     * */
    private static class DeleteOldVersionAsyncTask extends AsyncTask<List<String>, Void, Void> {
        private final DataAccessObject dataAccessObject; //Объект доступа к данным

        /** DeleteOldVersionAsyncTask
         *      Конструктор класса
         *  Вход:
         *      DataAccessObject dataAccessObject - объект доступа к данным
         * */
        public DeleteOldVersionAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        /** doInBackground - процедура
         *      Удаление старой версии "функции" из базы данных
         *  Вход:
         *      List<String>... lists
         * */
        @SafeVarargs
        @Override
        protected final Void doInBackground(List<String>... lists) {
            dataAccessObject.deleteOldVersion(lists[0].get(0), lists[0].get(1));
            return null;
        }
    }

    /** AddFunctionsAsyncTask
     *      Класс для добавления "функции" в базу данных в отдельном потоке
     * */
    private static class AddFunctionsAsyncTask extends AsyncTask<FunctionsEntity, Void, Void> {
        private final DataAccessObject dataAccessObject; //Объект доступа к данным

        /** AddFunctionsAsyncTask
         *      Конструктор класса
         *  Вход:
         *      DataAccessObject dataAccessObject - объект доступа к данным
         * */
        public AddFunctionsAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        /** doInBackground - процедура
         *      Добавление "функции" в базу данных
         *  Вход:
         *      FunctionsEntity... functionsEntities
         * */
        @Override
        protected Void doInBackground(FunctionsEntity... functionsEntities) {
            dataAccessObject.addNewFunction(functionsEntities[0]);
            return null;
        }
    }

    /** DeleteFunctionsAsyncTask
     *      Класс для удаления "функции" из базы данных в отдельном потоке
     * */
    private static class DeleteFunctionsAsyncTask extends AsyncTask<FunctionsEntity, Void, Void> {
        private final DataAccessObject dataAccessObject; //Объект доступа к данным

        /** DeleteFunctionsAsyncTask
         *      Конструктор класса
         *  Вход:
         *      DataAccessObject dataAccessObject - объект доступа к данным
         * */
        public DeleteFunctionsAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        /** doInBackground - процедура
         *      Удаление "функции" из базы данных
         *  Вход:
         *      FunctionsEntity... functionsEntities
         * */
        @Override
        protected Void doInBackground(FunctionsEntity... functionsEntities) {
            dataAccessObject.deleteFunction(functionsEntities[0]);
            return null;
        }
    }

    /** DeleteAllFunctionsAsyncTask
     *      Класс для удаления всех функций из базы данных в отдельном потоке
     * */
    private static class DeleteAllFunctionsAsyncTask extends AsyncTask<Void, Void, Void> {
        private final DataAccessObject dataAccessObject; //Объект доступа к данным

        /** DeleteAllFunctionsAsyncTask
         *      Конструктор класса
         *  Вход:
         *      DataAccessObject dataAccessObject - объект доступа к данным
         * */
        public DeleteAllFunctionsAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        /** doInBackground - процедура
         *      Удаление всех функций из базы данных
         * */
        @Override
        protected Void doInBackground(Void... voids) {
            dataAccessObject.deleteAllFromFunctionsTable();
            return null;
        }
    }
}
