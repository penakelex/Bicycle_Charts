package penakelex.bicycleCharts.grafics.Databases.FragmentsDatabase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class FragmentDatabaseHelper {
    public static ArrayList<Byte> getFragmentsInformation(Context context) {
        ArrayList<Byte> arrayList = new ArrayList<>();
        List<Fragment> list = FragmentDatabase.getDatabase(context).fragmentsDao().getAll();
        for (Fragment fragment : list) {
            arrayList.add(fragment.getID());
        }
        return (ArrayList<Byte>) arrayList.clone();
    }

    public static void addNewFragment(byte ID, Context context) {
        FragmentDatabase.getDatabase(context).fragmentsDao().addNewOne(new Fragment(ID));
    }
}
