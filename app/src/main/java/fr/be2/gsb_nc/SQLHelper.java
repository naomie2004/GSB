package fr.be2.gsb_nc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SQLHelper extends SQLiteOpenHelper {
    //declaration des variables
    public static final String DB_NAME = "GSB.db";
    public static final String DB_TABLE = "FRAIS";
    public static final String ID_FRAIS = "ID_FRAIS"; //ce sera les colonnes de la table frais
    public static final String TYPEFRAIS = "TYPEFRAIS";
    public static final String QUANTITE = "QUANTITE";
    public static final String DATEFRAIS = "DATEFRAIS";
    public static final String MONTANT = "MONTANT";
    public static final String DATESAISIE = "DATESAISIE";
    public static final String LIBELLE = "LIBELLE";

    /**
     * Crée une table par une requete SQL
     */
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" + ID_FRAIS +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + TYPEFRAIS + " TEXT," + QUANTITE + " INTEGER," + DATEFRAIS
            + " TEXT," + MONTANT + " REAL," + LIBELLE + " TEXT," + DATESAISIE + " DATETIME DEFAULT CURRENT_TIMESTAMP)";


    private static final String CREATE_PARAMETRES="CREATE TABLE PARAMETRES(id int primary key,codev text ,nom text ," +
            "prenom text, email text , urlserveur text ,password text)";

    private static final String INIT_PARAMETRES="INSERT INTO PARAMETRES( ID, CODEV,NOM, PRENOM,EMAIL, URLSERVEUR) Values(1,0,'','','@','https://')";
    private static final String TAG = "SQLHelper";

    /**
     *
     * @param context
     */

    public SQLHelper(Context context) {

        super(context, DB_NAME, null, 1);//permet d'acceder aux membres de la classe mère

    }

    /**
     * constructeur de la classe
     * methode venant de SQLLite et permettant d'executer une requete SQL
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_PARAMETRES);
        sqLiteDatabase.execSQL(INIT_PARAMETRES);
    }



    /**
     * destructeur de la classe
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    /**
     * Insère dans le BDD les données type, quantité, date, montant et libellé renseignées par le visiteur
     * @param typeForfait
     * @param quantite
     * @param dateForfait
     * @param montant
     * @param libelle
     * @return booleen
     */
    public boolean insertData(String typeForfait, Integer quantite, String dateForfait, double montant, String libelle) {
        //on cree une variable de type sqLitedatabase pr pouvoir y acceder
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPEFRAIS, typeForfait);
        contentValues.put(QUANTITE, quantite);
        contentValues.put(DATEFRAIS, dateForfait);
        contentValues.put(MONTANT, montant);
        contentValues.put(LIBELLE, libelle);
        //insert sert a inserer des donnees, elle insere ds notre table contentValue les contenus
        // des variables que l'utilisateur renseigne
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;

    }

    public Cursor fetchAllFrais() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(DB_TABLE, new String[] { "rowid _id",DATEFRAIS,
                        MONTANT, DATESAISIE ,LIBELLE,ID_FRAIS},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor fetchFrais(String filtre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(DB_TABLE, new String[] { "rowid _id",DATEFRAIS,
                        MONTANT, DATESAISIE ,LIBELLE},
                filtre, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    public SQLHelper open() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        return this;

    }

    public boolean Update_Data(Integer Codev , String Nom , String Prenom, String Mail , String Urlserveur,String Password) {
        //on cree une variable de type sqLitedatabase pr pouvoir y acceder
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codev", Codev);
        contentValues.put("nom", Nom );
        contentValues.put("prenom", Prenom);
        contentValues.put("email", Mail);
        contentValues.put("urlserveur", Urlserveur);
        if (Password.toString().trim().length() > 0) {
            String hashedPassword = SQLHelper.hashPassword(Password.toString());
            contentValues.put("password", Password);

        }
        //insert sert a inserer des donnees, elle insere ds notre table contentValue les contenus
        // des variables que l'utilisateur renseigne
        long result = db.update("PARAMETRES",contentValues,"id=1",null);
        return result != -1;

    }



    /**
     * //fonction qui hache un mot de passe.
     * @param Password
     * @return
     */
    public static String hashPassword(String Password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteData(Integer ID_FRAIS){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(DB_TABLE, "ID_FRAIS=" + ID_FRAIS, null);

        return result != -1;
    }

}