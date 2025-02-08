package xyz.akryptic.budgetbuddy.presentation.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

import xyz.akryptic.budgetbuddy.R;
import xyz.akryptic.budgetbuddy.data.repository.RecordRepositoryImpl;
import xyz.akryptic.budgetbuddy.domain.models.ERecord;
import xyz.akryptic.budgetbuddy.domain.usecases.AddERecords;
import xyz.akryptic.budgetbuddy.domain.usecases.GetERecords;
import xyz.akryptic.budgetbuddy.presentation.viewmodel.RecordViewModel;

public class MainActivity extends AppCompatActivity {

    private RecordViewModel recordViewModel;
    private EditText etAmount, etCategory;
    private Spinner spType;
    private RecordAdapter recordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.et_amount);
        etCategory = findViewById(R.id.et_category);
        spType = findViewById(R.id.sp_type);
        Button btnAdd = findViewById(R.id.btn_add);
        RecyclerView recyclerView = findViewById(R.id.recycler_records);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recordAdapter = new RecordAdapter();
        recyclerView.setAdapter(recordAdapter);

        // Initialize ViewModel with repository and use cases
        RecordRepositoryImpl repository = new RecordRepositoryImpl(this);
        AddERecords addRecordUseCase = new AddERecords(repository);
        GetERecords getRecordsUseCase = new GetERecords(repository);

        recordViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends androidx.lifecycle.ViewModel> T create(Class<T> modelClass) {
                return (T) new RecordViewModel(addRecordUseCase, getRecordsUseCase);
            }
        }).get(RecordViewModel.class);

        // Observe LiveData
        recordViewModel.getRecordsLiveData().observe(this, records -> {
            recordAdapter.submitList(records);
        });

        // Add Record Button Click
        btnAdd.setOnClickListener(v -> {
            String amountStr = etAmount.getText().toString();
            String category = etCategory.getText().toString();
            String type = spType.getSelectedItem().toString();

            if (amountStr.isEmpty() || category.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount = Double.parseDouble(amountStr);
            ERecord eRecord = new ERecord(amount, category, type, new Date());
            recordViewModel.addRecord(eRecord);

            etAmount.setText("");
            etCategory.setText("");
        });
    }
}
