package penakelex.bicycleCharts.grafics.Database.FunctionsTable;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Arrays;
import java.util.List;

@Entity(tableName = "functions_table")
@TypeConverters({FunctionsEntity.FunctionsConverter.class})
public class FunctionsEntity {
    @ColumnInfo(name = "primary_id")
    @PrimaryKey(autoGenerate = true)
    private int primaryID;
    @ColumnInfo(name = "functions")
    private List<String> functions;
    @ColumnInfo(name = "is_favourite")
    private boolean isFavourite;

    public FunctionsEntity() {
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    @Ignore
    public FunctionsEntity(List<String> functions) {
        this.functions = functions;
    }

    public int getPrimaryID() {
        return primaryID;
    }

    public void setPrimaryID(int primaryID) {
        this.primaryID = primaryID;
    }

    public List<String> getFunctions() {
        return functions;
    }

    public void setFunctions(List<String> functions) {
        this.functions = functions;
    }

    public static class FunctionsConverter {
        @TypeConverter
        public String fromFunctions(List<String> functions) {
            return String.join(" ", functions);
        }

        @TypeConverter
        public List<String> toFunctions(String functions) {
            return Arrays.asList(functions.split(" "));
        }
    }
}
