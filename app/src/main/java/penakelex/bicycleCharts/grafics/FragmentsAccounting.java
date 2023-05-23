package penakelex.bicycleCharts.grafics;

import android.content.Context;

import java.util.ArrayList;

import penakelex.bicycleCharts.grafics.Databases.FragmentsDatabase.FragmentDatabaseHelper;

public class FragmentsAccounting {
    public static ArrayList<Byte> fragments = new ArrayList<>();

    public void setFragments(Context context) {
        fragments = FragmentDatabaseHelper.getFragmentsInformation(context);
    }
    public int getLastFragment() {
        if (fragments.size() >= 2) return fragments.get(fragments.size() - 2);
        return -1;
    }
}
