package android.example.minimarket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup;



import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class minuman extends AppCompatActivity {

    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static minuman da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_minuman);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView01 = (ListView) findViewById(R.id.listview);
        Button btn4=(Button)findViewById(R.id.input);

        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(minuman.this, inputproduk.class);
                startActivity(inte);
            }
        });

        da = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        try {
            SQLiteDatabase db = dbcenter.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM minuman", null);
            daftar = new String[cursor.getCount()];
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String namaProduk = cursor.getString(1); // Mengambil nama produk
                String hargaProduk = cursor.getString(2); // Mengambil harga produk
                daftar[i] = namaProduk; // Gabungkan nama dan harga produk
                cursor.moveToNext();
            }
            cursor.moveToFirst(); // Reset cursor position to the first row
            // Menggunakan adapter kustom dengan layout kustom
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list, R.id.listName, daftar) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView = view.findViewById(R.id.listPrice);
                    // Move cursor to the correct position based on the list item position
                    cursor.moveToPosition(position);
                    String harga = cursor.getString(2); // Ambil harga produk dari cursor
                    textView.setText("Rp " + harga); // Set teks harga
                    return view;
                }
            };
            ListView01.setAdapter(adapter);
            ListView01.setSelected(true);

            ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    final String selection = daftar[arg2];
                    final CharSequence[] dialogitem = {"Update Data", "Hapus Data"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(minuman.this);
                    builder.setTitle("Pilihan");
                    builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item) {
                                case 0:
                                    Intent in = new Intent(getApplicationContext(), updatedata.class);
                                    in.putExtra("nama", selection);
                                    startActivity(in);
                                    break;
                                case 1:
                                    SQLiteDatabase db = dbcenter.getWritableDatabase();
                                    db.execSQL("delete from minuman where nama = '" + selection + "'");
                                    RefreshList();
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
