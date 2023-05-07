package fr.be2.gsb_nc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    EditText codevisiteur;
    Context context;
    private static final String MON_FICHIER = "GSB_PREF_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secure();
    }
    public void click_Menu(View v) {
        String msg= "";
        switch (v.getId()){
            case R.id.main_button_fraisauforfait:
               // msg= "bouton frais forfait";
                intent = new Intent(MainActivity.this, FraisAuForfait.class);
                break;
            case R.id.main_button_fraishorsforfait:
                //msg= "bouton frais hors forfait";
                intent = new Intent(MainActivity.this, FraisHorsForfait.class);
                break;
            case R.id.main_button_synthesedumois:
                //msg= "synthèse du mois";
                intent = new Intent(MainActivity.this, synthese_mois.class);
                break;
            case R.id.main_button_parametres:
                //msg= "paramètres";
                intent = new Intent(MainActivity.this, parametres.class);
                break;
            case R.id.connexion:
                intent= new Intent(MainActivity.this,se_connecter.class);
                context.getSharedPreferences("PreferencesName", Context.MODE_PRIVATE).edit().clear().commit();
                break;
     }
        startActivity(intent);
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

}
    public void fermer(View v){
        this.finish();
    }
    public void secure(){
        String cvisiteur= getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("CodeVisiteur","pas authentifie");
        if (cvisiteur.equals("pas authentifie")) {
            Intent intent = new Intent(MainActivity.this,se_connecter.class);
            startActivity(intent);
        }}

    public void afficherMessage(String titre, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //classe qui constuit une boite de dialogue
        builder.setCancelable(true); //pr que la boite de dialogue soit refermable
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show();

    }



}
