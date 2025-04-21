package com.example.lr49;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.module.LibraryGlideModule;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button picasso;
    Button glide;
    Button coil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picasso = (Button) findViewById(R.id.picassoBtn);
        glide = (Button) findViewById(R.id.glideBtn);
        coil = (Button) findViewById(R.id.coilBtn);
        picasso.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("library", MainActivity2.ImageLibrary.PICASSO.name());
            startActivity(intent);
        });
        glide.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("library", MainActivity2.ImageLibrary.GLIDE.name());
            startActivity(intent);
        });
        coil.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("library", MainActivity2.ImageLibrary.COIL.name());
            startActivity(intent);
        });

    }
}



