package fr.be2.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class se_connecter extends MainActivity {
    EditText codevisiteur;
    LinearLayout verification;
    EditText mail;
    EditText codeverif;
    Integer codeAleatoire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_connecter);
        codevisiteur = findViewById(R.id.connectercv);
        verification = findViewById(R.id.layout);
        mail = findViewById(R.id.mail);

    }
    public void click_code(View v) {
            Random r = new Random();
            int min = 1000;
            int max = 9999;
            codeAleatoire = r.nextInt((max - min) + 1) + min;
            Toast.makeText(this, "code=" + codeAleatoire.toString(), Toast.LENGTH_SHORT).show();
            verification.setVisibility(View.VISIBLE);
    }
    public void verif(View v){
        String codeAleatoireStr= codeAleatoire.toString();
        String codeverifStr= codeverif.getText().toString();
        if (codeAleatoireStr.equals(codeverifStr)){
            Toast.makeText(this, "Réussite", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
        }
    }

}