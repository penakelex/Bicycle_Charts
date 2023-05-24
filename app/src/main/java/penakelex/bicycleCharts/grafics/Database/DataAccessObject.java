package penakelex.bicycleCharts.grafics.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;
import penakelex.bicycleCharts.grafics.Database.FunctionsTable.FunctionsEntity;

@Dao
public interface DataAccessObject {
    //Получение всех фрагментов из базы данных
    //Данных на входе нет
    //Тип данных на выходе: LiveData<List<FragmentEntity>>
    @Query("SELECT * FROM fragment_table")
    LiveData<List<FragmentEntity>> getAllFragments();

    //Добавление нового фрагмента в базу данных
    //Данные на входе: FragmentEntity fragmentEntity
    //Выходных данных нет
    @Insert
    void addNewFragment(FragmentEntity fragmentEntity);

    //Удаление всех фрагментов из базы данных
    //Данных на входе нет
    //Данных на выходе нет
    @Query("DELETE FROM fragment_table")
    void deleteAllFromFragmentTable();

    //Получение всех функций из базы данных
    //Данных на входе нет
    //Тип данных на выходе: LiveData<List<FunctionsEntity>>
    @Query("SELECT * FROM functions_table")
    LiveData<List<FunctionsEntity>> getAllFunctions();

    //Добавление новой функции в базу данных
    //Данные на входе: FunctionsEntity functionsEntity
    //Данных на выходе нет
    @Insert
    void addNewFunction(FunctionsEntity functionsEntity);

    //Удаление функции из базы данных
    //Данные на входе: FunctionsEntity functionsEntity
    //Данных на выходе нет
    @Delete
    void deleteFunction(FunctionsEntity functionsEntity);

    //Удаление всех функций из базы данных
    //Данных на входе нет
    //Данных на выходе нет
    @Query("DELETE FROM functions_table")
    void deleteAllFromFunctionsTable();
}
