package xyz.akryptic.budgetbuddy.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_RECORDS = "records";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TYPE = "type"; // "expense" or "gain"
    public static final String COLUMN_DATE = "date";
    private static final String DATABASE_NAME = "expense_manager.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_RECORDS = "CREATE TABLE " + TABLE_RECORDS + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_AMOUNT + " REAL NOT NULL, " + COLUMN_CATEGORY + " TEXT NOT NULL, " + COLUMN_TYPE + " TEXT NOT NULL CHECK(" + COLUMN_TYPE + " IN ('expense', 'gain')), " + COLUMN_DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RECORDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        onCreate(db);
    }
}
