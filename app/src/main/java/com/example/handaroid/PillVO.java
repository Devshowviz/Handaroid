package com.example.handaroid;

public class PillVO {
    private String pills_img;
    private String pills_name;
    private String pills_Ingredient;
    private String pills_efficacy;
    private String pills_company;

    //main클래스에서 데이터를 넘겨줄 수 있는 생성자메소드 생성
    public PillVO(String pills_img,String pills_name,String pills_Ingredient,String pills_efficacy,String pills_company){
        this.pills_img = pills_img;
        this.pills_name = pills_name;
        this.pills_Ingredient = pills_Ingredient;
        this.pills_efficacy = pills_efficacy;
        this.pills_company = pills_company;
    }

    public String getPills_img() {
        return pills_img;
    }

    public void setPills_img(String pills_img) {
        this.pills_img = pills_img;
    }

    public String getPills_name() {
        return pills_name;
    }

    public void setPills_name(String pills_name) {
        this.pills_name = pills_name;
    }

    public String getPills_Ingredient() {
        return pills_Ingredient;
    }

    public void setPills_Ingredient(String pills_Ingredient) {
        this.pills_Ingredient = pills_Ingredient;
    }

    public String getPills_efficacy() {
        return pills_efficacy;
    }

    public void setPills_efficacy(String pills_efficacy) {
        this.pills_efficacy = pills_efficacy;
    }

    public String getPills_company() {
        return pills_company;
    }

    public void setPills_company(String pills_company) {
        this.pills_company = pills_company;
    }
}
