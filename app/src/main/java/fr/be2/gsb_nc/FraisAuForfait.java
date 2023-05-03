package fr.be2.gsb_nc;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class FraisAuForfait extends MainActivity {
    EditText quantité;
    Spinner typeForfait;
    String[] valeurs;
    TextView mSomme;
    SQLHelper database;
    CalendarView calendar;
    TextView datefrais;
    Float montantCalcule;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_au_forfait);
        typeForfait= findViewById(R.id.type_forfait);
        quantité= findViewById(R.id.quantite);
        valeurs= getResources().getStringArray(R.array.valeurForfait);
        mSomme= findViewById(R.id.somme);
        datefrais= findViewById(R.id.DateFrais);
        database= new SQLHelper(this);
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
}
    public void Monclick(View v){
        switch (v.getId()){
            case R.id.boutonajouter:

                // if (quantité.getText().toString().trim().length() == 0 || typeForfait.getSelectedItem().toString().length() ==0)

        }
        Integer quantite = Integer.parseInt(String.valueOf(quantité.getText()));
        String forfait = typeForfait.getSelectedItem().toString();
        montantCalcule = quantite * Float.parseFloat(mSomme.getText().toString());
        String madateforfait = datefrais.getText().toString();
        if (database.insertData(forfait, quantite, madateforfait, montantCalcule, forfait)) {
            afficherMessage("Succès", "Valeur ajoutée. " + "Montant= " + montantCalcule);
            return;
        }

    }
    public void ShowCal(View vv)
    {
        picker = new DatePickerDialog(FraisAuForfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        datefrais.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                },aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();
    }
}
