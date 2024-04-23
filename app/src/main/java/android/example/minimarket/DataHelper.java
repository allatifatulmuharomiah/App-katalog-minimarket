package android.example.minimarket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "minuman.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE minuman(id INTEGER PRIMARY KEY, nama TEXT NULL, harga INTEGER NULL)";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        // Insert sample data
        sql = "INSERT INTO minuman (id, nama, harga) VALUES ('0', 'Susu Segar', '7000')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades, if needed
        Log.w(DataHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS minuman");
        onCreate(db);
    }
}

