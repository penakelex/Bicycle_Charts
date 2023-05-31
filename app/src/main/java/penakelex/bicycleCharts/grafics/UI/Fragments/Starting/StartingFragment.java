package penakelex.bicycleCharts.grafics.UI.Fragments.Starting;

import static penakelex.bicycleCharts.grafics.Constants.Main_Container_ID;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import penakelex.bicycleCharts.grafics.UI.Fragments.Charts.ChartsSettingsFragment;
import penakelex.bicycleCharts.grafics.UI.Fragments.MainFragmentsParent;
import penakelex.bicycleCharts.grafics.databinding.FragmentStartingBinding;

/** StartingFragment
 *      Стартовый фрагмент
 * */
public class StartingFragment extends MainFragmentsParent{
    private FragmentStartingBinding binding; //binding

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
        binding = FragmentStartingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /** onViewCreated - процедура
     *      Добавление фрагмента в базу данных и установка слушателя на кнопку
     *  Вход:
     *      View view,
     *      Bundle savedInstanceState
     * */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Добавление фрагмента в базу данных
        addNewFragment((byte) 0);
        //Установка слушателя на кнопку
        binding.start.setOnClickListener(listener -> settingChartsFragment());
    }

    /** settingChartsFragment - процедура
     *      Переход на другой фрагмент
     * */
    private void settingChartsFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(Main_Container_ID, new ChartsSettingsFragment()).commit();
    }
}