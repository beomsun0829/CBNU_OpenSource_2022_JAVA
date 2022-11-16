package com.example.practice;

public class ListViewItem {
    private int iconDrawable ;
    private String contentStr ;
    private String titleStr ;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setIcon(int icon) {
        iconDrawable = icon ;
    }
    public void setContent(String content) {
        contentStr = content ;
    }


    public int getIcon() {
        return this.iconDrawable ;
    }
    public String getContent() {
        return this.contentStr ;
    }
    public String getTitle() {
        return this.titleStr ;
    }

}