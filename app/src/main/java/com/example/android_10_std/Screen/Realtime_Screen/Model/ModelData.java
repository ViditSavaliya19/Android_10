package com.example.android_10_std.Screen.Realtime_Screen.Model;

public class ModelData {
    String name, grid, mobile,key;


    public ModelData(String name, String grid, String mobile, String key) {
        this.name = name;
        this.grid = grid;
        this.mobile = mobile;
        this.key = key;
    }

    public ModelData(String name, String grid, String mobile) {
        this.name = name;
        this.grid = grid;
        this.mobile = mobile;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
