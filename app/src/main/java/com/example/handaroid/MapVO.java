package com.example.handaroid;

public class MapVO {
    private String Map_name;
    private String Map_date;
    private String Map_context1;
    private String Map_context2;
    private String Map_context3;
    private String Map_context4;
    private String Map_context5;
    private String Map_context6;
    private String Map_context7;


    //main클래스에서 데이터를 넘겨줄 수 있는 생성자메소드 생성
    public MapVO(String Map_name, String Map_date, String Map_context1,String Map_context2,String Map_context3,String Map_context4,String Map_context5,String Map_context6,String Map_context7){
        this.Map_name = Map_name;
        this.Map_date = Map_date;
        this.Map_context1 = Map_context1;
        this.Map_context2 = Map_context2;
        this.Map_context3 = Map_context3;
        this.Map_context4 = Map_context4;
        this.Map_context5 = Map_context5;
        this.Map_context6 = Map_context6;
        this.Map_context7 = Map_context7;

    }

    public String getMap_name() {
        return Map_name;
    }

    public void setMap_name(String map_name) {
        Map_name = map_name;
    }

    public String getMap_date() {
        return Map_date;
    }

    public void setMap_date(String map_date) {
        Map_date = map_date;
    }

    public String getMap_context1() {
        return Map_context1;
    }

    public void setMap_context1(String map_context1) {
        Map_context1 = map_context1;
    }

    public String getMap_context2() {
        return Map_context2;
    }

    public void setMap_context2(String map_context2) {
        Map_context2 = map_context2;
    }

    public String getMap_context3() {
        return Map_context3;
    }

    public void setMap_context3(String map_context3) {
        Map_context3 = map_context3;
    }

    public String getMap_context4() {
        return Map_context4;
    }

    public void setMap_context4(String map_context4) {
        Map_context4 = map_context4;
    }

    public String getMap_context5() {
        return Map_context5;
    }

    public void setMap_context5(String map_context5) {
        Map_context5 = map_context5;
    }

    public String getMap_context6() {
        return Map_context6;
    }

    public void setMap_context6(String map_context6) {
        Map_context6 = map_context6;
    }

    public String getMap_context7() {
        return Map_context7;
    }

    public void setMap_context7(String map_context7) {
        Map_context7 = map_context7;
    }
}
