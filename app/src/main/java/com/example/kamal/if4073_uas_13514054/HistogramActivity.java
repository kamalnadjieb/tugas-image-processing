package com.example.kamal.if4073_uas_13514054;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class HistogramActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;

    private Bitmap bmp_bgr;
    private Bitmap bmp_grayscale;
    private Bitmap bmp_hist_bgr;
    private Bitmap bmp_hist_grayscale;

    private ImageView iv_bgr;
    private ImageView iv_grayscale;
    private ImageView iv_hist_bgr;
    private ImageView iv_hist_grayscale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogram);

        iv_bgr = (ImageView) findViewById(R.id.iv_bgr);
        iv_grayscale = (ImageView) findViewById(R.id.iv_grayscale);
        iv_hist_bgr = (ImageView) findViewById(R.id.iv_hist_bgr);
        iv_hist_grayscale = (ImageView) findViewById(R.id.iv_hist_grayscale);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            final Uri uri = data.getData();

            try {
                bmp_bgr = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {

            }

            bmp_grayscale = Pengcit.getGrayscaleBitmap(bmp_bgr);
            bmp_hist_bgr = Pengcit.getHistogramBitmap(bmp_bgr, 500, 500);
            bmp_hist_grayscale = Pengcit.getHistogramBitmap(bmp_grayscale, 500, 500);

            iv_bgr.setImageBitmap(bmp_bgr);
            iv_grayscale.setImageBitmap(bmp_grayscale);
            iv_hist_bgr.setImageBitmap(bmp_hist_bgr);
            iv_hist_grayscale.setImageBitmap(bmp_hist_grayscale);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
