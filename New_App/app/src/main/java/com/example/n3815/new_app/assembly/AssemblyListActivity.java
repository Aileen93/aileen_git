package com.example.n3815.new_app.assembly;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.n3815.new_app.MainActivity;
import com.example.n3815.new_app.R;
import com.example.n3815.new_app.common.DefaultRestClient;
import com.example.n3815.new_app.common.bean.AssemBean;
import com.example.n3815.new_app.common.bean.Items;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by N3815 on 2016-12-19.
 */
public class AssemblyListActivity extends MainActivity{

    ListView listview; // listView 객체
    AssemblyListAdapter adapter; // 아이템의 상세 정보를 셋팅해줄 Adapter
    ArrayList<AssemBean> assemblyList = new ArrayList<AssemBean>(); // 전체 국회의원의 정보를 담는 객체

    EditText search; // 국회의원 검색을 하기 위한 Text 객체
    private ArrayList<AssemBean> searchList = new ArrayList<AssemBean>(); // 검색된 국회의원 리스트

    // API 호출을 위한 Client
    DefaultRestClient<AssemblyService> restClient;
    // 국회의원 REST API URL SERVICE
    AssemblyService assemblyService;

    boolean lastitemVisibleFlag = false;	//화면에 리스트의 마지막 아이템이 보여지는지 체크


    // 로딩화면에서 미리 호출해주기

    // 300개가 완료되면 onCreate 호출

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

        // 전체 국회의원 리스트 가져오기
        try {
            AssemblyList();
        } catch (IOException e) {
            // 다시 onCreate 호출하도록 수정
            e.printStackTrace();
        }

        // 기능1) 아이템 클릭시 상세 화면 호출하기
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setFocusable(false);

                Intent intent = new Intent(AssemblyListActivity.this, AssemblyDetailActivity.class);
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
                // 어뎁터에 전달
                searchList = adapter.filter(text, assemblyList);
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
     * 국회의원 목록 가져오기 I/F
     */
    public void AssemblyList(
    ) throws IOException {

        restClient = new DefaultRestClient<>();
        assemblyService = restClient.getAssemblyClient(AssemblyService.class);

        // 아이템을 추가하는 동안 중복 요청을 방지하기 위해 락을 걸어둡니다.
        // lastitemVisibleFlag = true;
        // setPage(page);  // 페이지 설정 - 페이징에 따른 호출 시, 사용하기 위해서

        Call<Items> call = assemblyService.getMemberCurrStateList(1, 300);
        call.enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                try{
                    if(response.isSuccessful()){
                        // 전체 대상자 추가하기
                        for(AssemBean userInfo : response.body().getItems()){
                            assemblyList.add(userInfo);
                        }
                        Log.v("[API 호출 성공]",":"+assemblyList.size());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    Log.v("[Adapter에 전달]",": 모든 국회의원 정보를 조회 완료하였습니다.");
                    // searchList = adapter.filter("", assemblyList);
                    adapter.notifyDataSetChanged();

                    // 다시 호출할 수 있도록 false 처리
                    // lastitemVisibleFlag = false;
                }
            }

            @Override
            public void onFailure(Call<Items> call, Throwable throwable) {
                Toast.makeText(AssemblyListActivity.this ,"Error",Toast.LENGTH_LONG).show();
                // throwable.printStackTrace();
            }
        });
    }
}
