package android.example.minimarket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splashscreen extends AppCompatActivity {

    private int waktu_loading=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//setelah loading maka akan langsung berpindah ke Main activity
                Intent home = new Intent(splashscreen.this,
                        MainActivity.class);
                startActivity(home);
                finish();
            }
        }, waktu_loading);


    }
}