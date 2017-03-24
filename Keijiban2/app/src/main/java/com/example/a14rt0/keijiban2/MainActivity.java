package com.example.a14rt0.keijiban2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import android.util.Log;

import com.example.a14rt0.keijiban2.RES;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    RealmResults<RES> query;
    RES res1;
    View v;
    EditText edt_comment,edt_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        //Realm.deleteRealm(config);
        realm = Realm.getInstance(config);
        Show_Last_Info(v);
        // 書き込み部分を表示
        Add_WriteBox();


    }
    public void onClickAction(View v){
        // push send_button
        // writing DB
        Add_Data(v);
        // show last data
        Show_Last_Info(v);
        //  書き込み部分を表示
        Add_WriteBox();
    }

    public void Add_Data(View v){
        edt_comment = (EditText) findViewById(R.id.edt_comment);
        edt_name = (EditText) findViewById(R.id.edt_name);
        //　　未入力の時はデータの更新だけする
        if(edt_comment .length() != 0){
          // 入力有の時

            query = realm.where(RES.class).findAll();
            realm.beginTransaction();

            // write
            res1 = realm.createObject(RES.class);
            String str_name=edt_name.getText().toString();
            res1.setName(str_name);
            res1.setResnum(String.valueOf(query.size()));//queryは０から始まる
            res1.setTime(getNowDate());
            String str_comment=edt_comment.getText().toString();
            res1.setComment(str_comment);
            realm.commitTransaction();


        }

    }
    public static String getNowDate(){
        final Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日' kk'時'mm'分'ss'秒'");
        return sdf.format(date);
    }
    public void Show_Last_Info(View v){
        query = realm.where(RES.class).findAll();
        setContentView(R.layout.activity_main);

        for(int i = 0; i < query.size(); i++){
            //  レス番号
            addTextView(String.valueOf(i));
            //  Time
            addTextView(query.get(i).getTime());
            //  Name
            addTextView(query.get(i).getName());
            //  Comment
            addTextView(query.get(i).getComment());
        }
    }
    public void addTextView(String message) {
        LinearLayout layout = (LinearLayout)this.findViewById(R.id.root_layout);
        // TextViewを作成してテキストを設定
        TextView tv = new TextView(this);
        tv.setText(message);
        layout.addView(tv);
    }
    public void Add_WriteBox(){
        LinearLayout layout = (LinearLayout)this.findViewById(R.id.root_layout);
        View view = getLayoutInflater().inflate(R.layout.write_box, null);
        layout.addView(view);
        //setContentView(layout);
    }


}
