package com.desktopapp;

public class Spring {
    private double force = 0;
    private final double k = 5;
    private double lenght = 0;
    private double x = 0;
    private Body b1;
    private Body b2;


    public Spring(double force, double lenght, double x, Body body1, Body body2) {
        this.force = force;
        this.lenght = lenght;
        this.x = x;
        this.b1 = body1;
        this.b2 = body2;
    }

    public double getForce() {
        return force;
    }
    public void setForce(double force) {
        this.force = force;
    }
    
    public double getK() {
        return k;
    }
    public double getLenght() {
        return lenght;
    }
    public void setLenght(double x) {
        this.lenght = x;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    
    public Body getB1() {
        return b1;
    }

    public void setB1(Body b1) {
        this.b1 = b1;
    }

    public Body getB2() {
        return b2;
    }

    public void setB2(Body b2) {
        this.b2 = b2;
    }
}
