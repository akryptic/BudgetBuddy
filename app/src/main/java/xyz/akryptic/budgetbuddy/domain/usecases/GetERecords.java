package xyz.akryptic.budgetbuddy.domain.usecases;

import java.util.List;

import xyz.akryptic.budgetbuddy.domain.models.ERecord;
import xyz.akryptic.budgetbuddy.domain.repository.RecordRepository;

public class GetERecords {
    private final RecordRepository recordRepository;

    public GetERecords(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<ERecord> execute() {
        return recordRepository.getAllRecords();
    }

    public List<ERecord> executeByType(String type) {
        if (type.equals("expense") || type.equals("gain")) {
            return recordRepository.getRecordsByType(type);
        } else {
            throw new IllegalArgumentException("Invalid record type");
        }
    }
}
