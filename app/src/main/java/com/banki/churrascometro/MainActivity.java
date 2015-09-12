package com.banki.churrascometro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    // tipos de convidados
    public final int nTiposConvidados = 3;
    public final int HOMENS = 0;
    public final int MULHERES = 1;
    public final int CRIANCAS = 2;

    // tipos de ingredientes
    public final int nTiposIngredientes = 4;
    public final int CARNE = 0;
    public final int LINGUICA = 1;
    public final int CERVEJA = 2;
    public final int REFRI = 3;

    // dados que precisam ser acessados nos listeners
    private EditText[] edit = new EditText[nTiposConvidados];
    private int[] nConvidados = new int[nTiposConvidados];
    private CheckBox[] check = new CheckBox[nTiposIngredientes];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InicializaControles();
        criaBtnAjuda();
    }

    private void InicializaControles() {
        int[] idEdit = new int[nTiposConvidados];
        idEdit[HOMENS] = R.id.editHomens;
        idEdit[MULHERES] = R.id.editMulheres;
        idEdit[CRIANCAS] = R.id.editCriancas;

        nConvidados[HOMENS] = 20;
        nConvidados[MULHERES] = 15;
        nConvidados[CRIANCAS] = 5;

        for (int i = HOMENS; i <= CRIANCAS; i++) {
            edit[i] = (EditText) findViewById(idEdit[i]);
            setIntValue(edit[i], nConvidados[i]);
        }
        criaBtnMais();
        criaBtnMenos();
        criaEditListeners();
        criaCheckListeners();
        calcula();
    }

    private int getIntValue(EditText edit) {
        int value = 0;
        try {
            value = Integer.parseInt(edit.getText().toString());
        }
        catch (NumberFormatException e) {
            Log.e("Churrascômetro", Log.getStackTraceString(e));
        }
        return value;
    }

    private void setIntValue(EditText edit, int value) {
        if (value < 0)
            value = 0;
        edit.setText(String.valueOf(value));
    }

    private void criaBtnMais() {
        Button[] btnMais = new Button[nTiposConvidados];

        btnMais[HOMENS] = (Button)findViewById(R.id.btnMaisHomens);
        btnMais[MULHERES] = (Button)findViewById(R.id.btnMaisMulheres);
        btnMais[CRIANCAS] = (Button)findViewById(R.id.btnMaisCriancas);

        btnMais[HOMENS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[HOMENS] = getIntValue(edit[HOMENS]);
                setIntValue(edit[HOMENS], ++nConvidados[HOMENS]);
                calcula();
            }
        });
        btnMais[MULHERES].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[MULHERES] = getIntValue(edit[MULHERES]);
                setIntValue(edit[MULHERES], ++nConvidados[MULHERES]);
                calcula();
            }
        });
        btnMais[CRIANCAS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[CRIANCAS] = getIntValue(edit[CRIANCAS]);
                setIntValue(edit[CRIANCAS], ++nConvidados[CRIANCAS]);
                calcula();
            }
        });
    }

    private void criaBtnMenos() {
        Button[] btnMenos = new Button[nTiposConvidados];

        btnMenos[HOMENS] = (Button)findViewById(R.id.btnMenosHomens);
        btnMenos[MULHERES] = (Button)findViewById(R.id.btnMenosMulheres);
        btnMenos[CRIANCAS] = (Button)findViewById(R.id.btnMenosCriancas);

        btnMenos[HOMENS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[HOMENS] = getIntValue(edit[HOMENS]);
                setIntValue(edit[HOMENS], --nConvidados[HOMENS]);
                calcula();
            }
        });
        btnMenos[MULHERES].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[MULHERES] = getIntValue(edit[MULHERES]);
                setIntValue(edit[MULHERES], --nConvidados[MULHERES]);
                calcula();
            }
        });
        btnMenos[CRIANCAS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[CRIANCAS] = getIntValue(edit[CRIANCAS]);
                setIntValue(edit[CRIANCAS], --nConvidados[CRIANCAS]);
                calcula();
            }
        });
    }

    private void criaEditListeners() {
        edit[HOMENS].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nConvidados[HOMENS] = getIntValue(edit[HOMENS]);
                calcula();
            }
        });
        edit[MULHERES].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nConvidados[MULHERES] = getIntValue(edit[MULHERES]);
                calcula();
            }
        });
        edit[CRIANCAS].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nConvidados[CRIANCAS] = getIntValue(edit[CRIANCAS]);
                calcula();
            }
        });
    }

    private void criaCheckListeners() {
        check[CARNE] = (CheckBox)findViewById(R.id.checkCarne);
        check[LINGUICA] = (CheckBox)findViewById(R.id.checkLinguica);
        check[CERVEJA] = (CheckBox)findViewById(R.id.checkCerveja);
        check[REFRI] = (CheckBox)findViewById(R.id.checkRefri);

        check[CARNE].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[LINGUICA].setEnabled(check[CARNE].isChecked());
                calcula();
            }
        });
        check[LINGUICA].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[CARNE].setEnabled(check[LINGUICA].isChecked());
                calcula();
            }
        });
        check[CERVEJA].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[REFRI].setEnabled(check[CERVEJA].isChecked());
                calcula();
            }
        });
        check[REFRI].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[CERVEJA].setEnabled(check[REFRI].isChecked());
                calcula();
            }
        });
    }

    private void calcula() {
        TextView[] result = new TextView[nTiposIngredientes];
        result[CARNE] = (TextView)findViewById(R.id.resultCarne);
        result[LINGUICA] = (TextView)findViewById(R.id.resultLinguica);
        result[CERVEJA] = (TextView)findViewById(R.id.resultCerveja);
        result[REFRI] = (TextView)findViewById(R.id.resultRefri);

        double[][] parametros = new double[nTiposConvidados][nTiposIngredientes];
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

        double[] quant = new double[nTiposIngredientes];
        for (int i=CARNE; i<=REFRI;i++) {
            quant[i] = 0;
            for (int j=HOMENS; j<=CRIANCAS; j++) {
                quant[i] += nConvidados[j] * parametros[j][i];
            }
        }

        if (!check[CARNE].isChecked()) {
            quant[LINGUICA] += quant[CARNE];
            quant[CARNE] = 0;
        }
        else if (!check[LINGUICA].isChecked()) {
            quant[CARNE] += quant[LINGUICA];
            quant[LINGUICA] = 0;
        }

        if (!check[CERVEJA].isChecked()) {
            quant[REFRI] += quant[CERVEJA];
            quant[CERVEJA] = 0;
        }
        else if (!check[REFRI].isChecked()) {
            quant[CERVEJA] += quant[REFRI];
            quant[REFRI] = 0;
        }

        result[CARNE].setText(String.format("%1$,.1f kg",quant[CARNE]));
        result[LINGUICA].setText(String.format("%1$,.1f kg", quant[LINGUICA]));
        result[CERVEJA].setText(String.format("%1$,.1f l", quant[CERVEJA]));
        result[REFRI].setText(String.format("%1$,.1f l", quant[REFRI]));
    }

    private void criaBtnAjuda() {
        Button btnAjuda = (Button)findViewById(R.id.btnAjuda);
        btnAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(
                        MainActivity.this,
                        R.string.ajuda,
                        LENGTH_LONG);
                t.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btnConfig) {
            Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
