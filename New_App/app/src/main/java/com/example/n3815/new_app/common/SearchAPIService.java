package com.example.n3815.new_app.common;

import com.example.n3815.new_app.common.bean.Items;
import com.example.n3815.new_app.common.bean.NaverSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by N3815 on 2017-01-05.
 */

public interface SearchAPIService {

    // https://openapi.naver.com/v1/ search/blog?query=

    /**
     * 네이버 블로그의 검색 결과를 보여줍니다.
     *
     * @param query -- 검색키
     * @return
     *  xml형식의 블로그 검색결과
     */
    @GET("search/blog.xml")
    Call<NaverSearch> searchBlogAPI(
            @Query("query") String query    // utf-8로 인코딩된 검색키
    );

}
