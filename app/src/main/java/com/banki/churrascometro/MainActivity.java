package com.banki.churrascometro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.banki.utils.DecrementButton;
import com.banki.utils.EditInteger;
import com.banki.utils.IncrementButton;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private Churrasco churrasco = new Churrasco();

    // dados que precisam ser acessados nos listeners
    private EditInteger[] edit = new EditInteger[Churrasco.nTiposConvidados];
    private int[] nConvidados = new int[Churrasco.nTiposConvidados];
    private CheckBox[] check = new CheckBox[Churrasco.nTiposIngredientes];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nConvidados[Churrasco.HOMENS] = 20;
        nConvidados[Churrasco.MULHERES] = 15;
        nConvidados[Churrasco.CRIANCAS] = 5;

        InicializaControles();
        criaBtnAjuda();
    }

    private void InicializaControles() {
        criaEdits();
        criaBtnMais();
        criaBtnMenos();
        criaEditListeners();
        criaCheckListeners();
        atualizaResultado();
    }

    private void criaEdits() {
        int[] idEdit = new int[Churrasco.nTiposConvidados];
        idEdit[Churrasco.HOMENS] = R.id.editHomens;
        idEdit[Churrasco.MULHERES] = R.id.editMulheres;
        idEdit[Churrasco.CRIANCAS] = R.id.editCriancas;

        for (int i = Churrasco.HOMENS; i <= Churrasco.CRIANCAS; i++) {
            edit[i] = (EditInteger) findViewById(idEdit[i]);
            edit[i].setIntValue(nConvidados[i]);
        }
    }

    private void criaBtnMais() {
        IncrementButton[] btnMais = new IncrementButton[Churrasco.nTiposConvidados];
        btnMais[Churrasco.HOMENS] = (IncrementButton)findViewById(R.id.btnMaisHomens);
        btnMais[Churrasco.MULHERES] = (IncrementButton)findViewById(R.id.btnMaisMulheres);
        btnMais[Churrasco.CRIANCAS] = (IncrementButton)findViewById(R.id.btnMaisCriancas);

        for (int i= Churrasco.HOMENS; i<= Churrasco.CRIANCAS; i++)
            btnMais[i].setAssociatedEdit(edit[i]);
    }

    private void criaBtnMenos() {
        DecrementButton[] btnMenos = new DecrementButton[Churrasco.nTiposConvidados];

        btnMenos[Churrasco.HOMENS] = (DecrementButton)findViewById(R.id.btnMenosHomens);
        btnMenos[Churrasco.MULHERES] = (DecrementButton)findViewById(R.id.btnMenosMulheres);
        btnMenos[Churrasco.CRIANCAS] = (DecrementButton)findViewById(R.id.btnMenosCriancas);

        for (int i= Churrasco.HOMENS; i<= Churrasco.CRIANCAS; i++)
            btnMenos[i].setAssociatedEdit(edit[i]);
    }

    private void criaEditListeners() {
        edit[Churrasco.HOMENS].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nConvidados[Churrasco.HOMENS] = edit[Churrasco.HOMENS].getIntValue();
                atualizaResultado();
            }
        });
        edit[Churrasco.MULHERES].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nConvidados[Churrasco.MULHERES] = edit[Churrasco.MULHERES].getIntValue();
                atualizaResultado();
            }
        });
        edit[Churrasco.CRIANCAS].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nConvidados[Churrasco.CRIANCAS] = edit[Churrasco.CRIANCAS].getIntValue();
                atualizaResultado();
            }
        });
    }

    private void criaCheckListeners() {
        check[Churrasco.CARNE] = (CheckBox)findViewById(R.id.checkCarne);
        check[Churrasco.LINGUICA] = (CheckBox)findViewById(R.id.checkLinguica);
        check[Churrasco.CERVEJA] = (CheckBox)findViewById(R.id.checkCerveja);
        check[Churrasco.REFRI] = (CheckBox)findViewById(R.id.checkRefri);

        check[Churrasco.CARNE].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[Churrasco.LINGUICA].setEnabled(check[Churrasco.CARNE].isChecked());
                atualizaResultado();
            }
        });
        check[Churrasco.LINGUICA].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[Churrasco.CARNE].setEnabled(check[Churrasco.LINGUICA].isChecked());
                atualizaResultado();
            }
        });
        check[Churrasco.CERVEJA].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[Churrasco.REFRI].setEnabled(check[Churrasco.CERVEJA].isChecked());
                atualizaResultado();
            }
        });
        check[Churrasco.REFRI].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check[Churrasco.CERVEJA].setEnabled(check[Churrasco.REFRI].isChecked());
                atualizaResultado();
            }
        });
    }

    private void atualizaResultado() {
        for (int i=Churrasco.HOMENS; i<=Churrasco.CRIANCAS; i++)
            churrasco.setNumeroConvidados(i, nConvidados[i]);

        for (int j=Churrasco.CARNE; j<=Churrasco.REFRI; j++)
            churrasco.setIngredienteCheck(j, check[j].isChecked());

        TextView[] result = new TextView[Churrasco.nTiposIngredientes];
        result[Churrasco.CARNE] = (TextView)findViewById(R.id.resultCarne);
        result[Churrasco.LINGUICA] = (TextView)findViewById(R.id.resultLinguica);
        result[Churrasco.CERVEJA] = (TextView)findViewById(R.id.resultCerveja);
        result[Churrasco.REFRI] = (TextView)findViewById(R.id.resultRefri);

        result[Churrasco.CARNE].setText(churrasco.consumoAsString(Churrasco.CARNE));
        result[Churrasco.LINGUICA].setText(churrasco.consumoAsString(Churrasco.LINGUICA));
        result[Churrasco.CERVEJA].setText(churrasco.consumoAsString(Churrasco.CERVEJA));
        result[Churrasco.REFRI].setText(churrasco.consumoAsString(Churrasco.REFRI));
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
