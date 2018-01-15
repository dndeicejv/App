package com.example.app;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.zip.DataFormatException;

public class NewoneActivity extends AppCompatActivity {
    private final static  int PHOTO = 99;
    private final static int CAMERA = 66 ;
    private DisplayMetrics mPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPhone = new DisplayMetrics();
        final  Insert is = (Insert) getApplicationContext();

        setContentView(R.layout.activity_newone);
        ConnectionClass connectionClass = new ConnectionClass();
        Button back = (Button)findViewById(R.id.quit);
        Button image = (Button)findViewById(R.id.imgbtn);
        Button photo = (Button)findViewById(R.id.photobtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewoneActivity.this.finish();
            }
        });

        Button next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  Insert is = (Insert) getApplicationContext();
                Function f = new Function();
                EditText msg = (EditText)findViewById(R.id.msg);
                is.setId(f.getid());
                is.setDate(f.getdate());
                is.setDay(f.getday());
                is.setMsg(msg.getText().toString());


                new AlertDialog.Builder(NewoneActivity.this)
                        .setTitle("送出資料")
                        .setMessage("確定資料沒問題，要送出嗎?")
                      .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Function f = new Function();
                                f.insertsql(is.getId(),is.getDate(),is.getDay(),is.getUser(),is.getFloor(),is.getTime(),is.getPlace(),is.getClasss(),is.getMsg());
                                NewoneActivity.this.finish();
                            }
                        })

                        .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();

            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent();
             intent.setType("image/*");
             intent.setAction(Intent.ACTION_GET_CONTENT);
             startActivityForResult(intent,PHOTO);
            }
        });
       photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues value = new ContentValues();
                value.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");	//讀取影像類型
               // Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value);	//抓取SD卡儲存位置
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");	//開啟相機
               // intent.putExtra(MediaStore.EXTRA_OUTPUT, uri.getPath());			//將照片儲存至SD卡
                startActivity(intent);
            }
        });


    }
}
