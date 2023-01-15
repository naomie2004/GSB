package fr.be2.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.androidgamesdk.GameActivity;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                break;
     }
        startActivity(intent);
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();



}
    public void fermer(View v){
        this.finish();
    }
}
