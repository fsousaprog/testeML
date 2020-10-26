package com.testeml.service;

import com.testeml.model.Simian;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.util.StringUtils.toStringArray;

@Service
public class SimianService {

    // Linhas e colunas
    private static final int LINHA = 6;
    private static final int COLUNA = 6;
    // Para buscar omnidirecionalmente
    private static final int[] X = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] Y = {-1, 0, 1, -1, 1, -1, 0, 1};
    // Sequencias (Como deve encontrar 4 ou mais, basta encontrar 4)
    private static final String SEQ_A = "AAAA";
    private static final String SEQ_T = "TTTT";
    private static final String SEQ_C = "CCCC";
    private static final String SEQ_G = "GGGG";

    public Simian create(JSONObject simianJson) throws Exception {
        this.isJSONValid(simianJson);
        return new Simian(toStringArray((ArrayList<String>) simianJson.get("dna")));
    }

    public boolean isSimian(String[] dna) {
        char[][] grid = new char[LINHA][COLUNA];
        int i = 0;
        for (String sequencia : dna) {
            grid[i] = sequencia.toCharArray();
            i++;
        }

        for (int r = 0; r < LINHA; r++) {
            for (int c = 0; c < COLUNA; c++) {
                if (buscaEmVetor(grid, r, c)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Busca em todas as direções a partir do ponto (l, c) na grid[][]
    private boolean buscaEmVetor(char[][] grid, int l, int c) {

        // Se não começar com, já tira a letra especifica das chances de sequencia
        String sequencia = SEQ_A;
        if (grid[l][c] == SEQ_T.charAt(0)) {
            sequencia = SEQ_T;
        } else if (grid[l][c] == SEQ_C.charAt(0)) {
            sequencia = SEQ_C;
        } else if (grid[l][c] == SEQ_G.charAt(0)) {
            sequencia = SEQ_G;
        }

        int tamanho = 4;
        for (int dir = 0; dir < 8; dir++) {
            // Inicializa ponto da direção atual
            int k, rd = l + X[dir], cd = c + Y[dir];

            for (k = 1; k < tamanho; k++) {
                // Se passar do limite
                if (rd >= LINHA || rd < 0 || cd >= COLUNA || cd < 0)
                    break;
                // Se não encontrar
                if (grid[rd][cd] != sequencia.charAt(k))
                    break;
                // Anda
                rd += X[dir];
                cd += Y[dir];
            }

            if (k >= tamanho)
                return true;
        }
        return false;
    }

    private void isJSONValid(JSONObject json) throws IOException {
        try {
            ArrayList<String> dna = (ArrayList<String>) json.get("dna");
            if (dna != null && !dna.isEmpty()) {
                if (dna.size() == 6) {
                    for (String sequencia : dna) {
                        if (sequencia.length() != 6 || sequencia.matches(".*\\d.*") || sequencia.matches(".*[a-zBD-FH-SU-Z].*")) {
                            throw new IOException("Cada sequência de dna deve ter 6 valores não numéricos, e apenas as letras maiúsculas: A, T, C, G");
                        }
                    }
                } else {
                    throw new IOException("Valor da tag 'dna' deve ter 6 campos");
                }
            } else {
                throw new IOException("Tag 'dna' não encontrada ou vazia");
            }
        } catch (Exception e) {
            throw new IOException("JSON no formato incorreto: " + e.getMessage());
        }
    }

}