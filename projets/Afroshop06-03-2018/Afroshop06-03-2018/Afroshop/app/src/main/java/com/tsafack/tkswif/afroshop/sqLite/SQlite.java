package com.tsafack.tkswif.afroshop.sqLite;

/**
 * Created by Tsafack Cedrick on 23/07/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class SQlite extends SQLiteOpenHelper {

	SQLiteDatabase db;
	
	protected static final String TAG = SQlite.class.getSimpleName();
	
	//les variables statiques
	//le nom de la base de donnee
	private static final String DATABASE_NAME = "afroshop";
	
	// version de la base de donnee
    private static final int DATABASE_VERSION = 1;
	
	//table categorie
	public static final String TABLE_CATEGORIE_ARTICLE = "categorie_article";
	
	public static final String KEY_ID_CATEGORIE_ARTICLE = "id_categorie_article";
	public static final String KEY_LIBELLE_CATEGORIE_ARTICLE = "libelle_categorie_article";
	public static final String KEY_TYPE_CATEGORIE_ARTICLE = "type_categorie_article";
	public static final String KEY_DATE_CREATION_CATEGORIE_ARTICLE = "date_creation_categorie_article";
	public static final String KEY_IMAGE_CATEGORIE_ARTICLE = "image_categorie_article";
	
	public SQlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	// creation des tables
    @Override
        
	public void onCreate(SQLiteDatabase db) {

        // création de la table des categories
        String CREATE_CATEGORIE_ARTICLE_TABLE = "CREATE TABLE " + TABLE_CATEGORIE_ARTICLE + "("
            + KEY_ID_CATEGORIE_ARTICLE + " TEXT PRIMARY KEY," + KEY_LIBELLE_CATEGORIE_ARTICLE + " TEXT,"
            + KEY_TYPE_CATEGORIE_ARTICLE + " TEXT," + KEY_DATE_CREATION_CATEGORIE_ARTICLE + " TEXT," + KEY_IMAGE_CATEGORIE_ARTICLE + " TEXT " + ");";
        db.execSQL(CREATE_CATEGORIE_ARTICLE_TABLE);

        Log.d(TAG, "table categorie article cree dans la base de donnee");

    }
	
	// Update DataBase
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIE_ARTICLE);
        // Create tables again
        onCreate(db);
    }

	public void write () {
		db = this.getWritableDatabase();
	}

	public void read() {
		db = this.getReadableDatabase();
	}
	
}