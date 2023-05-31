package penakelex.bicycleCharts.grafics.ViewModel.Fragments;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import penakelex.bicycleCharts.grafics.Database.DataAccessObject;
import penakelex.bicycleCharts.grafics.Database.Database;
import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;

/** FragmentsRepository
 *      Репозиторий с фрагментами
 * */
public class FragmentsRepository {
    private final DataAccessObject dataAccessObject; //Объект доступа к данным
    private final LiveData<List<FragmentEntity>> allFragments; //liveData список со всеми "фрагментами"

    /** FragmentsRepository
     *      Конструктор класса
     *  Вход:
     *      Application application
     * */
    public FragmentsRepository(Application application) {
        this.dataAccessObject = Database.getDatabase(application).dataAccessObject();
        this.allFragments = dataAccessObject.getAllFragments();
    }

    /** getAllFragments - функция (геттер)
     *      Получение LiveData списка со всеми "фрагментами"
     *  Выход:
     *      LiveData<List<FragmentEntity>> allFragments - LiveData список со всеми "фрагмент"
     * */
    public LiveData<List<FragmentEntity>> getAllFragments() {
        return allFragments;
    }

    /** add - процедура
     *      Добавление нового фрагмента в базу данных в отдельном потоке
     *  Вход:
     *      FragmentEntity fragment - "фрагмент"
     * */
    public void add(FragmentEntity fragment) {
        new AddFragmentAsyncTask(dataAccessObject).execute(fragment);
    }

    /** deleteAll - процедура
     *      Удаление всех фрагментов из базы данных в отдельном потоке
     * */
    public void deleteAll() {
        new DeleteAllFragmentsAsyncTask(dataAccessObject).execute();
    }

    /** AddFragmentAsyncTask
     *      Класс для добавления фрагмента в отдельном потоке в базу данных
     * */
    private static class AddFragmentAsyncTask extends AsyncTask<FragmentEntity, Void, Void> {
        private final DataAccessObject dataAccessObject; //Объект доступа к данным

        /** AddFragmentAsyncTask
         *      Конструктор класса
         *  Вход:
         *      DataAccessObject dataAccessObject - объект доступа к данным
         * */
        public AddFragmentAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        /** doInBackground - процедура
         *      Добавление "фрагмента" в базу данных
         *  Вход:
         *      FragmentEntity... fragmentEntities
         * */
        @Override
        protected Void doInBackground(FragmentEntity... fragmentEntities) {
            dataAccessObject.addNewFragment(fragmentEntities[0]);
            return null;
        }
    }

    /** DeleteAllFragmentsAsyncTask
     *      Класс для удаления всех фрагментов из базы данных в отдельном потоке
     * */
    private static class DeleteAllFragmentsAsyncTask extends AsyncTask<Void, Void, Void> {
        private final DataAccessObject dataAccessObject; //Объект доступа к данным

        /** DeleteAllFragmentsAsyncTask
         *      Конструктор класса
         *  Вход:
         *      DataAccessObject dataAccessObject - объект доступа к данным
         * */
        public DeleteAllFragmentsAsyncTask(DataAccessObject dataAccessObject) {
            this.dataAccessObject = dataAccessObject;
        }

        /** doInBackground - процедура
         *      Удаление всех фрагментов из базы данных
         * */
        @Override
        protected Void doInBackground(Void... voids) {
            dataAccessObject.deleteAllFromFragmentTable();
            return null;
        }
    }
}
