package com.ithaha.systemwidget.floorview;

/**
 * Created by Administrator on 2016/1/15.
 */
public class Comment implements Commentable, Comparable {

    private String name;

    private String content;

    private int floorNum = 1;

    public Comment(String name, String content, int floorNum) {
        this.name = name;
        this.content = content;
        this.floorNum = floorNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    @Override
    public int getCommentFloorNum() {
        return getFloorNum();
    }

    @Override
    public String getCommentContent() {
        return getContent();
    }

    @Override
    public String getAuthorName() {
        return getName();
    }

    @Override
    public int compareTo(Object another) {
        Comment anotherComment = (Comment) another;
        return getFloorNum() - anotherComment.getFloorNum();
    }
}
