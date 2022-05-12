package cn.oneplustow.mc.test.StringTest;

import lombok.Data;

import java.util.List;

/**
 * Author lwj
 * Date 2022/4/28
 * Description TODO
 * BVersion 1.0
 **/
public class A {
    private String name;
    private List<String> bName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getbName() {
        return bName;
    }

    public void setbName(List<String> bName) {
        this.bName = bName;
    }
}
