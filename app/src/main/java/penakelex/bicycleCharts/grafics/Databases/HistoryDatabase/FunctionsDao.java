package penakelex.bicycleCharts.grafics.Databases.HistoryDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FunctionsDao {
    @Query("SELECT * FROM functions")
    List<Functions> getAllFunctions();

    @Insert
    void addNewOne(Functions functions);

    @Delete
    void deleteOne(Functions functions);

    @Query("DELETE FROM functions")
    void deleteAll();
}
