package fr.be2.gsb_nc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class se_connecter extends AppCompatActivity {
    EditText codevisiteur;
    LinearLayout verification;
    EditText mail;
    EditText codeverif;
    Integer codeAleatoire;
    private static final String MON_FICHIER = "GSB_pref_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_connecter);
        codevisiteur = findViewById(R.id.connectercv);
        verification = findViewById(R.id.layout);
        mail = findViewById(R.id.mail);
        codeverif= findViewById(R.id.codeverif);
    }
    public void click_code(View v) {
            Random r = new Random();
            int min = 1000;
            int max = 9999;
            codeAleatoire = r.nextInt((max - min) + 1) + min;
            Toast.makeText(this, "code=" + codeAleatoire.toString(), Toast.LENGTH_SHORT).show();
            verification.setVisibility(View.VISIBLE);
    }
    public void valide_code(View v){
        String codeAleatoireStr= codeAleatoire.toString();
        String codeverifStr= codeverif.getText().toString();
        if (codeAleatoireStr.equals(codeverifStr)){
            Toast.makeText(this, "Réussite", Toast.LENGTH_SHORT).show();
            getSharedPreferences(MON_FICHIER, MODE_PRIVATE)
                    .edit()
                    .putString("codevisiteur", codevisiteur.getText().toString())
                    .putString("mail", mail.getText().toString())
                    .apply();
        }else{
            Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
        }
    }
}