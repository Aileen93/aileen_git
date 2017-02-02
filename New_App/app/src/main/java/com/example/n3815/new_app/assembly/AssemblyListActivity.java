package com.example.n3815.new_app.assembly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.n3815.new_app.R;
import com.example.n3815.new_app.common.DefaultRestClient;
import com.example.n3815.new_app.common.bean.AssemBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/**
 * LoadingActivity 호출 후에 실행되는 MainActivity입니다.
 * Created by N3815 on 2016-12-19.
 */
public class AssemblyListActivity extends Activity {

    ListView listview; // listView 객체
    EditText search; // 국회의원 검색을 하기 위한 Text 객체
    AssemblyListAdapter adapter; // 아이템의 상세 정보를 셋팅해줄 Adapter

    public static boolean isSearch = false;

    private ArrayList<AssemBean> assemblyList = new ArrayList<AssemBean>(); // 전체 국회의원의 정보를 담는 객체
    private ArrayList<AssemBean> searchList = new ArrayList<AssemBean>(); // 검색된 국회의원 리스트

    private ArrayList<AssemBean> temp = new ArrayList<AssemBean>();

    // API 호출을 위한 Client
    DefaultRestClient<AssemblyService> restClient;
    // 국회의원 REST API URL SERVICE
    AssemblyService assemblyService;

    // 데이터 정렬을 위한 Comparator
    public static Comparator<AssemBean> cmpAsc = new Comparator<AssemBean>() {
        @Override
        public int compare(AssemBean o1, AssemBean o2) {
            return o1.getEmpNm().compareTo(o2.getEmpNm()) ;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Todo - 안드로이드가 메모리가 부족할경우, onCreate가 kill 당했다가 다시 불러와 질 수 있다.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assem_list);

        // custom adapter 생성
        adapter = new AssemblyListAdapter();
        listview = (ListView) findViewById(R.id.assembly_listView);

        // 해당 어뎁터 설정
        listview.setAdapter(adapter);

        // intent 받아온 Array 정보 가져오기
        ArrayList<AssemBean> ch = (ArrayList<AssemBean>) getIntent().getSerializableExtra("assem");
        if(ch != null && assemblyList.size() != 300) {
            assemblyList.addAll(ch);
            searchList.addAll(ch);
            adapter.addItem(assemblyList);
        }

        // 기능1) 아이템 클릭시 상세 화면 호출하기
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setFocusable(false);

                Intent intent = new Intent(AssemblyListActivity.this, AssemblyDetailActivity.class);
                Log.v("[AssemblyListActivity]","====== 상세화면 이동 :"+searchList.get(position).getDeptCd());
                intent.putExtra("numOfRows",20); // 한페이지 결과수
                intent.putExtra("dept_cd",searchList.get(position).getDeptCd()); // 부서코드
                intent.putExtra("num",searchList.get(position).getNum()); // 국회의원 번호
                intent.putExtra("jpgLink",searchList.get(position).getJpgLink()); // 사진
                intent.putExtra("empNm",searchList.get(position).getEmpNm()); // 국회의원 이름
                startActivity(intent);
            }
        });

        // 기능2) 국회의원 검색하기
        search = (EditText)findViewById(R.id.searchValue);
        search.addTextChangedListener(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때
                String text = s.toString().toLowerCase(Locale.getDefault());

                // 검색결과 초기화
                searchList.clear();
                Log.v("[AssemblyListActivity]","[검색시작] 전체 대상자 :"+assemblyList.size()+", (검색어 :"+text+")＊＊＊");

                if (text.length() == 0) {
                    // 검색된 글자가 하나도 없을 경우, 전체 리스트를 넣어주기
                    searchList.addAll(assemblyList);
                    isSearch = false;
                } else {
                    // 검색된 글자가 있을 경우, 검색어와 일치하는 국회의원 정보를 담아주기
                    for(AssemBean u : assemblyList){
                        String name = u.getEmpNm();  // 국회의원 이름
                        if (name.toLowerCase().contains(text)) {
                            Log.v("[AssemblyListActivity]","[검색결과] : "+name);
                            searchList.add(u);
                        }
                    }
                    isSearch = true;
                }

                // searchList에 담아준뒤, 어뎁터에 전달
                adapter.addItem(searchList);
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에
            }
        });
    }

    /**
     * 대상 배열을 재정렬해줍니다.
     * @param targetArray   -- 정렬할 배열
     */
    public void orderByAssemList(ArrayList<AssemBean> targetArray){

        // 전체 정렬
        Collections.sort(targetArray, cmpAsc);

        // Adapter에 전달
        adapter.addItem(targetArray);
    }

    /**
     * Adapter에서 변경된 국회의원 정보를 갱신해준다.
     * @param newAssemInfo -- 변경된 새로운 국회의원 정보
     */
    public void changeItemInfo(AssemBean newAssemInfo){
        Log.v("[AssemblyListActivity]","[changeItemInfo] newAssemInfo :"+newAssemInfo.getEmpNm());

        // 전체 검색 배열에 적용
        for(AssemBean u : assemblyList){
            if(u.getDeptCd().equals(newAssemInfo.getDeptCd())){
                u.setFavorite(newAssemInfo.getFavorite());
            }
        }

        // 검색 결과 배열에 적용
        for(AssemBean u : searchList){
            if(u.getDeptCd().equals(newAssemInfo.getDeptCd())){
                u.setFavorite(newAssemInfo.getFavorite());
            }
        }

        // searchList에 담아준뒤, 어뎁터에 전달
        orderByAssemList(searchList);
    }
}
