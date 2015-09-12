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

    public final int HOMENS = 0;
    public final int MULHERES = 1;
    public final int CRIANCAS = 2;

    private EditText[] edit = new EditText[3];
    private int[] idEdit = new int[3];
    private int[] nConvidados = new int[3];

    CheckBox checkCarne, checkLinguica, checkCerveja, checkRefri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InicializaControles();
        criaBtnAjuda();
    }

    private void InicializaControles() {
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
        Button[] btnMais = new Button[3];

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
        Button[] btnMenos = new Button[3];

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
        checkCarne = (CheckBox)findViewById(R.id.checkCarne);
        checkLinguica = (CheckBox)findViewById(R.id.checkLinguica);
        checkCerveja = (CheckBox)findViewById(R.id.checkCerveja);
        checkRefri = (CheckBox)findViewById(R.id.checkRefri);

        checkCarne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLinguica.setEnabled(checkCarne.isChecked());
                calcula();
            }
        });
        checkLinguica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCarne.setEnabled(checkLinguica.isChecked());
                calcula();
            }
        });
        checkCerveja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRefri.setEnabled(checkCerveja.isChecked());
                calcula();
            }
        });
        checkRefri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCerveja.setEnabled(checkRefri.isChecked());
                calcula();
            }
        });
    }

    private void calcula() {
        TextView resultCarne = (TextView)findViewById(R.id.resultCarne);
        TextView resultLinguica = (TextView)findViewById(R.id.resultLinguica);
        TextView resultCerveja = (TextView)findViewById(R.id.resultCerveja);
        TextView resultRefri = (TextView)findViewById(R.id.resultRefri);

        double quantCarne = 0.450 * nConvidados[HOMENS] + 0.300 * nConvidados[MULHERES] + 0.150 * nConvidados[CRIANCAS];
        double quantLinguica = 0.200 * nConvidados[HOMENS] + 0.200 * nConvidados[MULHERES] + 0.050 * nConvidados[CRIANCAS];
        double quantCerveja = 1.250 * nConvidados[HOMENS] + 0.500 * nConvidados[MULHERES];
        double quantRefri = 0.200 * nConvidados[HOMENS] + 0.500 * nConvidados[MULHERES] + 0.500 * nConvidados[CRIANCAS];

        CheckBox checkCarne = (CheckBox)findViewById(R.id.checkCarne);
        CheckBox checkLinguica = (CheckBox)findViewById(R.id.checkLinguica);
        CheckBox checkCerveja = (CheckBox)findViewById(R.id.checkCerveja);
        CheckBox checkRefri = (CheckBox)findViewById(R.id.checkRefri);

        if (!checkCarne.isChecked()) {
            quantLinguica += quantCarne;
            quantCarne = 0;
        }
        else if (!checkLinguica.isChecked()) {
            quantCarne += quantLinguica;
            quantLinguica = 0;
        }

        if (!checkCerveja.isChecked()) {
            quantRefri += quantCerveja;
            quantCerveja = 0;
        }
        else if (!checkRefri.isChecked()) {
            quantCerveja += quantRefri;
            quantRefri = 0;
        }

        resultCarne.setText(String.format("%1$,.1f kg",quantCarne));
        resultLinguica.setText(String.format("%1$,.1f kg",quantLinguica));
        resultCerveja.setText(String.format("%1$,.1f l",quantCerveja));
        resultRefri.setText(String.format("%1$,.1f l",quantRefri));
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
