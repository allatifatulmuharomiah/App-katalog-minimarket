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

public class inputproduk extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1;
    EditText text1, text2, text3;
    String edit;
    TextView textV1,textV2,textV3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputproduk);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editTextIdName);
        text2 = (EditText) findViewById(R.id.editTextProductName);
        text3 = (EditText) findViewById(R.id.editTextProductPrice);

        textV1=(TextView)findViewById(R.id.editTextIdName);
        textV2=(TextView)findViewById(R.id.editTextProductName);
        textV3=(TextView)findViewById(R.id.editTextProductPrice);
        ton1 = (Button) findViewById(R.id.buttonSubmit);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db =
                        dbHelper.getWritableDatabase();

                edit = text1.getText().toString();
                edit=text2.getText().toString();
                edit=text3.getText().toString();

                if(edit.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Kolom tidak boleh kosong...",Toast.LENGTH_SHORT).show();
                }    else{


                    db.execSQL("insert into minuman(id, nama, harga) values('" +
                            text1.getText().toString() + "','" +
                            text2.getText().toString() + "','" +
                            text3.getText().toString() + "')");

                    Toast.makeText(getApplicationContext(), "Data Tersimpan...", Toast.LENGTH_LONG).show();
                    finish();
                }

                minuman.da.RefreshList();

            }

        });


    }
}