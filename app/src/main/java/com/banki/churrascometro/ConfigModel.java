package com.banki.churrascometro;

import java.io.Serializable;

public class ConfigModel implements Serializable {

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

    public ConfigModel() {
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

    public double Consumo(int convidado, int ingrediente) {
        return parametros[convidado][ingrediente];
    }
}
