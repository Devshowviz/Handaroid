package com.example.handaroid;

public class AlarmVO {
    //kakaolist 템플릿에 들어갈수 있는 데이터 정보를 정의하는 클래스
    //img,text,text   kakaolist xml에 있는


    private int alarm_seq;
    private String text;
    private String hours;
    private String minutes;

    //main클래스에서 데이터를 넘겨줄 수 있는 생성자메소드 생성
    public AlarmVO(int alarm_seq, String text, String hours,String minutes){
        this.alarm_seq = alarm_seq;
        this.text = text;
        this.hours = hours;
        this.minutes = minutes;
    }

    //데이터들에 접근할 수 있는 get/set 메소드 생성하기


    public int getAlarm_seq() {
        return alarm_seq;
    }

    public void setAlarm_seq(int alarm_seq) {
        this.alarm_seq = alarm_seq;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }
}

