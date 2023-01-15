package fr.be2.gsb;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FraisAuForfait extends MainActivity {
    EditText quantité;
    Spinner typeForfait;
    String[] valeurs;
    TextView mSomme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_au_forfait);
        typeForfait= findViewById(R.id.type_forfait);
        quantité= findViewById(R.id.quantite);
        valeurs= getResources().getStringArray(R.array.valeurForfait);
        mSomme = findViewById(R.id.somme);
        quantité.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This is where we'll check the user input
                Integer q1 = Integer.parseInt(String.valueOf("0" + quantité.getText()));
                //String f1 = typeForfait.getSelectedItem().toString();
                int posF1 = typeForfait.getSelectedItemPosition();
                Float s1 = q1 * Float.parseFloat(valeurs[posF1]);
                mSomme.setText(s1.toString());
            }
        });
}}