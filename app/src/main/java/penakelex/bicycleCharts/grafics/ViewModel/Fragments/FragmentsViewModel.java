package penakelex.bicycleCharts.grafics.ViewModel.Fragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;

/** FragmentsViewModel
 *      ViewModel для фрагментов
 * */
public class FragmentsViewModel extends ViewModel {
    private FragmentsRepository repository = null; //Репозиторий
    private LiveData<List<FragmentEntity>> allFragments = null; //LiveData список со всеми фрагментами

    /** initiate - процедура
     *      Инициализация репозитория и LiveData списка со всеми фрагментами
     *  Вход:
     *      Application application
     * */
    public void initiate(@NonNull Application application) {
        this.repository = new FragmentsRepository(application);
        this.allFragments = repository.getAllFragments();
    }

    /** add - процедура
     *      Добавление нового фрагмента в базу данных через репозиторий
     *  Вход:
     *      FragmentEntity fragment
     * */
    public void add(FragmentEntity fragment) {
        repository.add(fragment);
    }

    /** deleteAll - процедура
     *      Удаление всех фрагментов через репозиторий
     * */
    public void deleteAll() {
        repository.deleteAll();
    }

    /** getAllFragments - геттер
     *      Получение LiveData списка всех фрагментов
     *  Выход:
     *      LiveData<List<FragmentEntity>> allFragments
     * */
    public LiveData<List<FragmentEntity>> getAllFragments() {
        return allFragments;
    }
}
