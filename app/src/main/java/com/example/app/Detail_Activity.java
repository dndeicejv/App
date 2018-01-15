package com.example.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Detail_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        final  Insert is = (Insert) getApplicationContext();
        TextView id = (TextView)findViewById(R.id.ID);
        TextView date = (TextView)findViewById(R.id.DATE);
        TextView user = (TextView)findViewById(R.id.USER);
        TextView floor = (TextView)findViewById(R.id.FLOOR);
        TextView place = (TextView)findViewById(R.id.PLACE);
        TextView event = (TextView)findViewById(R.id.EVENT);
        TextView msg = (TextView)findViewById(R.id.MSG);
        ImageView image = (ImageView)findViewById(R.id.img);
        ConnectionClass  connectionClass = new ConnectionClass();
       try{
            Connection con =  connectionClass.CONN();
            String query  ="SELECT *  FROM Detail WHERE 編號 = '" +is.getIdp()+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                id.setText(rs.getString("編號"));
                date.setText(rs.getString("日期"));
                user.setText(rs.getString("輪值者編號"));
                floor.setText(rs.getString("樓層"));
                place.setText(rs.getString("位置"));
                event.setText((rs.getString("分類")));
                msg.setText(rs.getString("備註"));
                byte[] decodedBytes = Base64.decode(rs.getBytes("圖片"), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes , 0, decodedBytes.length);
                image.setImageBitmap(bitmap);
            }

        }catch(Exception e){

        }
    }
}
