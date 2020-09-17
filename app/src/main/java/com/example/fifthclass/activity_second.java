package com.example.fifthclass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class activity_second extends AppCompatActivity {

    Button btn;
    ImageView image;
    public static final int GALLERY_REQUEST=1,CAMERA_REQUEST=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Second Activity");

        btn=findViewById(R.id.pickBtnId);
        image=findViewById(R.id.imageId);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });



    }

    private void openDialog() {
        AlertDialog.Builder builder= new AlertDialog.Builder(activity_second.this);
        String[] option = {"Gallery","Camera"};
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int index) {
                if(index==0){
                    openGallery();
                }
                else if(index==1){
                    openCamera();
                }

            }
        }).create().show();
    }

    private void openCamera() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST);
    }

    private void openGallery() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK){
            Uri uri = data.getData();
            image.setImageURI(uri);
        }
        else if(requestCode==CAMERA_REQUEST && resultCode==RESULT_OK){
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);
        }
    }
}