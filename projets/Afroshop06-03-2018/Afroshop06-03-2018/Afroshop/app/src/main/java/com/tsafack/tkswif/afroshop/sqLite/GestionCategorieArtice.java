package com.tsafack.tkswif.afroshop.sqLite;

/**
 * Created by Tsafack Cedrick on 23/07/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tsafack.tkswif.afroshop.entities.entitiesBD.CategorieArticle;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class GestionCategorieArtice {
	

	SQlite sqlite;
	
	public GestionCategorieArtice (Context context){
		sqlite = new SQlite(context);
	}
	
	public void addCategorieArticle (CategorieArticle categorieArticle){
		sqlite.write();
		
		ContentValues values = new ContentValues();
		values.put(SQlite.KEY_ID_CATEGORIE_ARTICLE, categorieArticle.getIdCategorieArticle());
		values.put(SQlite.KEY_LIBELLE_CATEGORIE_ARTICLE, categorieArticle.getLibelleCategorieArticle());
		values.put(SQlite.KEY_TYPE_CATEGORIE_ARTICLE, categorieArticle.getTypeCategorieArticle());
		values.put(SQlite.KEY_DATE_CREATION_CATEGORIE_ARTICLE, String.valueOf(categorieArticle.getDateCreationCatégorieArticle()));
		values.put(SQlite.KEY_IMAGE_CATEGORIE_ARTICLE, categorieArticle.getImageCategorieArticle());

		long id = sqlite.db.insert(SQlite.TABLE_CATEGORIE_ARTICLE, null, values);
		sqlite.db.close();
		Log.d(SQlite.TAG, "une nouvelle categorie d'article enregistree dans SQLite " + id);
	}

	public void modifierCategorieArticle (String id, CategorieArticle categorieArticle){

		sqlite.write();

		ContentValues values = new ContentValues();
		values.put(SQlite.KEY_LIBELLE_CATEGORIE_ARTICLE, categorieArticle.getLibelleCategorieArticle());
		values.put(SQlite.KEY_DATE_CREATION_CATEGORIE_ARTICLE, String.valueOf(categorieArticle.getDateCreationCatégorieArticle()));
		values.put(SQlite.KEY_IMAGE_CATEGORIE_ARTICLE, categorieArticle.getImageCategorieArticle());
		values.put(SQlite.KEY_TYPE_CATEGORIE_ARTICLE, categorieArticle.getTypeCategorieArticle());

		long idl = sqlite.db.update(SQlite.TABLE_CATEGORIE_ARTICLE, values, SQlite.KEY_ID_CATEGORIE_ARTICLE + " = ?", new String[]{id});
		sqlite.db.close();
		Log.d(TAG, "modification d'une categorie d'article " + idl);
	}

	public ArrayList<CategorieArticle> getCategorieArticles (){
		List<CategorieArticle> categorieArticleList = new ArrayList<>();

		String query = "SELECT * FROM " + SQlite.TABLE_CATEGORIE_ARTICLE;
		sqlite.read();

		Cursor cursor = sqlite.db.rawQuery(query, null);
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			CategorieArticle categorieArticle = new CategorieArticle();
			categorieArticle.setIdCategorieArticle(cursor.getString(cursor.getColumnIndex(SQlite.KEY_ID_CATEGORIE_ARTICLE)));
			categorieArticle.setLibelleCategorieArticle(cursor.getString(cursor.getColumnIndex(SQlite.KEY_LIBELLE_CATEGORIE_ARTICLE)));
			categorieArticle.setTypeCategorieArticle(cursor.getString(cursor.getColumnIndex(SQlite.KEY_TYPE_CATEGORIE_ARTICLE)));
//			categorieArticle.setDateCreationCategorieArticle(Date.valueOf(cursor.getString(cursor.getColumnIndex(SQlite.KEY_DATE_CREATION_CATEGORIE_ARTICLE))));
			categorieArticle.setImageCategorieArticle(cursor.getString(cursor.getColumnIndex(SQlite.KEY_IMAGE_CATEGORIE_ARTICLE)));

			categorieArticleList.add(categorieArticle);
		}
		cursor.close();
		sqlite.db.close();

		return (ArrayList<CategorieArticle>) categorieArticleList;
	}

	public void deleteCategorieArticle (String id){
		sqlite.write();
		long idl = sqlite.db.delete(SQlite.TABLE_CATEGORIE_ARTICLE, SQlite.KEY_ID_CATEGORIE_ARTICLE + " = ?", new String[]{id});
		sqlite.db.close();
		Log.d(TAG, "suppression d'une categorie d'article " + idl);
	}


	public void deleteCategorieArticle(){
		sqlite.write();

		sqlite.db.delete(SQlite.TABLE_CATEGORIE_ARTICLE, null, null);
		sqlite.db.close();

		Log.d(SQlite.TAG, "les categories d'articles sont supprimees");
	}
}