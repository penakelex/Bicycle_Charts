package penakelex.bicycleCharts.grafics.Database.FragmentsTable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fragment_table")
public class FragmentEntity {
    @ColumnInfo(name = "primary_id")
    @PrimaryKey(autoGenerate = true)
    private short primaryID;
    @ColumnInfo(name = "id")
    private final byte ID;

    public FragmentEntity(byte ID) {
        this.ID = ID;
    }

    public void setPrimaryID(short primaryID) {
        this.primaryID = primaryID;
    }

    public short getPrimaryID() {
        return primaryID;
    }

    public byte getID() {
        return ID;
    }
}
