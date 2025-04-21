package com.example.lr49;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity2 extends AppCompatActivity {
    ImageLoadersClass imageLoader;
    String base_url = "https://i.pinimg.com/736x/58/5f/f0/585ff02681f2c28ae70583942fea6775.jpg";
    int error_url =R.drawable.tikva;
    ImageView imageView;
    Button defaultBtn;
    Button centrecrop;
    Button centreinside;
    Button fit;
    Button placeholder;
    Button error;
    Button callback;
    Button resize;
    EditText editHeight;
    EditText editWidth;
    Button rotateBtn;
    EditText rotateET;
    Button circle;
    Button gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String enumString = getIntent().getStringExtra("library");
        if (enumString != null) {
            ImageLibrary enumValue = ImageLibrary.valueOf(enumString);
            imageLoader = ImageLoaderFactory.create(this,enumValue);
        }
        imageView=(ImageView) findViewById(R.id.iv);
        defaultBtn = (Button) findViewById(R.id.defaultbtn);
        centrecrop = (Button) findViewById(R.id.crop);
        centreinside=(Button) findViewById(R.id.InsideBtn);
        editHeight = (EditText) findViewById(R.id.editHeight);
        placeholder = (Button) findViewById(R.id.PlaceHolderBtn);
        editWidth = (EditText) findViewById(R.id.editWidth);
        rotateBtn = (Button) findViewById(R.id.rotateBtn);
        rotateET = (EditText) findViewById(R.id.Angle);
        circle = (Button) findViewById(R.id.circleBtn);
        fit = (Button) findViewById(R.id.FitBtn);
        gif = (Button)  findViewById(R.id.Gif);
        resize = (Button) findViewById(R.id.resize);
        centrecrop = (Button) findViewById(R.id.crop);
        error = (Button) findViewById(R.id.errorBtn);
        callback = (Button) findViewById(R.id.callbackBtn);
        defaultBtn.setOnClickListener(view ->{
            imageLoader.loadImage(base_url, imageView, new ImageLoadCallback() {
                @Override
                public void onSuccess() {
                    Toast.makeText(MainActivity2.this,"Success", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(MainActivity2.this,"Error" + e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        });
        circle.setOnClickListener(view -> {
            imageLoader.customTransform(base_url,imageView);
        });
        rotateBtn.setOnClickListener(view -> {
            float x =Float.parseFloat(rotateET.getText().toString());
            imageLoader.rotateCustom(base_url,imageView,x);
        });
        resize.setOnClickListener(view -> {
            int x =Integer.parseInt(editWidth.getText().toString());
            int y =Integer.parseInt(editHeight.getText().toString());
            imageLoader.resizeImageCustom(base_url, imageView,x,y);
        });
        centrecrop.setOnClickListener(view -> {
            imageLoader.centerCrop(base_url,imageView);
            Toast.makeText(MainActivity2.this,"Centrecrop called", Toast.LENGTH_SHORT).show();
        });
        centreinside.setOnClickListener(view -> {
            imageLoader.centerInside(base_url,imageView);
            Toast.makeText(MainActivity2.this,"Centreinside called", Toast.LENGTH_SHORT).show();
        });
        fit.setOnClickListener(view -> {
            imageLoader.fit(base_url,imageView);
            Toast.makeText(MainActivity2.this,"Fit called", Toast.LENGTH_SHORT).show();
        });
        placeholder.setOnClickListener(view -> {
            imageLoader.loadImage(base_url, imageView,R.drawable.ic_launcher_background);
            Toast.makeText(MainActivity2.this,"Placeholder called ", Toast.LENGTH_SHORT).show();
        });
        error.setOnClickListener(view -> {
            imageLoader.loadImage(base_url, imageView, R.drawable.ic_launcher_background, R.drawable.tikva);
            Toast.makeText(MainActivity2.this,"Error called ", Toast.LENGTH_SHORT).show();
        });
        callback.setOnClickListener(view -> {
            imageLoader.loadImage(base_url, imageView,new ImageLoadCallback() {
                @Override
                public void onSuccess() {
                    Toast.makeText(MainActivity2.this, "Success", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure (Exception e){
                    Toast.makeText(MainActivity2.this, "Error:" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
        gif.setOnClickListener(view -> {
            Glide.with(this)
                    .asGif()
                    .load("https://media1.tenor.com/m/Ndz14XFuzAoAAAAC/renekton-league-of-legends.gif")
                    .error(R.drawable.tikva).diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(imageView);       });
    }
    public enum ImageLibrary{
        NONE,
        PICASSO,
        GLIDE,
        COIL
    }
}
