package penakelex.bicycleCharts.grafics.UI.Fragments.Charts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import penakelex.bicycleCharts.grafics.Charts.Charts;

/** ChartingFragment
 *      Фрагмент для графиков
 * */
public class ChartingFragment extends Fragment {

    /** onCreateView - функция
     *      Создание View
     *  Вход:
     *      LayoutInflater inflater,
     *      ViewGroup container,
     *      Bundle savedInstanceState
     *  Выход:
     *      View view
     * */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new Charts(requireActivity(), requireActivity(), requireActivity().getApplication(), requireArguments().getString("firstFunction"), requireArguments().getString("secondFunction"), requireArguments().getDouble("step"));
    }

    /** getChartingFragment - функция
     *      Создание фргамента для графиков и добавление в него аргументов
     *  Вход:
     *      String firstFunction - первая функция,
     *      String secondFunction - вторая функция,
     *      double step - единичный отрезок
     *  Выход:
     *      ChartingFragment fragment - фрагмент для графиков
     * */
    public static ChartingFragment getChartingFragment(String firstFunction, String secondFunction, double step) {
        ChartingFragment fragment = new ChartingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("firstFunction", firstFunction);
        bundle.putString("secondFunction", secondFunction);
        bundle.putDouble("step", step);
        fragment.setArguments(bundle);
        return fragment;
    }
}
