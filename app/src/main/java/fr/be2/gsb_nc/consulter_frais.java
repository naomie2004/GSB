package fr.be2.gsb_nc;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class consulter_frais extends MainActivity {
    private SQLHelper SQLHelper;
    private SimpleCursorAdapter dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_frais);

        SQLHelper = new SQLHelper(this);
        SQLHelper.open();

        //Nettoyer les données
        // SQLHelper.deleteAllFrais();
        //On ajoute des données
        // SQLHelper.insertSomeFrais();
        //Générer le ListView a partir de SQLite Database
        //displayListView();
    }

    private void displayListView() {


        Cursor cursor = SQLHelper.fetchAllFrais();

        // Les colonnes que l’on veut lier
        String[] columns = new String[] {
                SQLHelper.ID_FRAIS,
                SQLHelper.TYPEFRAIS,
                SQLHelper.QUANTITE,
                SQLHelper.DATEFRAIS
        };

        // Les éléments defnis dans le XML auxquels les données sont liées
        int[] to = new int[] {
                R.id.code,
                R.id.type,
                R.id.quantite,
                R.id.date,
        };


//On crée l'adaptateur à l'aide du curseur pointant sur les données souhaitées  ainsi que les informations de mise en page
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.activity_detail_frais,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Attribuer l’adapter au ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // On obtient le curseur, positionne sur la ligne correspondante dans le jeu de résultats
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                //On obtient l'id du frais en cliquant sur le frais
                String myId = cursor.getString(cursor.getColumnIndexOrThrow("ID"));
                Toast.makeText(getApplicationContext(), myId, Toast.LENGTH_SHORT).show();
                // SQLHelper.deleteData (parseInt (myId));

            }
        });

    }

}





