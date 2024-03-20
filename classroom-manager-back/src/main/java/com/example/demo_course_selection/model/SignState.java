package com.example.demo_course_selection.model;

public enum SignState {

    NOTSIGNED("未签"),
    SIGNED("已签");

    private String state;
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    SignState(String state){
        this.state = state;
    }






}
