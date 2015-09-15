package com.banki.churrascometro;

import java.io.Serializable;

public class Churrasco implements Serializable {

    // tipos de convidados
    public static final int nTiposConvidados = 3;
    public static final int HOMENS = 0;
    public static final int MULHERES = 1;
    public static final int CRIANCAS = 2;

    // tipos de ingredientes
    public static final int nTiposIngredientes = 4;
    public static final int CARNE = 0;
    public static final int LINGUICA = 1;
    public static final int CERVEJA = 2;
    public static final int REFRI = 3;

    // quantidade consumida por tipo de convidado
    double[][] parametros = new double[nTiposConvidados][nTiposIngredientes];

    // ingredientes escolhidos
    boolean[] ingredienteCheck = new boolean[nTiposIngredientes];

    // número de convidados
    int[] numeroConvidados = new int[nTiposConvidados];

    public Churrasco() {
        parametros[HOMENS][CARNE] = 0.450;
        parametros[MULHERES][CARNE] = 0.300;
        parametros[CRIANCAS][CARNE] = 0.150;
        parametros[HOMENS][LINGUICA] = 0.300;
        parametros[MULHERES][LINGUICA] = 0.200;
        parametros[CRIANCAS][LINGUICA] = 0.050;
        parametros[HOMENS][CERVEJA] = 1.250;
        parametros[MULHERES][CARNE] = 0.500;
        parametros[CRIANCAS][CARNE] = 0;
        parametros[HOMENS][REFRI] = 0.200;
        parametros[MULHERES][REFRI] = 0.500;
        parametros[CRIANCAS][REFRI] = 0.500;
    }

    public void setIngredienteCheck(int tipo, boolean value) {
        ingredienteCheck[tipo] = value;
    }

    public void setNumeroConvidados(int tipo, int value) {
        numeroConvidados[tipo] = value;
    }

    private double consumo(int ingrediente) {
        double quant = 0;
        for (int i=HOMENS; i<=CRIANCAS; i++) {
            quant += numeroConvidados[i] * parametros[i][ingrediente];
        }
        return quant;
    }

    private double consumoCorrigido(int ingrediente) {
        if (!ingredienteCheck[ingrediente])
            return 0;

        double quant = consumo(ingrediente);

        int ingredienteCombinado = 0;
        if (ingrediente == CARNE)
            ingredienteCombinado = LINGUICA;
        else if (ingrediente == LINGUICA)
            ingredienteCombinado = CARNE;
        else if (ingrediente == CERVEJA)
            ingredienteCombinado = REFRI;
        else if (ingrediente == REFRI)
            ingredienteCombinado = CERVEJA;

        if (!ingredienteCheck[ingredienteCombinado])
            quant += consumo(ingredienteCombinado);

        return quant;
    }

    public String consumoAsString(int ingrediente) {
        if (ingrediente == CARNE || ingrediente == LINGUICA)
            return String.format("%1$,.1f kg", consumoCorrigido(ingrediente));
        else
            return String.format("%1$,.1f l", consumoCorrigido(ingrediente));
    }

}
