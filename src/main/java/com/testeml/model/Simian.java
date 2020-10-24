package com.testeml.model;

import lombok.Data;

@Data
public class Simian {

    private String[] dna;

    public Simian(String[] dna) {
        this.dna = dna;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
