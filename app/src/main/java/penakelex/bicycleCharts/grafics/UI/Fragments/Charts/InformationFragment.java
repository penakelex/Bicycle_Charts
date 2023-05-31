package penakelex.bicycleCharts.grafics.UI.Fragments.Charts;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FunctionsTable.FunctionsEntity;
import penakelex.bicycleCharts.grafics.R;
import penakelex.bicycleCharts.grafics.UI.Adapters.AreasAdapter;
import penakelex.bicycleCharts.grafics.ViewModel.Functions.FunctionsViewModel;

/** InformationFragment
 *      Диалоговое окно с информацией о значениях площадей
 * */
public class InformationFragment extends DialogFragment {
    private FunctionsViewModel viewModel; //viewModel для получения данных из базы данных
    private AreasAdapter adapter; //Адаптер для площадей

    /** onCreateDialog - функция
     *      Создание диалогового окна
     *  Вход:
     *      Bundle savedInstanceState
     *  Выход:
     *      Dialog dialog
     * */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Инициализация базы данных
        viewModel = new ViewModelProvider(requireActivity()).get(FunctionsViewModel.class);
        viewModel.initiate(requireActivity().getApplication());
        //Получение liveData списка функций
        LiveData<List<FunctionsEntity>> liveData = viewModel.getAllFunctions();
        //Создание view диалогового окна
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_information, null);
        builder.setView(view);
        RecyclerView recyclerView = view.findViewById(R.id.containerForAreas);
        AppCompatTextView textView = view.findViewById(R.id.functions);
        adapter = new AreasAdapter(requireActivity());
        recyclerView.setAdapter(adapter);
        //Установка наблюдателя за изменениями в базе данных
        liveData.observe(requireActivity(), functionsEntities -> {
            if (functionsEntities.size() != 0) {
                adapter.setAreasInformation(functionsEntities.get(0).getAreas());
                textView.setText(String.format("y=%s\ny=%s", functionsEntities.get(0).getFirstFunction(), functionsEntities.get(0).getSecondFunction()));
            }
        });
        return builder.create();
    }
}
