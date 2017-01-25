package com.example.n3815.new_app.customList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.n3815.new_app.MainActivity;
import com.example.n3815.new_app.R;
import com.example.n3815.new_app.common.bean.CustomItem;

import java.io.File;
import java.util.ArrayList;

/*
 * Created by N3815 on 2016-12-12.
 */
// mipmap - img-text.png, img_x.png 민둥아 img-text도 drawable에들어가면되지 네네네네
public class CustomListActivity extends MainActivity {

    ListView listview;
    CustomAdapter adapter;

    // CustomItem 빈 리스트 객체 생성
    final ArrayList<CustomItem> customView = new ArrayList<CustomItem>();

    // 1. 액티비티 생성
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        // adapter 생성
        adapter = new CustomAdapter();

        listview = (ListView) findViewById(R.id.custom_listview);
        listview.setAdapter(adapter);
        // -------------------------------------------------------------------------

        // 리스트 추가하기 버튼
        Button addButton = (Button)findViewById(R.id.btn_customAdd);
        addButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                adapter.addItem((BitmapDrawable)ContextCompat.getDrawable(CustomListActivity.this, R.drawable.img_x_web), "민정", "테스트");
                adapter.notifyDataSetChanged();
            }
        });
    }
}
