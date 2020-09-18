package com.example.examplecalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class Gallery_layout extends AppCompatActivity{

    private ArrayList<String> imagesEncodedList;
    private String imageEncoded;
    private Uri selectedImageUri;
    int PICK_MULTIPLE_IMAGES = 1;
    FloatingActionButton floating_add_gallery;
    RecyclerView rec;
    My_Gallery_Adapter recyclerViewAdapter;
    ArrayList<String> list = new ArrayList<>();
    File path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_layout);

        rec = (RecyclerView) findViewById(R.id.myrec);

        loadAllimages();
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new GridLayoutManager(this,2));
        recyclerViewAdapter = new My_Gallery_Adapter(this, list);
        rec.setAdapter(recyclerViewAdapter);

        floating_add_gallery = findViewById(R.id.floating_add_gallery);

        floating_add_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select photos"),PICK_MULTIPLE_IMAGES);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == PICK_MULTIPLE_IMAGES && resultCode == RESULT_OK){

            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            imagesEncodedList = new ArrayList<String>();
            if(data.getData()!=null){

                Uri mImageUri=data.getData();

                Cursor cursor = getContentResolver().query(mImageUri,filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imageEncoded  = cursor.getString(columnIndex);
                Log.d("imageEncoded","image in imageEncoded" );
                cursor.close();

            } else {
                if (data.getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    for (int i = 0; i < mClipData.getItemCount(); i++) {

                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        mArrayUri.add(uri);
                        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imageEncoded  = cursor.getString(columnIndex);
                        Log.d("imageencoded uri","images in imageencoded" + uri);
                        imagesEncodedList.add(imageEncoded);
                        Log.d("imageencodedlist","images in imageencodedlist" +uri+ imagesEncodedList);
                        cursor.close();

                        selectedImageUri = uri;

                        String picturePath = getPathFromURI(uri);

                        try {
                            MoveFile(picturePath, Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyAppPicture/"+getFileName(selectedImageUri));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());

                }

            }

        }
        loadAllimages();

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void MoveFile(String path_source, String path_destination) throws IOException {
        File file_Source = new File(path_source);
        File file_Destination = new File(path_destination);

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(file_Source).getChannel();
            destination = new FileOutputStream(file_Destination).getChannel();

            long count = 0;
            long size = source.size();
            while((count += destination.transferFrom(source, count, size-count))<size);
            file_Source.delete();
        }

        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    @SuppressLint("ObsoleteSdkInt")
    public String getPathFromURI(Uri uri){
        String realPath="";
// SDK < API11
        if (Build.VERSION.SDK_INT < 11) {
            Log.d("api level if 1",Build.VERSION.SDK_INT+" here");

            String[] proj = { MediaStore.Images.Media.DATA };
            @SuppressLint("Recycle") Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
            int column_index = 0;
            String result="";
            if (cursor != null) {
                column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                realPath=cursor.getString(column_index);
            }
        }
        // SDK >= 11 && SDK < 19
        else if (Build.VERSION.SDK_INT < 19){
            Log.d("api level else 2",Build.VERSION.SDK_INT+" here");

            String[] proj = { MediaStore.Images.Media.DATA };
            CursorLoader cursorLoader = new CursorLoader(this, uri, proj, null, null, null);
            Cursor cursor = cursorLoader.loadInBackground();
            if(cursor != null){
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                realPath = cursor.getString(column_index);
            }
        }
        // SDK > 19 (Android 4.4)
        else{
            Log.d("api level else 3",Build.VERSION.SDK_INT+" here");

            String wholeID = DocumentsContract.getDocumentId(uri);
            // Split at colon, use second item in the array
            String id = wholeID.split(":")[1];
            String[] column = { MediaStore.Images.Media.DATA };
            // where id is equal to
            String sel = MediaStore.Images.Media._ID + "=?";
            Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, new String[]{ id }, null);
            int columnIndex = 0;
            if (cursor != null) {
                columnIndex = cursor.getColumnIndex(column[0]);
                if (cursor.moveToFirst()) {
                    realPath = cursor.getString(columnIndex);
                }
                cursor.close();
            }
        }
        return realPath;
    }

    public void loadAllimages(){

        String[] filenames = new String[0];
        path = new File(Environment.getExternalStorageDirectory() + "/MyAppPicture");
        if (path.exists()) {
            filenames = path.list();

            if (filenames.toString().isEmpty()){

                makeText(this, "nothing to show", Toast.LENGTH_SHORT).show();

            }
            else {


                for (int i=0;i<filenames.length;i++){
                    list.add(path.getPath()+"/"+filenames[i]);



                }



            }

//            Log.d("filename",filenames[0]);
//            Log.d("filename",filenames[0]+" lenght"+" ="+filenames.length);
            rec.setLayoutManager(new GridLayoutManager(this,2));
            recyclerViewAdapter = new My_Gallery_Adapter(this, list);
            rec.setAdapter(recyclerViewAdapter);

        }
        else{
            makeText(getApplicationContext(), "Nothing to show", LENGTH_LONG).show();
        }



    }


}
