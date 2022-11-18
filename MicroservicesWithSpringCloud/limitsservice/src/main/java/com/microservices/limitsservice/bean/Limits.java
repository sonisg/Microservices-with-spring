package com.microservices.limitsservice.bean;

public class Limits {
    private int minimum;
    private int maximun;

    public Limits() {
        super();
    }

    public Limits(int minimum, int maximun) {
        this.minimum = minimum;
        this.maximun = maximun;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximun() {
        return maximun;
    }

    public void setMaximun(int maximun) {
        this.maximun = maximun;
    }
}
