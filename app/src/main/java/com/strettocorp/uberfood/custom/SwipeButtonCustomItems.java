package com.strettocorp.uberfood.custom;

/**
 * Created by StrettO on 2017/06/04.
 */

public abstract class SwipeButtonCustomItems {
    //These are the default values if we don't choose to set them later:
    private int gradientColor1 = 0xFF333333;
    private int gradientColor2 = 0xFF666666;
    private int gradientColor2Width = 50;
    private int gradientColor3 = 0xFF888888;
    private int postConfirmationColor = 0xFF888888;
    private double actionConfirmDistanceFraction = 0.7;
    private String buttonPressText = ">>   SWIPE TO CONFIRM   >> ";

    private String actionConfirmText = null;

    public int getGradientColor1() {
        return gradientColor1;
    }

    public SwipeButtonCustomItems setGradientColor1(int gradientColor1) {
        this.gradientColor1 = gradientColor1;
        return this;
    }

    public int getGradientColor2() {
        return gradientColor2;
    }

    public SwipeButtonCustomItems setGradientColor2(int gradientColor2) {
        this.gradientColor2 = gradientColor2;
        return this;
    }

    public int getGradientColor2Width() {
        return gradientColor2Width;
    }

    public SwipeButtonCustomItems setGradientColor2Width(int gradientColor2Width) {
        this.gradientColor2Width = gradientColor2Width;
        return this;
    }

    public int getGradientColor3() {
        return gradientColor3;
    }

    public SwipeButtonCustomItems setGradientColor3(int gradientColor3) {
        this.gradientColor3 = gradientColor3;
        return this;
    }

    public double getActionConfirmDistanceFraction() {
        return actionConfirmDistanceFraction;
    }

    public SwipeButtonCustomItems setActionConfirmDistanceFraction(double actionConfirmDistanceFraction) {
        this.actionConfirmDistanceFraction = actionConfirmDistanceFraction;
        return this;
    }

    public String getButtonPressText() {
        return buttonPressText;
    }

    public SwipeButtonCustomItems setButtonPressText(String buttonPressText) {
        this.buttonPressText = buttonPressText;
        return this;
    }

    public String getActionConfirmText() {
        return actionConfirmText;
    }

    public SwipeButtonCustomItems setActionConfirmText(String actionConfirmText) {
        this.actionConfirmText = actionConfirmText;
        return this;
    }

    public int getPostConfirmationColor() {
        return postConfirmationColor;
    }

    public SwipeButtonCustomItems setPostConfirmationColor(int postConfirmationColor) {
        this.postConfirmationColor = postConfirmationColor;
        return this;
    }

    //These methods listed below can be overridden in the instance of SwipeButton and
    public void onButtonPress(){

    }

    public void onSwipeCancel(){

    }

    abstract public void onSwipeConfirm();
}
