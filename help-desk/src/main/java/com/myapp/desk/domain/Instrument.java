package com.myapp.desk.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.io.Serializable;

@Entity
@Table(name = "instrument")
public class Instrument implements Serializable{

    @Id
    private long id ;
    private String symbol;
    private String name;
    private String isin;

    // Getters and Setters

    public long getId() { return id; }

    public  void setId(long id) { this.id = id; }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }
}
