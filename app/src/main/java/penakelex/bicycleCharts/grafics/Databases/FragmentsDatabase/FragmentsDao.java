package penakelex.bicycleCharts.grafics.Databases.FragmentsDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FragmentsDao {
    @Query("SELECT * FROM fragment")
    List<Fragment> getAll();

    @Insert
    void addNewOne(Fragment fragment);

    @Query("DELETE FROM fragment")
    void deleteAll();
}
