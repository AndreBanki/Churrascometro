package com.banki.churrascometro;

import android.content.SharedPreferences;

public class Churrasco {

    public static final String PREFS_NAME = "ChurrasPref";

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
    int[][] parametros = new int[nTiposConvidados][nTiposIngredientes];

    // ingredientes escolhidos
    boolean[] ingredienteCheck = new boolean[nTiposIngredientes];

    // número de convidados
    int[] numeroConvidados = new int[nTiposConvidados];

    public Churrasco() {
        numeroConvidados[HOMENS] = 20;
        numeroConvidados[MULHERES] = 15;
        numeroConvidados[CRIANCAS] = 5;

        parametros[HOMENS][CARNE] = 450;
        parametros[MULHERES][CARNE] = 300;
        parametros[CRIANCAS][CARNE] = 150;
        parametros[HOMENS][LINGUICA] = 300;
        parametros[MULHERES][LINGUICA] = 200;
        parametros[CRIANCAS][LINGUICA] = 50;
        parametros[HOMENS][CERVEJA] = 1250;
        parametros[MULHERES][CERVEJA] = 500;
        parametros[CRIANCAS][CERVEJA] = 0;
        parametros[HOMENS][REFRI] = 200;
        parametros[MULHERES][REFRI] = 500;
        parametros[CRIANCAS][REFRI] = 500;
    }

    public void saveConvidados(SharedPreferences settings) {
        SharedPreferences.Editor editor = settings.edit();
        for (int i=HOMENS; i<=CRIANCAS; i++) {
            String key = "Convidados_" + i;
            editor.putInt(key,numeroConvidados[i]);
        }
        editor.commit();
    }

    public void restoreConvidados(SharedPreferences settings) {
        for (int i=HOMENS; i<=CRIANCAS; i++) {
            String key = "Convidados_" + i;
            // return current value (default) if prefs not present
            numeroConvidados[i] = settings.getInt(key, numeroConvidados[i]);
        }
    }

    public void saveParametrosConsumo(SharedPreferences settings, int tipoConvidado) {
        SharedPreferences.Editor editor = settings.edit();
        for (int i=CARNE; i<=REFRI; i++) {
            String key = "Consumo_" + i + "_" + tipoConvidado;
            editor.putInt(key, parametros[tipoConvidado][i]);
        }
        editor.commit();
    }

    public void restoreParametrosConsumo(SharedPreferences settings, int tipoConvidado) {
        for (int i=CARNE; i<=REFRI; i++) {
            String key = "Consumo_" + i + "_" + tipoConvidado;
            // return current value (default) if prefs not present
            parametros[tipoConvidado][i] = settings.getInt(key, parametros[tipoConvidado][i]);
        }
    }

    public void restoreAllData(SharedPreferences settings) {
        restoreConvidados(settings);
        for (int i=HOMENS; i<=CRIANCAS; i++)
            restoreParametrosConsumo(settings, i);
    }

    public void setIngredienteCheck(int tipo, boolean value) {
        ingredienteCheck[tipo] = value;
    }

    public void setNumeroConvidados(int tipo, int value) {
        numeroConvidados[tipo] = value;
    }

    public int getNumeroConvidados(int tipo) {
        return numeroConvidados[tipo];
    }

    public void setParametroConsumo(int tipoConvidado, int ingrediente, int valor) {
        parametros[tipoConvidado][ingrediente] = valor;
    }

    public int getParametroConsumo(int tipoConvidado, int ingrediente) {
        return parametros[tipoConvidado][ingrediente];
    }

    private int consumo(int ingrediente) {
        int quant = 0;
        for (int i=HOMENS; i<=CRIANCAS; i++) {
            quant += numeroConvidados[i] * parametros[i][ingrediente];
        }
        return quant;
    }

    private int  consumoCorrigido(int ingrediente) {
        if (!ingredienteCheck[ingrediente])
            return 0;

        int quant = consumo(ingrediente);

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

    public String getConsumoAsString(int ingrediente) {
        if (ingrediente == CARNE || ingrediente == LINGUICA)
            return String.format("%1$,.1f kg", (float)consumoCorrigido(ingrediente)/1000);
        else
            return String.format("%1$,.1f l", (float)consumoCorrigido(ingrediente)/1000);
    }

}
