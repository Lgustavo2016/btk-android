package com.lsandoval.btk_android.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // region database configs
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "btk.db";
    // endregion

    // region database tables
    private static final String[] DATABASE_TABLES = {
            "user",
            "author",
            "gender",
            "book",
            "loan"
    };

    private static final String[] CREATE_DATABASE_TABLES = {
            "CREATE TABLE user " +
                    "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR NOT NULL, " +
                    "email VARCHAR NOT NULL, " +
                    "password VARCHAR NOT NULL )",
            "CREATE TABLE author " +
                    "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR NOT NULL, " +
                    "age INTEGER, " +
                    "country VARCHAR)",
            "CREATE TABLE gender " +
                    "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR NOT NULL, " +
                    "description VARCHAR )",
            "CREATE TABLE book " +
                    "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR NOT NULL, " +
                    "publishingCompany VARCHAR NOT NULL, " +
                    "quantity INTEGER NOT NULL, " +
                    "description VARCHAR, " +
                    "author_id INTEGER NOT NULL, " +
                    "gender_id INTEGER NOT NULL, " +
                    "CONSTRAINT author_id " +
                    "FOREIGN KEY (author_id) " +
                    "REFERENCES author(id), " +
                    "CONSTRAINT gender_id " +
                    "FOREIGN KEY (gender_id) " +
                    "REFERENCES gender(id)) ",
            "CREATE TABLE loan" +
                    "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "loanStatus VARCHAR NOT NULL, " +
                    "loanDate DATE NOT NULL, " +
                    "returnDate DATE NOT NULL, " +
                    "renter_id INTEGER NOT NULL, " +
                    "lessee_id INTEGER NOT NULL, " +
                    "book_id INTEGER NOT NULL, " +
                    "CONSTRAINT renter_id " +
                    "FOREIGN KEY (renter_id) " +
                    "REFERENCES user(id), " +
                    "CONSTRAINT lessee_id " +
                    "FOREIGN KEY (lessee) " +
                    "REFERENCES user(id)) " +
                    "CONSTRAINT book_id " +
                    "FOREIGN KEY (book_id) " +
                    "REFERENCES book(id)"
    };
    // endregion

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (final String createTableQuery : CREATE_DATABASE_TABLES) db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (final String table : DATABASE_TABLES) db.execSQL(String.format("DROP TABLE IF EXISTS %s", table));
    }
}
