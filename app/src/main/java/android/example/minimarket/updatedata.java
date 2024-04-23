package android.example.minimarket;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class updatedata extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1;
    EditText text1, text2, text3;

    String edit;
    TextView textV1,textV2,textV3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.updateTextIdName);
        text2 = (EditText) findViewById(R.id.updateTextProductName);
        text3 = (EditText) findViewById(R.id.updateTextProductPrice);


        textV1=(TextView)findViewById(R.id.updateTextIdName);
        textV2=(TextView)findViewById(R.id.updateTextProductName);
        textV3=(TextView)findViewById(R.id.updateTextProductPrice);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM minuman WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
        }
        ton1 = (Button) findViewById(R.id.buttonSubmit2);
        // daftarkan even onClick pada btnSimpan
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Mendapatkan nilai dari EditText
                String namaProduk = text2.getText().toString();
                String hargaProduk = text3.getText().toString();

                // Memeriksa apakah EditText kosong
                if (namaProduk.isEmpty() || hargaProduk.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong...", Toast.LENGTH_SHORT).show();
                } else {
                    // Mengambil instance dari SQLiteDatabase
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    // Mengeksekusi perintah SQL untuk mengupdate data
                    db.execSQL("UPDATE minuman SET nama='" + namaProduk + "', harga='" + hargaProduk + "' WHERE id='" + text1.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(), "Perubahan Tersimpan...", Toast.LENGTH_LONG).show();
                    // Menutup activity setelah perubahan disimpan
                    finish();
                }
                // Memanggil method RefreshList() pada activity minuman untuk memperbarui tampilan list
                minuman.da.RefreshList();
            }
        });

    }
}