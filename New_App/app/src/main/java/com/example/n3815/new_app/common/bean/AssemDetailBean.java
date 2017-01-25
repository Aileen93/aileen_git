package com.example.n3815.new_app.common.bean;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by N3815 on 2017-01-04.
 */
@Root(name = "item")
public class AssemDetailBean {

    @Element(name="header", required = false)
    private String header;

        @Element(name="resultCode", required = false)
        private String ResultCode;

        @Element(name="resultMsg", required = false)
        private String ResultMsg;

    @Element(name="body", required = false)
    private String body;

    // -----------------------------------------------------------------------------

    @Element(name="item", required = false)
    private String item;   // Root

    @Element(name="empNm", required = false)
    private String empNm;   // 한글이름

    @Element(name="hjNm", required = false)
    private String hjNm;    // 한자이름

    @Element(name="engNm", required = false)
    private String engNm;   // 영문이름

    @Element(name="bthDate", required = false)
    private String bthDate;   // 생년월일

    @Element(name="polyNm", required = false)
    private String polyNm;   // 소속정당

    @Element(name="origNm", required = false)
    private String origNm;      // 선거구

    @Element(name="sharNm", required = false)
    private String sharNm;      // 소속위원회

    @Element(name="reeleGbnNm", required = false)
    private String reeleGbnNm; // 당선횟수

    @Element(name="electionNum", required = false)
    private String electionNum; // 당선대수

    @Element(name="assemTel", required = false)
    private String assemTel; // 사무실 전화

    @Element(name="assemHomep", required = false)
    private String assemHomep; // 홈페이지

    @Element(name="assemEmail", required = false)
    private String assemEmail; // 이메일

    @Element(name="staff", required = false)
    private String staff; // 보좌관

    @Element(name="secretary2", required = false)
    private String secretary2; // 비서관

    @Element(name="secretary", required = false)
    private String secretary; // 비서

    @Element(name="hbbyCd", required = false)
    private String hbbyCd; // 취미

    @Element(name="examCd", required = false)
    private String examCd; // 특기

    @Element(name="memTitle", required = false)
    private String memTitle; // 약력

    @Element(name="shrtNm", required = false)
    private String shrtNm; // 직책?

    public String getEmpNm(){
        return this.empNm;
    }
    public void setEmpNm(String empNm){
        this.empNm = empNm;
    }

    public String getHjNm(){
        return this.hjNm;
    }
    public void setHjNm(String hjNm){
        this.hjNm = hjNm;
    }

    public String getEngNm(){
        return this.engNm;
    }
    public void setEngNm(String engNm){
        this.engNm = engNm;
    }

    public String getBthDate(){
        return this.bthDate;
    }
    public void setBthDate(String bthDate){
        this.bthDate = bthDate;
    }

    public String getPolyNm(){
        return this.polyNm;
    }
    public void setPolyNm(String polyNm){
        this.polyNm = polyNm;
    }

    public String getOrigNm(){
        return this.origNm;
    }
    public void setOrigNm(String origNm){
        this.origNm = origNm;
    }

    public String getSharNm(){
        return this.sharNm;
    }
    public void setSharNm(String sharNm){
        this.sharNm = sharNm;
    }

    public String getReeleGbnNm(){
        return this.reeleGbnNm;
    }
    public void setReeleGbnNm(String reeleGbnNm){
        this.reeleGbnNm = reeleGbnNm;
    }

    public String getAssemTel(){
        return this.assemTel;
    }
    public void setAssemTel(String assemTel){
        this.assemTel = assemTel;
    }
    public String getAssemHomep(){
        return this.assemHomep;
    }
    public void setAssemHomep(String assemHomep){
        this.assemHomep = assemHomep;
    }

    public String getAssemEmail(){
        return this.assemEmail;
    }
    public void setAssemEmail(String assemEmail){
        this.assemEmail = assemEmail;
    }

    public String getStaff(){
        return this.staff;
    }
    public void setStaff(String staff){
        this.staff = staff;
    }

    public String getSecretary2(){
        return this.secretary2;
    }
    public void setSecretary2(String secretary2){
        this.secretary2 = secretary2;
    }

    public String getSecretary(){
        return this.secretary;
    }
    public void setSecretary(String secretary){
        this.secretary = secretary;
    }

    public String getHbbyCd(){
        return this.hbbyCd;
    }
    public void setHbbyCd(String hbbyCd){
        this.hbbyCd = hbbyCd;
    }

    public String getExamCd(){
        return this.examCd;
    }
    public void setExamCd(String examCd){
        this.examCd = examCd;
    }

    public String getMemTitle(){
        return this.memTitle;
    }
    public void setMemTitle(String memTitle){
        this.memTitle = memTitle;
    }

    public String getElectionNum(){
        return this.electionNum;
    }
    public void setElectionNum(String electionNum){
        this.electionNum = electionNum;
    }

}
