package com.example.n3815.new_app.assembly;

import com.example.n3815.new_app.common.bean.AssemDetailBean;
import com.example.n3815.new_app.common.bean.Items;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by N3815 on 2016-12-26.
 */

public interface AssemblyService {

    public final static String serviceKey = "q1p5TosEhgURKtNouuTaxt4aOX0yu55kVUa%2FZIizv2tdjIG5WARFe7ET02PNGyPThekktyx12SZ9GBSjkwti8A%3D%3D";

    /**
     * 현재 국회의원 리스트를 조회하는 기능 제공
     *
     * @param pageNum -- 페이지 번호
     * @param numOfRows -- 가져올 개수
     * @return
     */
    @GET("getMemberCurrStateList?serviceKey="+serviceKey)
    Call<Items> getMemberCurrStateList(
            @Query("pageNo") int pageNum    // 페이지
            , @Query("numOfRows") int numOfRows // 페이지당 가져올 개수
    );
    // http://apis.data.go.kr/9710000/NationalAssemblyInfoService/getMemberCurrStateList?serviceKey=q1p5TosEhgURKtNouuTaxt4aOX0yu55kVUa%2FZIizv2tdjIG5WARFe7ET02PNGyPThekktyx12SZ9GBSjkwti8A%3D%3D&numOfrows=300

    /**
     * 국회의원의 상세 정보를 보여주는 기능 제공
     *
     * @param numOfRows -- 한 페이지 결과수
     * @param pageNo -- 페이지 번호
     * @param dept_cd -- 부서코드
     * @param num -- 국회의원 식별코드
     * @return
     */
    @GET("getMemberDetailInfoList?serviceKey="+serviceKey)
    Call<AssemDetailBean> getMemberDetailInfoList(
            @Query("numOfRows") int numOfRows,
            @Query("pageNo") int pageNo,
            @Query("dept_cd") String dept_cd,
            @Query("num") String num
    );
    // http://apis.data.go.kr/9710000/NationalAssemblyInfoService/getMemberDetailInfoList?dept_cd=9770276&num=153&ServiceKey=q1p5TosEhgURKtNouuTaxt4aOX0yu55kVUa%2FZIizv2tdjIG5WARFe7ET02PNGyPThekktyx12SZ9GBSjkwti8A%3D%3D&page=1&numOfRows=20

}
