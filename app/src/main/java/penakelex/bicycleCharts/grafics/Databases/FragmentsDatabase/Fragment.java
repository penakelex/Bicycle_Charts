package penakelex.bicycleCharts.grafics.Databases.FragmentsDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Fragment {
    @ColumnInfo(name = "primary_id")
    @PrimaryKey(autoGenerate = true)
    private short primaryID;

    public void setPrimaryID(short primaryID) {
        this.primaryID = primaryID;
    }

    @ColumnInfo(name = "id")
    private final byte ID;

    public Fragment(byte ID) {
        this.ID = ID;
    }

    public short getPrimaryID() {
        return primaryID;
    }

    public byte getID() {
        return ID;
    }
}
