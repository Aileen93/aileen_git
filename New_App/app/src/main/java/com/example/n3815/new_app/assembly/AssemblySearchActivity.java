package com.example.n3815.new_app.assembly;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.n3815.new_app.R;
import com.example.n3815.new_app.common.DefaultRestClient;
import com.example.n3815.new_app.common.SearchService;
import com.example.n3815.new_app.common.bean.AssemDetailBean;
import com.example.n3815.new_app.common.bean.BlogInfo;
import com.example.n3815.new_app.common.bean.NaverSearch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by N3815 on 2017-01-05.
 */

public class AssemblySearchActivity extends AssemblyDetailActivity{

    DefaultRestClient<SearchService> searchClient;  // restAPI
    SearchService searchService;    // Url service

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assem_search);

        // 넘어온 변수 받아오기
        Intent intent = getIntent();
        String empNm = intent.getStringExtra("empNm");

        // 연관 검색어 가져오기
        AssemblySearch(empNm);
    }

    /**
     * 블로그 검색 결과를 보여줍니다.
     * @param searchKey
     */
    public void AssemblySearch(
        String searchKey
    ){
        searchClient = new DefaultRestClient<>();
        searchService = searchClient.getSearchBlogAPIClient(SearchService.class);

        Call<NaverSearch> call = searchService.searchBlogAPI("123");
        call.enqueue(new Callback<NaverSearch>() {
            @Override
            public void onResponse(Call<NaverSearch> call, Response<NaverSearch> response) {
                try {
                    if (response.isSuccessful()) {
                        Log.v("===================성공적인 호출","호추룰룰");

                        //for(BlogInfo blogInfo : response.body().item()){

                        //}

                       /* // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 데이터 획득
                        TextView empNm = (TextView) findViewById(R.id.as_empNm); // 의원이름
                        TextView birth = (TextView) findViewById(R.id.as_birth); // 의원 생일
                        TextView origNm = (TextView) findViewById(R.id.as_origNm); // 선거구
                        TextView polyNm = (TextView) findViewById(R.id.as_polyNm); // 소속정당
                        TextView memTitle = (TextView) findViewById(R.id.as_memTitle); // 약력

                        memTitle.setMovementMethod(new ScrollingMovementMethod());  // 스크롤 가능하도록 추가
                        empNm.setText(response.body().getEmpNm());
                        birth.setText(response.body().getBthDate());
                        origNm.setText(response.body().getOrigNm());
                        polyNm.setText(response.body().getPolyNm());
                        memTitle.setText(response.body().getMemTitle());*/
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NaverSearch> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

}
