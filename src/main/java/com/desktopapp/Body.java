package com.desktopapp;

public class Body {
    private double mass = 15;
    private double vY = 0;
    private double posY = 0;
    private double posX = 0; 
    private double vX = 0; 
    private double d = 0; 
    private double v0Y = 0; 
    private double v0X = 0; 

    public Body(double mass, double vY, double posX, double posY, double vX, double d, double v0Y, double v0X) {
        this.mass = mass;
        this.vY = vY;
        this.posX = posX;
        this.posY = posY;
        this.vX = vX;
        this.d = d;
        this.v0Y = v0Y;     
        this.v0X = v0X; 
    }
    
    
    public double getV0Y() {
        return v0Y;
    }

    public void setV0Y(double v0y) {
        v0Y = v0y;
    }
    public double getMass() {
        return mass;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }
    
    public double getPosX() {
        return posX;
    }
    public void setPosX(double posX) {
        this.posX = posX;
    }
     
    public double getPosY() {
        return posY;
    }
    public void setPosY(double posY) {
        this.posY = posY;
    }
    
    public double getvX() {
        return vX;
    }
    public void setvX(double vX) {
        this.vX = vX;
    }

    public double getvY() {
        return vY;
    }
    public void setvY(double vY) {
        this.vY = vY;
    } 

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getV0X() {
        return v0X;
    }


    public void setV0X(double v0x) {
        v0X = v0x;
    }
}
