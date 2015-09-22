package com.banki.churrascometro;

import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banki.utils.EditInteger;

public class ConfigTabFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private Churrasco churrasco = new Churrasco();
    private int tipoConvidado;
    SharedPreferences settings;

    private EditInteger[] edit = new EditInteger[Churrasco.nTiposIngredientes];

    public static ConfigTabFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ConfigTabFragment fragment = new ConfigTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipoConvidado = getArguments().getInt(ARG_PAGE);
        settings = getActivity().getSharedPreferences(Churrasco.PREFS_NAME, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_config, container, false);
        edit[Churrasco.CARNE] = (EditInteger) view.findViewById(R.id.editCarne);
        edit[Churrasco.LINGUICA] = (EditInteger) view.findViewById(R.id.editLinguica);
        edit[Churrasco.CERVEJA] = (EditInteger) view.findViewById(R.id.editCerveja);
        edit[Churrasco.REFRI] = (EditInteger) view.findViewById(R.id.editRefri);

        preencheEdits();
        return view;
    }

    @Override
    public void onPause() {
        for (int i=Churrasco.CARNE; i<=Churrasco.REFRI; i++)
            churrasco.setParametroConsumo(tipoConvidado, i, edit[i].getIntValue());

        churrasco.saveParametrosConsumo(settings, tipoConvidado);
        super.onPause();
    }

    @Override
    public void onResume() {
        churrasco.restoreParametrosConsumo(settings, tipoConvidado);
        preencheEdits();
        super.onResume();
    }

    private void preencheEdits() {
        for (int i= Churrasco.CARNE; i<= Churrasco.REFRI; i++)
            edit[i].setIntValue(churrasco.getParametroConsumo(tipoConvidado, i));
    }
}
