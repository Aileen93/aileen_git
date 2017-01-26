package com.example.n3815.new_app.assembly;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.n3815.new_app.R;
import com.example.n3815.new_app.common.DefaultRestClient;
import com.example.n3815.new_app.common.bean.AssemDetailBean;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by N3815 on 2017-01-05.
 */

public class AssemblyDetailActivity extends AssemblyListActivity {

    Button button;  // 버튼 객체 생성

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assem_detail);

        // 넘어온 변수 받아오기
        Intent intent = getIntent();
        int numOfRows = intent.getIntExtra("numOfRows", 20);
        int pageNo = intent.getIntExtra("pageNo", 1);
        String dep_cd = intent.getStringExtra("dept_cd");
        String num = intent.getStringExtra("num");
        String jpgLink = intent.getStringExtra("jpgLink");

        final String empNm = intent.getStringExtra("empNm");

        try {
            // 상세정보 가져오기
            AssemblyDetailInfo(numOfRows,pageNo, dep_cd, num);
            // 프로필 사진
            ImageView imgView = (ImageView) findViewById(R.id.as_profile);
            Picasso.with(getApplicationContext()).load(jpgLink).into(imgView);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 연관 검색어 결과 페이지로 이동
        button = (Button) findViewById(R.id.as_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssemblyDetailActivity.this, AssemblySearchActivity.class);
                intent.putExtra("searchKey",empNm);// 검색어
                startActivity(intent);
            }
        });
    }

    /*
     * 국회의원 정보 가져오기
     */
    public void AssemblyDetailInfo(
        int numOfRows
        , int pageNo
        , String dep_cd
        , String num
    ) throws IOException {

        final AssemDetailBean assemInfo = new AssemDetailBean();

        restClient = new DefaultRestClient<>();
        assemblyService = restClient.getAssemblyClient(AssemblyService.class);

        Call<AssemDetailBean> call = assemblyService.getMemberDetailInfoList(numOfRows, pageNo, dep_cd, num);
        call.enqueue(new Callback<AssemDetailBean>() {
            @Override
            public void onResponse(Call<AssemDetailBean> call, Response<AssemDetailBean> response) {
                try {
                    if (response.isSuccessful()) {

                        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 데이터 획득
                        TextView empNm = (TextView) findViewById(R.id.as_empNm); // 의원이름
                        TextView birth = (TextView) findViewById(R.id.as_birth); // 의원 생일
                        TextView origNm = (TextView) findViewById(R.id.as_origNm); // 선거구
                        TextView polyNm = (TextView) findViewById(R.id.as_polyNm); // 소속정당
                        TextView memTitle = (TextView) findViewById(R.id.as_memTitle); // 약력

                        empNm.setText(response.body().getEmpNm());
                        birth.setText(response.body().getBthDate());
                        origNm.setText(response.body().getOrigNm());
                        polyNm.setText(response.body().getPolyNm());
                        memTitle.setText(response.body().getMemTitle());
                        memTitle.setMovementMethod(new ScrollingMovementMethod());  // 스크롤 가능하도록 추가
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AssemDetailBean> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
