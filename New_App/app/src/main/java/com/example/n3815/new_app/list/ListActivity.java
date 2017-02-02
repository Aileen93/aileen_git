package com.example.n3815.new_app.list;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.n3815.new_app.LoadingActivity;
import com.example.n3815.new_app.R;

import java.util.ArrayList;

/**
 * Created by N3815 on 2016-12-08.
 */

public class ListActivity extends LoadingActivity {

    private ArrayAdapter adapter;

    // 1. 액티비티 생성
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // 빈 리스트 객체 생성
        final ArrayList<String> item = new ArrayList<String>();

        // arrayDapter 생성하여 아이템 view를 선택할 수 있게 만들기
        // simple_list_item_single_choice : 라디오 버튼이 있는 텍스트 리스트 뷰
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, item);

        // listView를 뿌려줄 list 객체 설정
        final ListView listview = (ListView) findViewById(R.id.list_contents);

        // listView 객체에 생성한 adapter를 연결시켜준다!!!!!
        // 이거 안해주면 화면에 안보임
        listview.setAdapter(adapter);
        // ------------------------------------------------------------------------------------------- step 1

        // 리스트 추가하기 버튼
        Button addButton = (Button)findViewById(R.id.btn_add);
        addButton.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {
                int count;
                count = adapter.getCount();

                // 아이템 추가
                item.add("LIST"+Integer.toString(count+1));

                // listView 갱신
                adapter.notifyDataSetChanged();
            }
        });

        // 리스트 수정하기 버튼
        Button modifyButton = (Button)findViewById(R.id.btn_modify);
        modifyButton.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                int count,checked;
                count = adapter.getCount();

                if(count>0){
                    // 선택한 아이템 값 가져오기
                    checked = listview.getCheckedItemPosition();
                    if(checked > -1 && checked < count){

                        // 아이템 수정
                        item.set(checked, Integer.toString(checked+1)+"(수정)");

                        // listView 갱신
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        // 리스트 삭제하기 버튼
        Button delButton = (Button)findViewById(R.id.btn_del);
        delButton.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v){
                int count, checked;
                count = adapter.getCount();

                if(count > 0){
                    // 선택한 아이템 값 가져오기
                    checked = listview.getCheckedItemPosition();

                    if(checked > -1 && checked < count){

                        // item에서 삭제
                        item.remove(checked);
                        // 선택 초기화
                        listview.clearChoices();
                        // listView 갱신
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        // ------------------------------------------------------------------------------------------- step 2
    }

}
