package org.jeroen.querydsl.domain;

public class Car {
    private String model;
    private Integer horsePower;
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public Integer getHorsePower() {
        return horsePower;
    }
    
    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }
}
