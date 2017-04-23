package com.roushan.assignment2;

public class DataModel {
    String text1, text2, text3;
    int img_left, img_center, img_right;

    public DataModel(String t1, String t2, String t3, int il, int ic, int ir) {
        this.text1 = t1;
        this.text2 = t2;
        this.text3 = t3;

        this.img_left = il;
        this.img_center = ic;
        this.img_right = ir;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public int getImg_left() {
        return img_left;
    }

    public void setImg_left(int img_left) {
        this.img_left = img_left;
    }

    public int getImg_center() {
        return img_center;
    }

    public void setImg_center(int img_center) {
        this.img_center = img_center;
    }

    public int getImg_right() {
        return img_right;
    }

    public void setImg_right(int img_right) {
        this.img_right = img_right;
    }
}
