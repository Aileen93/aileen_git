package com.example.n3815.new_app.common.bean;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by N3815 on 2016-12-28.
 * 국회의원 리스트를 가져오기위한 기본 bean
 */
public class Items {

    @Element(name="cmmMsgHeader", required = false)
    private String cmmMsgHeader;

    @Element(name="errMsg", required = false)
    private String errMsg;

    @Element(name="header", required = false)
    private String header;

        @Element(name="resultCode", required = false)
        private String ResultCode;

        @Element(name="resultMsg", required = false)
        private String ResultMsg;

    @Element(name="body", required = false)
    private String body;

        @ElementList(name="items", required = false)
        private List<AssemBean> items;

        @Element(name="numOfRows", required = false)
        private String numOfRows;

        @Element(name="pageNo", required = false)
        private String pageNo;

        @Element(name="totalCount", required = false)
        private String totalCount;

    public List<AssemBean> getItems(){return this.items;}
    public void setItems(List<AssemBean> items){this.items = items;}

    public String getNumOfRows(){return this.numOfRows;}
    public void setNumOfRows(String numOfRows){this.numOfRows = numOfRows;}

    public String getPageNo(){return this.pageNo;}
    public void setPageNo(String pageNo){this.pageNo = pageNo;}

    public String getTotalCount(){return this.totalCount;}
    public void setTotalCount(String totalCount){this.totalCount = totalCount;}



    public String getCmmMsgHeader(){
        return this.cmmMsgHeader;
    }
    public void setCmmMsgHeader(String cmmMsgHeader){
        this.cmmMsgHeader = cmmMsgHeader;
    }

    public String getErrMsg(){
        return this.errMsg;
    }
    public void setErrMsg(String errMsg){
        this.errMsg = errMsg;
    }

}
