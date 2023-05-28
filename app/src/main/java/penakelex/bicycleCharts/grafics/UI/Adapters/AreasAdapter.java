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

public class AreasAdapter extends RecyclerView.Adapter<AreasAdapter.ViewHolder> {
    private final ArrayList<Double> areasInformation;
    private final Context context;

    @SuppressLint("NotifyDataSetChanged")
    public AreasAdapter(Context context) {
        this.context = context;
        this.areasInformation = new ArrayList<>();
        areasInformation.add(-1.);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AreasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemFunctionAndAreasBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull AreasAdapter.ViewHolder holder, int position) {
        holder.bind(areasInformation.get(position), position, context);
    }

    @Override
    public int getItemCount() {
        return areasInformation.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setAreasInformation(String values) {
        areasInformation.clear();
        for (String value : values.split(" ")) {
            areasInformation.add(Double.parseDouble(value));
        }
        if (areasInformation.size() == 0) {
            areasInformation.add(-1.);
        }
        notifyDataSetChanged();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemFunctionAndAreasBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.binding = ItemFunctionAndAreasBinding.bind(itemView);
        }

        public void bind(Double value, int position, Context context) {
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
            if (value == -1.) {
                binding.method.setText(context.getResources().getString(R.string.loading));
                binding.value.setText("");
            }
        }
    }
}
