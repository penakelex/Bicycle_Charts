package penakelex.bicycleCharts.grafics.UI.Fragments.Charts;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import penakelex.bicycleCharts.grafics.UI.Fragments.MainFragmentsParent;
import penakelex.bicycleCharts.grafics.databinding.FragmentChartsBinding;

/** ChartsFragment
 *      Фрагмент, содержащий фрагмент с графиками
 * */
public class ChartsFragment extends MainFragmentsParent {
    private FragmentChartsBinding binding; //binding

    /** onCreateView - функция
     *      Создание View
     *  Вход:
     *      LayoutInflater inflater,
     *      ViewGroup container,
     *      Bundle savedInstanceState
     *  Выход:
     *      View view
     * */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChartsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    /** onViewCreated - процедура
     *      Установка слушателя на кнопку, фрагмента в контейнер для графиков
     *  Вход:
     *      View view,
     *      Bundle savedInstanceState
     * */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Добавление фрагмента в базу данных
        addNewFragment((byte) 2);
        //Получение аргументов, которые были переданы при создании фрагмента
        String firstFunction = requireArguments().getString("firstFunction"), secondFunction = requireArguments().getString("secondFunction");
        double step = requireArguments().getDouble("step");
        //Создание фрагмента для графиков
        requireActivity().getSupportFragmentManager().beginTransaction().replace(binding.containerForCharting.getId(), ChartingFragment.getChartingFragment(firstFunction, secondFunction, step)).commit();
        //Установка графиков
        binding.information.setOnClickListener(listener -> new InformationFragment().show(requireActivity().getSupportFragmentManager(), "functionsInformation"));
    }

    /** getChartsFragment - функция
     *      Создание фргамента для графиков и добавление в него аргументов
     *  Вход:
     *      String firstFunction - первая функция,
     *      String secondFunction - вторая функция,
     *      double step - единичный отрезок
     *  Выход:
     *      ChartsFragment fragment - фрагмент для графиков
     * */
    public static ChartsFragment getChartsFragment(String firstFunction, String secondFunction, double step) {
        ChartsFragment fragment = new ChartsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("firstFunction", firstFunction);
        bundle.putString("secondFunction", secondFunction);
        bundle.putDouble("step", step);
        fragment.setArguments(bundle);
        return fragment;
    }
}