package cd.be3c.m4gazinus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cd.be3c.m4gazinus.model.User;

/**
 * @author Michelo
 * @version 0.1.0
 * @date 01/06/2017
 */

public class MagazinusDatabase extends SQLiteOpenHelper{


    //Database version
    private static final int DATABASE_VERSION=1;
    //Database name
    private static final String DATABASE_NAME="m4gazinus.db";
    //User table name
    private static final String TABLE_USER="user";
    //User Table colums names
    private static final String COL_NAME="name";
    private static final String COL_EMAIL="email";
    private static final String COL_PASSWORD="password";
    //Create table sql query
    private String CREATE_USER_TABLE="CREATE TABLE " + TABLE_USER + "("
            + COL_NAME + " TEXT PRIMARY KEY, " + COL_EMAIL + " TEXT, " + COL_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE="DROP TABME IF EXISTS " + TABLE_USER;



    public MagazinusDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        //create table again
        onCreate(db);
    }


    //Création des methodes sur les données

    //Ajout des utilisateurs

    public void AjoutUtilisateur(User user){
        SQLiteDatabase data=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(COL_NAME,user.getName());
        values.put(COL_EMAIL,user.getEmail());
        values.put(COL_PASSWORD,user.getPassword());

        //inserer la ligne dans la bd
        data.insert(TABLE_USER,null,values);
        data.close();

    }

    public boolean checkIfUserExist(String email, String password){

        SQLiteDatabase data=this.getReadableDatabase();
        //selection en fonction des criteres
        String criteria=COL_EMAIL + " = ?" + " AND " + COL_PASSWORD+ " = ?";
        //les arguments
        String [] selectionArgs={email,password};

        String[] column={ COL_NAME };

        Cursor cursor=data.query(TABLE_USER,
                column,             //Colonne à retourner
                criteria,           //Where clause -> Critere
                selectionArgs,      //valeur de la clause Where
                null,               //group by
                null,               // filtre (having)
                null                // Order by
                );

        int cursorCount=cursor.getCount();
        cursor.close();
        data.close();

        if(cursorCount > 0){
            return  true;
        }
        return false;

    }


    public boolean checkIfEMailExist(String email){

        SQLiteDatabase data=this.getReadableDatabase();
        //selection en fonction des criteres
        String criteria=COL_EMAIL + " = ?" ;
        //les arguments
        String [] selectionArgs={email};

        String[] column={ COL_NAME };

        Cursor cursor=data.query(TABLE_USER,
                column,             //Colonne à retourner
                criteria,           //Where clause -> Critere
                selectionArgs,      //valeur de la clause Where
                null,               //group by
                null,               // filtre (having)
                null                // Order by
        );

        int cursorCount=cursor.getCount();
        cursor.close();
        data.close();

        if(cursorCount > 0){
            return  true;
        }
        return false;

    }


}
