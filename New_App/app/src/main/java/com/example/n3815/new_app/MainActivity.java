package com.example.n3815.new_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.n3815.new_app.assembly.AssemblyListActivity;
import com.example.n3815.new_app.customList.CustomListActivity;
import com.example.n3815.new_app.list.ListActivity;

public class MainActivity extends AppCompatActivity {

    Button button;  // 버튼 객체 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 라이프 사이클 메소드 handler나, I/F 호출 가능하다.
        setContentView(R.layout.activity_main); // activity_main 메인 액티비티를 호출하라! → layout/activity_main.xml

        Button btn01 = (Button)findViewById(R.id.btn_web);  // 버튼 생성을 하십시오.
        Intent intent = new Intent(this, SubActivity.class);

        // 버튼 클릭시, 타 액티비티로 이동하기
        button = (Button) findViewById(R.id.btn_web);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });

        // 리스트뷰 액티비티로 이동하기
        button = (Button) findViewById(R.id.btn_list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        // 커스텀 리스트뷰 액티비티로 이동하기
        button = (Button) findViewById(R.id.btn_customList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomListActivity.class);
                startActivity(intent);
            }
        });

        // 국회의원 프로젝트 액티비티로 이동하기
        button = (Button) findViewById(R.id.btn_assembly);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AssemblyListActivity.class);
                startActivity(intent);
            }
        });
    }

}
