package xyz.akryptic.budgetbuddy.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import xyz.akryptic.budgetbuddy.data.local.DatabaseHelper;
import xyz.akryptic.budgetbuddy.domain.models.ERecord;
import xyz.akryptic.budgetbuddy.domain.repository.RecordRepository;

public class RecordRepositoryImpl implements RecordRepository {

    private final DatabaseHelper dbHelper;

    public RecordRepositoryImpl(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void addRecord(ERecord eRecord) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_AMOUNT, eRecord.getAmount());
        values.put(DatabaseHelper.COLUMN_CATEGORY, eRecord.getCategory());
        values.put(DatabaseHelper.COLUMN_TYPE, eRecord.getType());
        values.put(DatabaseHelper.COLUMN_DATE, eRecord.getDate().getTime());

        try {
            db.insertOrThrow(DatabaseHelper.TABLE_RECORDS, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    @Override
    public void deleteRecord(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_RECORDS, DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public ERecord getRecordById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_RECORDS, null, DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            ERecord record = new ERecord(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)), cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY)), cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TYPE)), new java.util.Date(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DATE))));
            cursor.close();
            db.close();
            return record;
        }
        return null;
    }

    @Override
    public List<ERecord> getAllRecords() {
        List<ERecord> records = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_RECORDS + " ORDER BY " + DatabaseHelper.COLUMN_DATE + " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                records.add(new ERecord(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)), cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY)), cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TYPE)), new java.util.Date(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DATE)))));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return records;
    }

    @Override
    public List<ERecord> getRecordsByType(String type) {
        List<ERecord> records = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_RECORDS + " WHERE " + DatabaseHelper.COLUMN_TYPE + " = ? ORDER BY " + DatabaseHelper.COLUMN_DATE + " DESC", new String[]{type});

        if (cursor.moveToFirst()) {
            do {
                records.add(new ERecord(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)), cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY)), cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TYPE)), new java.util.Date(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DATE)))));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return records;
    }
}
