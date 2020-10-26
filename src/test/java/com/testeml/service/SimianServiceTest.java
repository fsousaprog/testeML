package com.testeml.service;


import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;

class SimianServiceTest {

    private static final String[] SIMIO = {
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
    };
    private static final String[] NAO_SIMIO = {
            "ATGCGA",
            "CAGTGC",
            "TTATTT",
            "AGACGG",
            "GCGTCA",
            "TCACTG"
    };
    private static final String[] SEQ_NUMERICA = {
            "ATGCGA",
            "CAGTG0",
            "TTATGT",
            "AG1AGG",
            "CCCCTA",
            "4CACTG"
    };
    private static final String[] SEQ_INCORRETA = {
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAG",
            "CCCCTA",
            "TCACTGT"
    };
    private static final String[] SEQ_LETRA_ERRADA = {
            "AKGCGA",
            "CAGTGC",
            "TTARGT",
            "AGAAGG",
            "CCPCTA",
            "TCACTG"
    };
    private static final String[] SEQ_FORA_DE_TAMANHO = {
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA"
    };

    @Test
    void create() {
        SimianService service = new SimianService();

        JSONObject jsonSimio = new JSONObject(singletonMap("dna", new ArrayList(asList(SIMIO))));
        JSONObject jsonDnaErrado = new JSONObject(singletonMap("dnaSimio", new ArrayList(asList(SEQ_NUMERICA))));
        JSONObject jsonSeqNumerica = new JSONObject(singletonMap("dna", new ArrayList(asList(SEQ_NUMERICA))));
        JSONObject jsonSeqIncorreta = new JSONObject(singletonMap("dna", new ArrayList(asList(SEQ_INCORRETA))));
        JSONObject jsonSeqLetraErrada = new JSONObject(singletonMap("dna", new ArrayList(asList(SEQ_LETRA_ERRADA))));
        JSONObject jsonSeqForaDeTamanho = new JSONObject(singletonMap("dna", new ArrayList(asList(SEQ_FORA_DE_TAMANHO))));

        try {
            assert service.create(jsonSimio) != null;
        } catch (Exception e) {
            assert false;
        }
        try {
            service.create(jsonDnaErrado);
            assert false;
        } catch (Exception e) {
            assert true;
        }
        try {
            service.create(jsonSeqNumerica);
            assert false;
        } catch (Exception e) {
            assert true;
        }
        try {
            service.create(jsonSeqIncorreta);
            assert false;
        } catch (Exception e) {
            assert true;
        }
        try {
            service.create(jsonSeqLetraErrada);
            assert false;
        } catch (Exception e) {
            assert true;
        }
        try {
            service.create(jsonSeqForaDeTamanho);
            assert false;
        } catch (Exception e) {
            assert true;
        }
    }

    @Test
    void isSimian() {
        SimianService service = new SimianService();
        assert service.isSimian(SIMIO);
        assert !service.isSimian(NAO_SIMIO);
    }

}
