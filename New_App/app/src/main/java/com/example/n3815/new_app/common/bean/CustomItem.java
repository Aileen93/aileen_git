package com.example.n3815.new_app.common.bean;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by N3815 on 2016-12-12.
 */

public class CustomItem {
    private Drawable drawIcon;
    private String strTitle;
    private String strDesc;

    public CustomItem() {
    }

    /**
     * 아이콘 - img_icon
     * @return
     */
    public Drawable getIcon(){
        return this.drawIcon;
    }
    public void setIcon(Drawable icon){
        this.drawIcon = icon;
    }

    /**
     * 제목 - txt_title
     * @return
     */
    public String getTitle(){
        return this.strTitle;
    }
    public void setTitle(String title){
        this.strTitle = title;
    }

    /**
     * 내용 - txt_desc
     * @return
     */
    public String getDesc(){
        return this.strDesc;
    }
    public void setDesc(String desc){
        this.strDesc = desc;
    }
}
