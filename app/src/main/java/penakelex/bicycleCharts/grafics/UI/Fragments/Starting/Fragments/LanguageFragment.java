package penakelex.bicycleCharts.grafics.UI.Fragments.Starting.Fragments;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import penakelex.bicycleCharts.grafics.R;

/** LanguageFragment
 *      Диалоговое окно "Язык"
 * */
public class LanguageFragment extends DialogFragment {

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
        builder.setTitle(requireActivity().getResources().getString(R.string.in_development));
        return builder.create();
    }
}