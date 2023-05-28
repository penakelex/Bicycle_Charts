package penakelex.bicycleCharts.grafics.Database.FunctionsTable;


import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Entity(tableName = "functions_table")
public class FunctionsEntity {
    @ColumnInfo(name = "primary_id")
    @PrimaryKey(autoGenerate = true)
    private int primaryID;
    @ColumnInfo(name = "first_function")
    private String firstFunction;
    @ColumnInfo(name = "second_function")
    private String secondFunction;
    @ColumnInfo(name = "is_favourite")
    private boolean isFavourite;

    @ColumnInfo(name = "areas")
    private String areas;

    public FunctionsEntity() {
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    @Ignore
    public FunctionsEntity(List<String> functions, String areas) {
        this.firstFunction = functions.get(0);
        this.secondFunction = functions.get(1);
        this.areas = areas;
    }

    public int getPrimaryID() {
        return primaryID;
    }

    public void setPrimaryID(int primaryID) {
        this.primaryID = primaryID;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getFirstFunction() {
        return firstFunction;
    }

    public void setFirstFunction(String firstFunction) {
        this.firstFunction = firstFunction;
    }

    public String getSecondFunction() {
        return secondFunction;
    }

    public void setSecondFunction(String secondFunction) {
        this.secondFunction = secondFunction;
    }
}
