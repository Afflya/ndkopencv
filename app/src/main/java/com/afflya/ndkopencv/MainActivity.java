 package com.afflya.ndkopencv;

 import android.graphics.Bitmap;
 import android.graphics.BitmapFactory;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.ImageView;

 import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnProc;
    private ImageView imageView;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnProc = findViewById(R.id.btn_gray_process);
        imageView = findViewById(R.id.image_view);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.testpic1);
        imageView.setImageBitmap(bmp);
        btnProc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int w = bmp.getWidth();
                int h = bmp.getHeight();
                int[] pixels = new int[w*h];
                bmp.getPixels(pixels, 0, w, 0, 0, w, h);
                int[] resultInt = grayProc(pixels, w, h);
                Bitmap resultImg = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                resultImg.setPixels(resultInt, 0, w, 0, 0, w, h);
                imageView.setImageBitmap(resultImg);
            }
        });
    }

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public static native int[] grayProc(int[] pixels, int w, int h);
}
