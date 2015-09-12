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

    ConfigModel config;

    // dados que precisam ser acessados nos listeners
    private EditText[] edit = new EditText[ConfigModel.nTiposConvidados];
    private int[] nConvidados = new int[ConfigModel.nTiposConvidados];
    private CheckBox[] check = new CheckBox[ConfigModel.nTiposIngredientes];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config = new ConfigModel();

        InicializaControles();
        criaBtnAjuda();
    }

    private void InicializaControles() {
        int[] idEdit = new int[ConfigModel.nTiposConvidados];
        idEdit[ConfigModel.HOMENS] = R.id.editHomens;
        idEdit[ConfigModel.MULHERES] = R.id.editMulheres;
        idEdit[ConfigModel.CRIANCAS] = R.id.editCriancas;

        nConvidados[ConfigModel.HOMENS] = 20;
        nConvidados[ConfigModel.MULHERES] = 15;
        nConvidados[ConfigModel.CRIANCAS] = 5;

        for (int i = ConfigModel.HOMENS; i <= ConfigModel.CRIANCAS; i++) {
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
        Button[] btnMais = new Button[ConfigModel.nTiposConvidados];

        btnMais[ConfigModel.HOMENS] = (Button)findViewById(R.id.btnMaisHomens);
        btnMais[ConfigModel.MULHERES] = (Button)findViewById(R.id.btnMaisMulheres);
        btnMais[ConfigModel.CRIANCAS] = (Button)findViewById(R.id.btnMaisCriancas);

        btnMais[ConfigModel.HOMENS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[ConfigModel.HOMENS] = getIntValue(edit[ConfigModel.HOMENS]);
                setIntValue(edit[ConfigModel.HOMENS], ++nConvidados[ConfigModel.HOMENS]);
                calcula();
            }
        });
        btnMais[ConfigModel.MULHERES].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[ConfigModel.MULHERES] = getIntValue(edit[ConfigModel.MULHERES]);
                setIntValue(edit[ConfigModel.MULHERES], ++nConvidados[ConfigModel.MULHERES]);
                calcula();
            }
        });
        btnMais[ConfigModel.CRIANCAS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[ConfigModel.CRIANCAS] = getIntValue(edit[ConfigModel.CRIANCAS]);
                setIntValue(edit[ConfigModel.CRIANCAS], ++nConvidados[ConfigModel.CRIANCAS]);
                calcula();
            }
        });
    }

    private void criaBtnMenos() {
        Button[] btnMenos = new Button[ConfigModel.nTiposConvidados];

        btnMenos[ConfigModel.HOMENS] = (Button)findViewById(R.id.btnMenosHomens);
        btnMenos[ConfigModel.MULHERES] = (Button)findViewById(R.id.btnMenosMulheres);
        btnMenos[ConfigModel.CRIANCAS] = (Button)findViewById(R.id.btnMenosCriancas);

        btnMenos[ConfigModel.HOMENS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[ConfigModel.HOMENS] = getIntValue(edit[ConfigModel.HOMENS]);
                setIntValue(edit[ConfigModel.HOMENS], --nConvidados[ConfigModel.HOMENS]);
                calcula();
            }
        });
        btnMenos[ConfigModel.MULHERES].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[ConfigModel.MULHERES] = getIntValue(edit[ConfigModel.MULHERES]);
                setIntValue(edit[ConfigModel.MULHERES], --nConvidados[ConfigModel.MULHERES]);
                calcula();
            }
        });
        btnMenos[ConfigModel.CRIANCAS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nConvidados[ConfigModel.CRIANCAS] = getIntValue(edit[ConfigModel.CRIANCAS]);
                setIntValue(edit[ConfigModel.CRIANCAS], --nConvidados[ConfigModel.CRIANCAS]);
                calcula();
            }
        });
    }

    private void criaEditListeners() {
        edit[ConfigModel.HOMENS].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nConvidados[ConfigModel.HOMENS] = getIntValue(edit[ConfigModel.HOMENS]);
                calcula();
            }
        });
        edit[ConfigModel.MULHERES].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nConvidados[ConfigModel.MULHERES] = getIntValue(edit[ConfigModel.MULHERES]);
                calcula();
            }
        });
        edit[ConfigModel.CRIANCAS].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nConvidados[ConfigModel.CRIANCAS] = getIntValue(edit[ConfigModel.CRIANCAS]);
                calcula();
            }
        });
    }

    private void criaCheckListeners() {
        check[ConfigModel.CARNE] = (CheckBox)findViewById(R.id.checkCarne);
        check[ConfigModel.LINGUICA] = (CheckBox)findViewById(R.id.checkLinguica);
        check[ConfigModel.CERVEJA] = (CheckBox)findViewById(R.id.checkCerveja);
        check[ConfigModel.REFRI] = (CheckBox)findViewById(R.id.checkRefri);

        check[ConfigModel.CARNE].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[ConfigModel.LINGUICA].setEnabled(check[ConfigModel.CARNE].isChecked());
                calcula();
            }
        });
        check[ConfigModel.LINGUICA].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[ConfigModel.CARNE].setEnabled(check[ConfigModel.LINGUICA].isChecked());
                calcula();
            }
        });
        check[ConfigModel.CERVEJA].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[ConfigModel.REFRI].setEnabled(check[ConfigModel.CERVEJA].isChecked());
                calcula();
            }
        });
        check[ConfigModel.REFRI].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[ConfigModel.CERVEJA].setEnabled(check[ConfigModel.REFRI].isChecked());
                calcula();
            }
        });
    }

    private void calcula() {
        TextView[] result = new TextView[ConfigModel.nTiposIngredientes];
        result[ConfigModel.CARNE] = (TextView)findViewById(R.id.resultCarne);
        result[ConfigModel.LINGUICA] = (TextView)findViewById(R.id.resultLinguica);
        result[ConfigModel.CERVEJA] = (TextView)findViewById(R.id.resultCerveja);
        result[ConfigModel.REFRI] = (TextView)findViewById(R.id.resultRefri);

        double[] quant = new double[ConfigModel.nTiposIngredientes];
        for (int i=ConfigModel.CARNE; i<=ConfigModel.REFRI;i++) {
            quant[i] = 0;
            for (int j=ConfigModel.HOMENS; j<=ConfigModel.CRIANCAS; j++) {
                quant[i] += nConvidados[j] * config.Consumo(j,i);
            }
        }

        if (!check[ConfigModel.CARNE].isChecked()) {
            quant[ConfigModel.LINGUICA] += quant[ConfigModel.CARNE];
            quant[ConfigModel.CARNE] = 0;
        }
        else if (!check[ConfigModel.LINGUICA].isChecked()) {
            quant[ConfigModel.CARNE] += quant[ConfigModel.LINGUICA];
            quant[ConfigModel.LINGUICA] = 0;
        }

        if (!check[ConfigModel.CERVEJA].isChecked()) {
            quant[ConfigModel.REFRI] += quant[ConfigModel.CERVEJA];
            quant[ConfigModel.CERVEJA] = 0;
        }
        else if (!check[ConfigModel.REFRI].isChecked()) {
            quant[ConfigModel.CERVEJA] += quant[ConfigModel.REFRI];
            quant[ConfigModel.REFRI] = 0;
        }

        result[ConfigModel.CARNE].setText(String.format("%1$,.1f kg",quant[ConfigModel.CARNE]));
        result[ConfigModel.LINGUICA].setText(String.format("%1$,.1f kg", quant[ConfigModel.LINGUICA]));
        result[ConfigModel.CERVEJA].setText(String.format("%1$,.1f l", quant[ConfigModel.CERVEJA]));
        result[ConfigModel.REFRI].setText(String.format("%1$,.1f l", quant[ConfigModel.REFRI]));
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
