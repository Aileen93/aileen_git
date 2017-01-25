package com.example.n3815.new_app.common.bean;

import org.simpleframework.xml.Element;

/**
 * Created by N3815 on 2017-01-05.
 */

public class NaverSearch {

    @Element(name="rss", required = false)
    public String rss;

    @Element(name="version", required = false)
    public String version;

    @Element(name="channel", required = false)
    public String channel;

    @Element(name="title", required = false)
    public String title;

    @Element(name="link", required = false)
    public String link;

    @Element(name="description", required = false)
    public String description;

    @Element(name="lastBuildDate", required = false)
    public String lastBuildDate;

    @Element(name="total", required = false)
    public String total;

    @Element(name="start", required = false)
    public String start;

    @Element(name="display", required = false)
    public String display;

    @Element(name="item", required = false)
    public String item;

    public String getVersion(){
        return this.version;
    }
    public void setVersion(String version){
        this.version = version;
    }

    public String getRss(){
        return this.rss;
    }
    public void setRss(String rss){
        this.rss = rss;
    }


}
