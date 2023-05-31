package penakelex.bicycleCharts.grafics.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;
import penakelex.bicycleCharts.grafics.Database.FunctionsTable.FunctionsEntity;
/** Database
 *      Класс, работающий с базой данных
 * */
@androidx.room.Database(entities = {FragmentEntity.class, FunctionsEntity.class}, version = 8) //Таблицы
public abstract class Database extends RoomDatabase {
    private static Database database; //База данных

    public abstract DataAccessObject dataAccessObject(); //Объект доступа к данным

    /** getDatabase - функция (геттер)
     *      Получение экземпляра базы данных
     *  Вход:
     *      Context context
     *  Выход:
     *      Database database - экземпляр базы данных
     * */
    public static synchronized Database getDatabase(Context context) {
        if (database == null) {
            database = Room.
                    databaseBuilder(context.getApplicationContext(), Database.class, "bicycle.db").
                    fallbackToDestructiveMigration().build();
        }
        return database;
    }
}
