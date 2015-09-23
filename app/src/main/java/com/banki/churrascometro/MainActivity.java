package com.banki.churrascometro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.banki.utils.DecrementButton;
import com.banki.utils.EditInteger;
import com.banki.utils.IncrementButton;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends Activity {

    private Churrasco churrasco = new Churrasco();
    SharedPreferences settings;

    private EditInteger[] edit = new EditInteger[Churrasco.nTiposConvidados];
    private CheckBox[] check = new CheckBox[Churrasco.nTiposIngredientes];
    private TextView[] result = new TextView[Churrasco.nTiposIngredientes];
    private boolean restoringState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences(Churrasco.PREFS_NAME, 0);
        criaControles();
        criaBtnAjuda();
    }

    @Override
    protected void onPause() {
        churrasco.saveConvidados(settings);
        super.onPause();
    }

    @Override
    protected void onResume() {
        churrasco.restoreAllData(settings);

        // faz com que a atualização da tela não seja feita até acabar de preencher os edits
        restoringState = true;
        atualizaEdits();
        restoringState = false;
        atualizaResultado();

        super.onResume();
    }

    private void criaControles() {
        criaEdits();
        criaBtnMais();
        criaBtnMenos();
        criaCheckBoxes();
        criaResultEdits();
    }

    private void criaEdits() {
        edit[Churrasco.HOMENS] = (EditInteger) findViewById(R.id.editHomens);
        edit[Churrasco.MULHERES] = (EditInteger) findViewById(R.id.editMulheres);
        edit[Churrasco.CRIANCAS] = (EditInteger) findViewById(R.id.editCriancas);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                atualizaResultado();
            }
        };

        for (int i= Churrasco.HOMENS; i<= Churrasco.CRIANCAS; i++)
            edit[i].addTextChangedListener(textWatcher);
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

    private void criaCheckBoxes() {
        check[Churrasco.CARNE] = (CheckBox)findViewById(R.id.checkCarne);
        check[Churrasco.LINGUICA] = (CheckBox)findViewById(R.id.checkLinguica);
        check[Churrasco.CERVEJA] = (CheckBox)findViewById(R.id.checkCerveja);
        check[Churrasco.REFRI] = (CheckBox)findViewById(R.id.checkRefri);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizaChecks();
                atualizaResultado();
            }
        };

        for (int i=Churrasco.CARNE; i<=Churrasco.REFRI; i++)
            check[i].setOnClickListener(clickListener);
    }

    private void criaResultEdits() {
        result = new TextView[Churrasco.nTiposIngredientes];
        result[Churrasco.CARNE] = (TextView)findViewById(R.id.resultCarne);
        result[Churrasco.LINGUICA] = (TextView)findViewById(R.id.resultLinguica);
        result[Churrasco.CERVEJA] = (TextView)findViewById(R.id.resultCerveja);
        result[Churrasco.REFRI] = (TextView)findViewById(R.id.resultRefri);
    }

    private void atualizaEdits() {
        for (int i= Churrasco.HOMENS; i<= Churrasco.CRIANCAS; i++)
            edit[i].setIntValue(churrasco.getNumeroConvidados(i));
    }

    private void atualizaChecks() {
        check[Churrasco.LINGUICA].setEnabled(check[Churrasco.CARNE].isChecked());
        check[Churrasco.CARNE].setEnabled(check[Churrasco.LINGUICA].isChecked());
        check[Churrasco.REFRI].setEnabled(check[Churrasco.CERVEJA].isChecked());
        check[Churrasco.CERVEJA].setEnabled(check[Churrasco.REFRI].isChecked());
    }

    private void atualizaResultado() {
        if (!restoringState) {
            // obtem dados da interface
            for (int i = Churrasco.HOMENS; i <= Churrasco.CRIANCAS; i++)
                churrasco.setNumeroConvidados(i, edit[i].getIntValue());

            for (int j = Churrasco.CARNE; j <= Churrasco.REFRI; j++)
                churrasco.setIngredienteCheck(j, check[j].isChecked());

            // preenche com os resultados calculados
            for (int j = Churrasco.CARNE; j <= Churrasco.REFRI; j++)
                result[j].setText(churrasco.getConsumoAsString(j));
        }
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnConfig) {
            Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.resetPrefs) {
            SharedPreferences.Editor editor = settings.edit();
            editor.clear();
            editor.commit();

            churrasco = new Churrasco();

            Intent intent = getIntent();
            finish();
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
