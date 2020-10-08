package com.example.safetogether.guest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import com.example.safetogether.R;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelfieActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
//    ContentValues values;
    private Uri file;
    ImageView imageView;
    Bitmap help1;

    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie);
        imageView = (ImageView) findViewById(R.id.imageView);
//        values = new ContentValues();
        Button selfieButton = findViewById(R.id.selfieButton);
        selfieButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //getting uri of the file
                file = FileProvider.getUriForFile(SelfieActivity.this,
                        "com.example.safetogether.fileprovider",
                        getFile());

                //Setting the file Uri to my photo
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,file);

                if(takePictureIntent.resolveActivity(getPackageManager())!=null)
                {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

                //TODO goto next activity and save image

            }
        });

        nextButton = findViewById(R.id.next);
        nextButton.setVisibility(View.GONE);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfieActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    //this method will create and return the path to the image file
    private File getFile() {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            ImageView imageView = findViewById(R.id.imageView);
//            imageView.setImageBitmap(imageBitmap);

            try {
                help1 = MediaStore.Images.Media.getBitmap(getContentResolver(),file);
//                imageView.setImageBitmap( help1);
                imageView.setImageBitmap( ThumbnailUtils.extractThumbnail(help1,help1.getWidth(),help1.getHeight()));
                nextButton.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
