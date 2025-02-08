package xyz.akryptic.budgetbuddy.presentation.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import xyz.akryptic.budgetbuddy.R;
import xyz.akryptic.budgetbuddy.domain.models.ERecord;

public class RecordAdapter extends ListAdapter<ERecord, RecordAdapter.RecordViewHolder> {

    private static final DiffUtil.ItemCallback<ERecord> DIFF_CALLBACK = new DiffUtil.ItemCallback<ERecord>() {
        @Override
        public boolean areItemsTheSame(@NonNull ERecord oldItem, @NonNull ERecord newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ERecord oldItem, @NonNull ERecord newItem) {
            return oldItem.equals(newItem);
        }
    };

    public RecordAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.erecord, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        ERecord eRecord = getItem(position);
        holder.bind(eRecord);
    }

    static class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvAmount, tvCategory, tvType, tvDate;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvType = itemView.findViewById(R.id.tv_type);
            tvDate = itemView.findViewById(R.id.tv_date);
        }

        public void bind(ERecord eRecord) {
            tvAmount.setText(String.format(Locale.getDefault(), "₹ %.2f", eRecord.getAmount()));
            tvCategory.setText(eRecord.getCategory());
            tvType.setText(eRecord.getType().toUpperCase());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            tvDate.setText(dateFormat.format(eRecord.getDate()));

            // Change text color based on type (Expense: Red, Gain: Green)
            if (eRecord.getType().equals("expense")) {
                tvAmount.setTextColor(itemView.getContext().getResources().getColor(R.color.red, null));
            } else {
                tvAmount.setTextColor(itemView.getContext().getResources().getColor(R.color.green, null));
            }
        }
    }
}