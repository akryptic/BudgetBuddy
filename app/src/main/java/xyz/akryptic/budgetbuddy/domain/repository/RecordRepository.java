package xyz.akryptic.budgetbuddy.domain.repository;

import java.util.List;

import xyz.akryptic.budgetbuddy.domain.models.ERecord;

public interface RecordRepository {
    void addRecord(ERecord eRecord);  // Add a new record
    void deleteRecord(int id);  // Delete a record by ID
    ERecord getRecordById(int id);  // Retrieve a record by ID
    List<ERecord> getAllRecords();  // Retrieve all records
    List<ERecord> getRecordsByType(String type); // Retrieve expenses or gains
}
