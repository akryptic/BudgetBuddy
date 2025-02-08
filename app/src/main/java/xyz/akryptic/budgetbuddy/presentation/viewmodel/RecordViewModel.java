package xyz.akryptic.budgetbuddy.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import xyz.akryptic.budgetbuddy.domain.models.ERecord;
import xyz.akryptic.budgetbuddy.domain.usecases.AddERecords;
import xyz.akryptic.budgetbuddy.domain.usecases.GetERecords;

public class RecordViewModel extends ViewModel {

    private final AddERecords addRecordUseCase;
    private final GetERecords getRecordsUseCase;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final MutableLiveData<List<ERecord>> recordsLiveData = new MutableLiveData<>();

    public RecordViewModel(AddERecords addRecordUseCase, GetERecords getRecordsUseCase) {
        this.addRecordUseCase = addRecordUseCase;
        this.getRecordsUseCase = getRecordsUseCase;
        loadRecords();
    }

    public LiveData<List<ERecord>> getRecordsLiveData() {
        return recordsLiveData;
    }

    public void addRecord(ERecord eRecord) {
        executorService.execute(() -> {
            addRecordUseCase.execute(eRecord);
            loadRecords(); // Refresh data after adding a record
        });
    }

    private void loadRecords() {
        executorService.execute(() -> {
            List<ERecord> eRecords = getRecordsUseCase.execute();
            recordsLiveData.postValue(eRecords);
        });
    }

}
