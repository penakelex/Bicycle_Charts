package penakelex.bicycleCharts.grafics.Databases.FragmentsDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Fragment.class}, version = 2)
public abstract class FragmentDatabase extends RoomDatabase {
    private static final String Database_Name = "fragments_accounting.db";
    private static FragmentDatabase database;

    public static synchronized FragmentDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), FragmentDatabase.class, Database_Name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return database;
    }
    public abstract FragmentsDao fragmentsDao();
}
