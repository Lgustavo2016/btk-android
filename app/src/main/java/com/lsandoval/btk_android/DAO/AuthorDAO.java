package com.lsandoval.btk_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lsandoval.btk_android.Helper.DatabaseHelper;
import com.lsandoval.btk_android.Model.AuthorBean;

import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends DatabaseHelper {

    private final String TABLE_NAME = "author";

    public AuthorDAO(Context context) {
        super(context);
    }

    public boolean create(AuthorBean author) {
        try {
            final ContentValues values = new ContentValues();

            values.put("name", author.getName());
            values.put("age", author.getAge());
            values.put("country", author.getCountry());

            final SQLiteDatabase db = this.getWritableDatabase();

            final boolean hasCreated = db.insert(this.TABLE_NAME, null, values) > 0;

            db.close();

            return hasCreated;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean update(AuthorBean author) {
        try {
            final SQLiteDatabase db = this.getWritableDatabase();
            final ContentValues values = new ContentValues();

            values.put("name", author.getName());
            values.put("age", author.getAge());
            values.put("country", author.getCountry());

            final boolean hasUpdated = db.update(this.TABLE_NAME, values, "id = ?", new String[]{String.valueOf(author.getId())}) > 0;

            db.close();

            return hasUpdated;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean delete(int authorId) {
        try {
            final SQLiteDatabase db = this.getWritableDatabase();

            final boolean hasDeleted = db.delete(this.TABLE_NAME, "id = ?", new String[]{String.valueOf(authorId)}) > 0;

            db.close();

            return hasDeleted;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public List<AuthorBean> listAll() {
        try {
            final String sql = "SELECT * FROM author";
            final SQLiteDatabase db = this.getReadableDatabase();

            final Cursor cursor = db.rawQuery(sql, new String[]{});
            final List<AuthorBean> authors = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    final AuthorBean author = new AuthorBean();

                    author.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    author.setName(cursor.getString(cursor.getColumnIndex("name")));
                    author.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                    author.setCountry(cursor.getString(cursor.getColumnIndex("country")));

                    authors.add(author);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return authors;

        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
