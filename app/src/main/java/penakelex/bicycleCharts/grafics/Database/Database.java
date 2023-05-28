package penakelex.bicycleCharts.grafics.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;
import penakelex.bicycleCharts.grafics.Database.FunctionsTable.FunctionsEntity;

@androidx.room.Database(entities = {FragmentEntity.class, FunctionsEntity.class}, version = 8)
public abstract class Database extends RoomDatabase {
    private static Database database;

    public abstract DataAccessObject dataAccessObject();

    public static synchronized Database getDatabase(Context context) {
        if (database == null) {
            database = Room.
                    databaseBuilder(context.getApplicationContext(), Database.class, "bicycle.db").
                    fallbackToDestructiveMigration().build();
        }
        return database;
    }
}
