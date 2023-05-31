package penakelex.bicycleCharts.grafics.UI.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import penakelex.bicycleCharts.grafics.R;
import penakelex.bicycleCharts.grafics.databinding.ItemFunctionAndAreasBinding;

/** AreasAdapter
 * Класс-адаптер для списка значений площадей
 */
public class AreasAdapter extends RecyclerView.Adapter<AreasAdapter.ViewHolder> {
    private final ArrayList<Double> areasInformation; //Список со значениями площадей
    private final Context context; //Контекст для получения ресурсов

    /** AreasAdapter
     *      Конструктор класса
     *  Вход:
     *      Context context
     * */
    @SuppressLint("NotifyDataSetChanged")
    public AreasAdapter(Context context) {
        this.context = context;
        this.areasInformation = new ArrayList<>();
        //По умолчанию добавляется -1.0
        areasInformation.add(-1.);
        notifyDataSetChanged();
    }

    /** onCreateViewHolder - функция
     *      Создание ViewHolder
     *  Вход:
     *      ViewGroup parent,
     *      int viewType
     *  Выход:
     *      AreasAdapter.ViewHolder viewHolder
     * */
    @NonNull
    @Override
    public AreasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemFunctionAndAreasBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false).getRoot());
    }

    /** onBindViewHolder - процедура
     *      Получение значения из списка по позиции, передача полученного значения, позиции, контекста в метод bind класса AreasAdapter.ViewHolder
     *  Вход:
     *      AreasAdapter.ViewHolder holder,
     *      int position
     * */
    @Override
    public void onBindViewHolder(@NonNull AreasAdapter.ViewHolder holder, int position) {
        holder.bind(areasInformation.get(position), position, context);
    }

    /** getItemCount - функция
     *      Получение информации о количестве элементов
     *  Выход:
     *      int size
     * */
    @Override
    public int getItemCount() {
        return areasInformation.size();
    }

    /** setAreasInformation - процедура (сеттер)
     *      Обновление списка со значениями площадей
     *  Вход:
     *      String values - строка со значениями площадей
     * */
    @SuppressLint("NotifyDataSetChanged")
    public void setAreasInformation(String values) {
        //Очистка списка
        areasInformation.clear();
        //Установка значений из строки
        for (String value : values.split(" ")) {
            areasInformation.add(Double.parseDouble(value));
        }
        //Если значений ещё нет, то добавление значения по умолчанию
        if (areasInformation.size() == 0) {
            areasInformation.add(-1.);
        }
        notifyDataSetChanged();
    }

    /** AreasAdapter.ViewHolder
     *      Собственный ViewHolder
     * */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemFunctionAndAreasBinding binding; //Binding для установки текста в поля названия метода и значения площади для данного метода

        /** ViewHolder
         *      Конструктор класса
         *  Вход:
         *      View itemView
         * */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.binding = ItemFunctionAndAreasBinding.bind(itemView);
        }

        /** bind - процедура
         *      Установка текста в поля названия метода и значения площади для данного метода
         *  Вход:
         *      Double value - значение площади,
         *      int position - позиция в списке,
         *      Context context
         * */
        public void bind(Double value, int position, Context context) {
            //Если значение не равно -1.0, то устанавливаются данные из списка
            if (value != -1.) {
                binding.value.setText(String.valueOf(value));
                switch (position) {
                    case 0 ->
                            binding.method.setText(context.getResources().getString(R.string.left_rectangles_method));
                    case 1 ->
                            binding.method.setText(context.getResources().getString(R.string.middle_rectangles_method));
                    case 2 ->
                            binding.method.setText(context.getResources().getString(R.string.right_rectangles_method));
                    case 3 ->
                            binding.method.setText(context.getResources().getString(R.string.trapezoid_method));
                }
            }
            //Иначе вывод загрузки
            else {
                binding.method.setText(context.getResources().getString(R.string.loading));
                binding.value.setText("");
            }
        }
    }
}
