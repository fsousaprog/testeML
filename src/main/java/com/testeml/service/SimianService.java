package com.testeml.service;

import com.testeml.model.Simian;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.util.StringUtils.toStringArray;

@Service
public class SimianService {

    public void isJSONValid(JSONObject json) throws IOException {
        try {
            ArrayList<String> dna = (ArrayList<String>) json.get("dna");
            if (dna != null && !dna.isEmpty()) {
                if (dna.size() == 6) {
                    for (String sequencia : dna) {
                        if (sequencia.length() != 6 || sequencia.matches(".*\\d.*")) {
                            throw new IOException("Cada sequência de dna deve ter 6 valores não numéricos");
                        }
                    }
                } else {
                    throw new IOException("Valor da tag 'dna' deve ter 6 campos separados por vírgula");
                }
            } else {
                throw new IOException("tag 'dnaString' não encontrada");
            }
        } catch (Exception e) {
            throw new IOException("JSON no formato incorreto");
        }
    }

    public boolean isSimian(String[] dna) {
        //TODO
        return false;
    }

    public Simian create(JSONObject simianJson) throws Exception {
        try {
            return new Simian(toStringArray((ArrayList<String>) simianJson.get("dna")));
        } catch (Exception e) {
            throw new Exception("JSON no formato incorreto");
        }
    }

    public void add(Simian simian) {
        //TODO
    }

}