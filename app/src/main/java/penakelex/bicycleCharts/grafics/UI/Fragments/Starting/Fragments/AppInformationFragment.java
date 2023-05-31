package penakelex.bicycleCharts.grafics.UI.Fragments.Starting.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import penakelex.bicycleCharts.grafics.R;
/** AppInformationFragment
 *      Диалоговое окно "О приложении"
 * */
public class AppInformationFragment extends DialogFragment {

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
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage(requireActivity().getResources().getString(R.string.functions_descriptions));
        return builder.create();
    }
}
