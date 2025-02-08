package xyz.akryptic.budgetbuddy.domain.usecases;

import xyz.akryptic.budgetbuddy.domain.models.ERecord;
import xyz.akryptic.budgetbuddy.domain.repository.RecordRepository;

public class AddERecords {
    private final RecordRepository recordRepository;

    public AddERecords(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void execute(ERecord eRecord) {
        if (eRecord.getAmount() > 0 && (eRecord.getType().equals("expense") || eRecord.getType().equals("gain"))) {
            recordRepository.addRecord(eRecord);
        } else {
            throw new IllegalArgumentException("Invalid record data");
        }
    }
}
