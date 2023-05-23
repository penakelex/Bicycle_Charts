package penakelex.bicycleCharts.grafics.Databases.HistoryDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Functions {
    @ColumnInfo(name = "primary_id")
    @PrimaryKey(autoGenerate = true)
    private int primaryID;

    @ColumnInfo(name = "functions")
    private List<String> functions;

    public Functions(List<String> functions) {
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
}
