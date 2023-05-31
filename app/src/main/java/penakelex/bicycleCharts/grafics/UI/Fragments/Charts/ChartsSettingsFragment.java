package penakelex.bicycleCharts.grafics.UI.Fragments.Charts;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import penakelex.bicycleCharts.grafics.R;
import penakelex.bicycleCharts.grafics.UI.Fragments.MainFragmentsParent;
import penakelex.bicycleCharts.grafics.databinding.FragmentChartsSettingsBinding;

/** ChartsSettingsFragment
 *      Настройки графиков перед построением
 * */
public class ChartsSettingsFragment extends MainFragmentsParent {
    private FragmentChartsSettingsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChartsSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Добавление фрагмента в базу данных
        addNewFragment((byte) 3);
        //Установка слушателя на кнопку
        binding.make.setOnClickListener(listener -> settingCharts());
    }

    /** settingCharts - процедура
     *      Создание графиков, если заполнены все поля
     * */
    private void settingCharts() {
        //Если не заполнены все поля, то предупреждение об этом
        if (binding.firstFunction.getText().toString().length() == 0 || binding.secondFunction.getText().toString().length() == 0 || binding.step.getText().toString().length() == 0) {
            Toast.makeText(requireActivity(), requireActivity().getResources().getString(R.string.fill_all), Toast.LENGTH_SHORT).show();
        }
        //Иначе создание графиков
        else {
            closeKeyboard();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, ChartsFragment.getChartsFragment(binding.firstFunction.getText().toString(), binding.secondFunction.getText().toString(), Double.parseDouble(binding.step.getText().toString()))).commit();
        }
    }

    /** closeKeyboard - процедура
     *      Скрытие клавиатуры после завершения ввода
     * */
    private void closeKeyboard() {
        ((InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(requireActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
