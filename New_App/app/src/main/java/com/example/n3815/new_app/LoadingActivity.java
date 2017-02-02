package com.example.n3815.new_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.n3815.new_app.assembly.AssemblyListActivity;
import com.example.n3815.new_app.assembly.AssemblyService;
import com.example.n3815.new_app.common.DefaultRestClient;
import com.example.n3815.new_app.common.SearchCommon;
import com.example.n3815.new_app.common.bean.AssemBean;
import com.example.n3815.new_app.common.bean.Items;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by N3815 on 2017-01-31.
 */

public class LoadingActivity extends Activity {

    ArrayList<AssemBean> assemblyList = new ArrayList<AssemBean>(); // 전체 국회의원의 정보를 담는 객체

    // API 호출을 위한 Client
    DefaultRestClient<AssemblyService> restClient;
    // 국회의원 REST API URL SERVICE
    AssemblyService assemblyService;

    boolean isCall = false;	// 호출이 되었는지 확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        try {
            if(!isCall) {
                // 전체 국회의원 리스트 가져오기
                AssemblyList();
            }
        } catch (IOException e) {
            // 다시 요청시도 하기
            Toast.makeText(LoadingActivity.this ,"[Loading] Error(10003-요청 오류)",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /**
     * 국회의원 목록 가져오기 I/F
     */
    public void AssemblyList() throws IOException {
        isCall = true;

        restClient = new DefaultRestClient<>();
        assemblyService = restClient.getAssemblyClient(AssemblyService.class);

        Call<Items> call = assemblyService.getMemberCurrStateList(1, 300);
        call.enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                try{
                    if(response.isSuccessful()){
                        // 전체 대상자 추가하기
                        for(AssemBean userInfo : response.body().getItems()){
                            // 초성정보 객체에 미리 넣어주기
                            userInfo.setInitialEmpNm(SearchCommon.getInitialSound(userInfo.getEmpNm().charAt(0)));
                            assemblyList.add(userInfo);
                        }
                        Log.v("[AssemblyListActivity]","[API 호출 성공]: "+assemblyList.size());
                        Log.v("[AssemblyListActivity]","[Adapter에 전달]: 모든 국회의원 정보를 조회 완료하였습니다.");

                        if(assemblyList.size() == 300){
                            Intent intent = new Intent(LoadingActivity.this, AssemblyListActivity.class);
                            intent.putExtra("assem", assemblyList);
                            startActivity(intent);
                        }
                    }
                } catch (Exception e){
                    Toast.makeText(LoadingActivity.this ,"[Loading] Error(10000-파싱 오류)",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Items> call, Throwable throwable) {
                if(throwable instanceof SocketTimeoutException){
                    // 다시 요청시도 하기
                    Toast.makeText(LoadingActivity.this ,"서버가 불안정합니다. 다시 시도해주세요.",Toast.LENGTH_LONG).show();
                }else{
                    // 다시 요청시도 하기
                    Toast.makeText(LoadingActivity.this ,"[Loading] Error(10001-요청 오류)",Toast.LENGTH_LONG).show();
                }
                throwable.printStackTrace();
            }
        });
    }

    /**
     * App이 재시작될 때 호출되는 함수로, onStart() 직전에 호출
     */
    @Override
    public void onRestart(){
        Log.v("[AssemblyListActivity]","---------[onRestart]---------");
        super.onRestart();
    }

    /**
     * 액티비티가 사용자에게 보이기 직전에 호출
     */
    @Override
    public void onStart(){
        Log.v("[AssemblyListActivity]","---------[onStart]---------");
        super.onStart();
    }

    /**
     * 사용자와 상호작용을 하기 전에 호출되는 함수
     * Activit 스택의 맨 위에 있어서 Activity가 사용자에게 보여지고, 사용자의 입력을 처리할 수 있을 때 호출
     */
    @Override
    public void onResume(){
        Log.v("[AssemblyListActivity]","---------[onResume]---------");
        super.onResume();
    }

    /**
     * 다른 Activity가 호출될 때 호출된다.
     * 데이터 저장 및 스레드 중지 등의 처리를 하기에 좋은 메소드
     */
    @Override
    public void onPause(){
        Log.v("[AssemblyListActivity]","---------[onPause]---------");
        super.onPause();
    }

    /**
     * 더이상 Activity가 스택의 최상 위에 있지 않을 때 즉, 사용자에게 보이지 않게 될 때 호출
     * 메모리가 부족할 경우, onStop() 메소드가 호출되지 않을 수도 있다.
     */
    @Override
    public void onStop(){
        Log.v("[AssemblyListActivity]","---------[onStop]---------");
        finish();
        super.onStop();
    }

    /**
     * Activity를 종료/소멸될 때 호출
     * finish() 메소드가 호출되거나 시스템이 메모리 확보를 위해서 액티비티 제거시 호출됨
     */
    public void onDestory(){
        Log.v("[AssemblyListActivity]","---------[onDestory]---------");
        finish();
        super.onDestroy();
    }

}
