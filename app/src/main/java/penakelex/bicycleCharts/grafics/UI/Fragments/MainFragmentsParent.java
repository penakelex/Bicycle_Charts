package penakelex.bicycleCharts.grafics.UI.Fragments;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import penakelex.bicycleCharts.grafics.Database.FragmentsTable.FragmentEntity;
import penakelex.bicycleCharts.grafics.ViewModel.Fragments.FragmentsViewModel;

/** MainFragmentsParent
 *      Родительский фрагмент
* */
public class MainFragmentsParent extends Fragment {

    /** addNewFragment - процедура
     *      Добавление нового фрагмента в базу данных
     *  Вход:
     *      byte fragment - идентификатор фрагмента
     * */
    public void addNewFragment(byte fragment) {
        FragmentsViewModel viewModel = new ViewModelProvider(requireActivity()).get(FragmentsViewModel.class);
        viewModel.initiate(requireActivity().getApplication());
        viewModel.add(new FragmentEntity(fragment));
    }
}
