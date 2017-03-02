package com.example.yow.easystock.BottomBar.MySelection;

/**
 * Created by 12205 on 2016/8/31.
 */
public class AddSelectionEvent {

    private final String message;

    public AddSelectionEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
