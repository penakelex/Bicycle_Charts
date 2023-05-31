package penakelex.bicycleCharts.grafics.Database.FragmentsTable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/** Entity
 *      Класс-сущность для таблицы базы данных с "фрагментами"
 * */
@Entity(tableName = "fragment_table")
public class FragmentEntity {
    @ColumnInfo(name = "primary_id")
    @PrimaryKey(autoGenerate = true)
    private short primaryID; //Ключевой идентификатор для таблицы
    @ColumnInfo(name = "id")
    private final byte ID; //Идентификатор

    /** FragmentEntity
     *      Конструктор класс
     *  Вход:
     *      byte ID - идентификатор
     * */
    public FragmentEntity(byte ID) {
        this.ID = ID;
    }

    /** setPrimaryID - сеттер
     *      Сеттер ключевого идентификатора для таблицы
     *  Вход:
     *      short primaryID - ключевой идентификатор
     * */
    public void setPrimaryID(short primaryID) {
        this.primaryID = primaryID;
    }

    /** setPrimaryID - геттер
     *      Геттер ключевого идентификатора для таблицы
     *  Выход:
     *      short primaryID - ключевой идентификатор
     * */
    public short getPrimaryID() {
        return primaryID;
    }

    /** getID - геттер
     *      Геттер идентификатора
     *  Выход:
     *      byte ID - идентификатор
     * */
    public byte getID() {
        return ID;
    }
}
