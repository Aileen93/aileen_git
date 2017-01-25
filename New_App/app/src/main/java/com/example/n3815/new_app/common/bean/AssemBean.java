package com.example.n3815.new_app.common.bean;

import android.graphics.drawable.Drawable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by N3815 on 2016-12-27.
 * 국회의원 기본 정보 pojo 객체
 */
@Root(name = "item")
public class AssemBean {

    @Element(name = "deptCd", required = false)
    private String deptCd;  // 부서코드

    @Element(name="num", required = false)
    private String num;     // 식별코드

    @Element(name="empNm", required = false)
    private String empNm;   // 한글이름

    @Element(name="engNm", required = false)
    private String engNm;   // 영문이름

    @Element(name="hjNm", required = false)
    private String hjNm;    // 한자이름

    @Element(name="reeleGbnNm", required = false)
    private String reeleGbnNm; // 당선횟수

    @Element(name="origNm", required = false)
    private String origNm;      // 선거구

    @Element(name="jpgLink",required = false)
    private String jpgLink;      // 프로필 사진 링크

    private Drawable img_profile;   // 프로필 사진

    private boolean favorite = false; // 즐겨찾기 등록여부

    public String getDeptCd(){
        return this.deptCd;
    }
    public void setDeptCd(String deptCd){
        this.deptCd = deptCd;
    }

    public String getNum(){
        return this.num;
    }
    public void setNum(String num){
        this.num = num;
    }

    public String getEmpNm(){
        return this.empNm;
    }
    public void setEmpNm(String empNm){
        this.empNm = empNm;
    }

    public String getEngNm(){
        return this.engNm;
    }
    public void setEngNm(String engNm){
        this.engNm = engNm;
    }

    public String getHjNm(){
        return this.hjNm;
    }
    public void setHjNm(String hjNm){
        this.hjNm = hjNm;
    }

    public String getReeleGbnNm(){
        return this.reeleGbnNm;
    }
    public void setReeleGbnNm(String reeleGbnNm){
        this.reeleGbnNm = reeleGbnNm;
    }

    public String getOrigNm(){
        return this.origNm;
    }
    public void setOrigNm(String origNm){
        this.origNm = origNm;
    }

    public String getJpgLink(){
        return this.jpgLink;
    }
    public void setJpgLink(String jpgLink){
        this.jpgLink = jpgLink;
    }

    /**
     * 아이콘 - img_icon
     * @return
     */
    public Drawable getImg_profile(){
        return this.img_profile;
    }
    public void setImg_profile(Drawable img_profile){
        this.img_profile = img_profile;
    }

    /**
     * 즐겨찾기 설정 여부
     * @return
     */
    public boolean getFavorite() { return this.favorite; }
    public void setFavorite( boolean favorite ){this.favorite = favorite; }
}
