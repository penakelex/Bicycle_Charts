package penakelex.bicycleCharts.grafics.Database.FunctionsTable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

/** FunctionsEntity
 *      Класс-сущность для таблицы базы данных с "функциями"
 * */
@Entity(tableName = "functions_table")
public class FunctionsEntity {
    @ColumnInfo(name = "primary_id")
    @PrimaryKey(autoGenerate = true)
    private int primaryID; //Ключевой идентификатор
    @ColumnInfo(name = "first_function")
    private String firstFunction; //Первая функция
    @ColumnInfo(name = "second_function")
    private String secondFunction; //Вторая функция
    @ColumnInfo(name = "is_favourite")
    private boolean isFavourite; //Избранный ли

    @ColumnInfo(name = "areas")
    private String areas; //Площади

    /** FunctionsEntity
     *      Пустой конструктор класса
     * */
    public FunctionsEntity() {
    }

    /** isFavourite - геттер
     *  Выход:
     *      boolean isFavourite
     * */
    public boolean isFavourite() {
        return isFavourite;
    }

    /** setFavourite - сеттер
     *  Вход:
     *      boolean favourite
     * */
    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    /** FunctionsEntity
     *      Конструктор класса
     *  Вход:
     *      List<String> functions - список с функциями,
     *      String areas - строка со значениями площадей
     * */
    @Ignore
    public FunctionsEntity(List<String> functions, String areas) {
        this.firstFunction = functions.get(0);
        this.secondFunction = functions.get(1);
        this.areas = areas;
    }

    /** getPrimaryID - геттер
     *      Получение ключевого идентификатора
     *  Выход:
     *      int primaryID - ключевой идентификатор
     * */
    public int getPrimaryID() {
        return primaryID;
    }

    /** setPrimaryID - сеттер
     *      Сеттер для ключевого идентификатора
     *  Вход:
     *      int primaryID - ключевой идентификатор
     * */
    public void setPrimaryID(int primaryID) {
        this.primaryID = primaryID;
    }

    /** getAreas - геттер
     *      Получение строки со значениями площадей
     *  Выход:
     *      String areas - строка с значениями площадей
     * */
    public String getAreas() {
        return areas;
    }

    /** setAreas - сеттер
     *      Сеттер для строки со значениями площадей
     *  Вход:
     *      String areas - строка со значениями площадей
     * */
    public void setAreas(String areas) {
        this.areas = areas;
    }

    /** getFirstFunction - геттер
     *      Получение первой функции
     *  Выход:
     *      String firstFunction - первая функция
     * */
    public String getFirstFunction() {
        return firstFunction;
    }

    /** setFirstFunction - сеттер
     *      Сеттер для строки, содержащей первую функцию
     *  Вход:
     *      String firstFunction - первая функция
     * */
    public void setFirstFunction(String firstFunction) {
        this.firstFunction = firstFunction;
    }

    /** getSecondFunction - геттер
     *      Получение второй функции
     *  Выход:
     *      String secondFunction
     * */
    public String getSecondFunction() {
        return secondFunction;
    }

    /** setSecondFunction - сеттер
     *      Сеттер для строки, содержащей вторую функцию
     *  Вход:
     *      String secondFunction - вторая функция
     * */
    public void setSecondFunction(String secondFunction) {
        this.secondFunction = secondFunction;
    }
}
