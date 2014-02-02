package br.com.brestate.top100appitunes.dao;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABELA = "top100";
	public static final String COLUNA_ID = "_id";
	public static final String COLUMN_COMMENT = "name";

	private static final String DATABASE_NAME = "topitunes.db";
	private static final int DATABASE_VERSION = 1;

	// Criando o Database
	private static final String DATABASE_CREATE = "create table " + TABELA
			+ "(" + COLUNA_ID + " integer primary key autoincrement, "
			+ COLUMN_COMMENT + " text not null);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABELA);
		onCreate(db);
	}
}