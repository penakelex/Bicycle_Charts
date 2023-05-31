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
    private FunctionsRepository repository; //Репозиторий с "функциями"
    private LiveData<List<FunctionsEntity>> allFunctions; //LiveData список со всеми "функциями"

    /** initiate - процедура
     *      Инициализация репозитория и LiveData списка со всеми "функциями"
     *  Вход:
     *      Application application
     * */
    public void initiate(@NonNull Application application) {
        this.repository = new FunctionsRepository(application);
        this.allFunctions = repository.getAllFunctions();
    }

    /** deleteOldVersion - процедура
     *      Удаление старой версии "функции"
     *  Вход:
     *      List<String> functions - список с функциями
     * */
    public void deleteOldVersion(List<String> functions) {
        repository.deleteOldVersion(functions);
    }

    /** updateAreas - процедура
     *      Обновление значений площадей
     *  Вход:
     *      String areas - строка со значениями площадей,
     *      ArrayList<String> functions - список с функциями
     * */
    public void updateAreas(String areas, ArrayList<String> functions) {
        repository.updateAreas(areas, (String[]) functions.toArray());
    }

    /** add - процедура
     *      Добавление "функции" в базу данных
     *  Вход:
     *      FunctionsEntity functions
     * */
    public void add(FunctionsEntity functions) {
        repository.add(functions);
    }

    /** delete - процедура
     *      Удаление "функции" из базы данных
     *  Вход:
     *      FunctionsEntity functions
     * */
    public void delete(FunctionsEntity functions) {
        repository.delete(functions);
    }

    /** deleteAll - процедура
     *      Удаление всех "функций" из базы данных
     * */
    public void deleteAll() {
        repository.deleteAll();
    }

    /** getAllFunctions - геттер
     *      Получение LiveData списка со всеми функциями
     *  Выход:
     *      LiveData<List<FunctionsEntity>> allFunctions
     * */
    public LiveData<List<FunctionsEntity>> getAllFunctions() {
        return allFunctions;
    }
}
