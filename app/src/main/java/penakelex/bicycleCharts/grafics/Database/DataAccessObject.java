package penakelex.bicycleCharts.grafics.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;
import penakelex.bicycleCharts.grafics.Database.FunctionsTable.FunctionsEntity;

/** DataAccessObject
 *      Интерфейс с описанием общих методов, которые используются при взаимодействии с базой данных
 * */
@Dao
public interface DataAccessObject {

    /** getAllFragments - функция
     *      Получение всех "фрагментов" из базы данных
     *  Выход:
     *      LiveData<List<FragmentEntity>> liveData
     * */
    @Query("SELECT * FROM fragment_table")
    LiveData<List<FragmentEntity>> getAllFragments();

    /** addNewFragment - процедура
     *      Добавление нового "фрагмента" в базу данных
     *  Вход:
     *      FragmentEntity fragmentEntity - "фрагмент"
     * */
    @Insert
    void addNewFragment(FragmentEntity fragmentEntity);

    /** deleteAllFromFragmentTable - процедура
     *      Удаление всех "фрагментов из базы данных
     * */
    @Query("DELETE FROM fragment_table")
    void deleteAllFromFragmentTable();

    /** getAllFunctions - функция
     *      Получение всех "функций" из базы данных
     *  Выход:
     *      LiveData<List<FunctionsEntity>> liveData
     * */
    @Query("SELECT * FROM functions_table")
    LiveData<List<FunctionsEntity>> getAllFunctions();

    /** addNewFunction - процедура
     *      Добавление новой функции в базу данных
     *  Выход:
     *      FunctionsEntity functionsEntity - "функция"
     * */
    @Insert
    void addNewFunction(FunctionsEntity functionsEntity);

    /** deleteFunction - процедура
     *      Удаление "функции" из базы данных
     *  Вход:
     *      FunctionsEntity functionsEntity - "функция"
     * */
    @Delete
    void deleteFunction(FunctionsEntity functionsEntity);

    /** deleteAllFromFunctionsTable - процедура
     *      Удаление всех "функций" из базы данных
     * */
    @Query("DELETE FROM functions_table")
    void deleteAllFromFunctionsTable();

    /** deleteOldVersion - процедура
     *      Удаление старой версии "функции"
     *  Вход:
     *      String firstFunction - первая функция,
     *      String secondFunction - вторая функция
     * */
    @Query("DELETE FROM functions_table WHERE first_function = :firstFunction AND second_function = :secondFunction")
    void deleteOldVersion(String firstFunction, String secondFunction);

    /** updateAreas - процедура
     *      Обновление значений площадей
     *  Вход:
     *      String areas - площади,
     *      String firstFunction - первая функция,
     *      String secondFunction - вторая функция
     * */
    @Query("UPDATE functions_table SET areas = :areas WHERE first_function = :firstFunction AND second_function = :secondFunction")
    void updateAreas(String areas, String firstFunction, String secondFunction);
}
