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

}
