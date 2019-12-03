package com.lsandoval.btk_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lsandoval.btk_android.Helper.DatabaseHelper;
import com.lsandoval.btk_android.Model.GenderBean;

import java.util.ArrayList;
import java.util.List;

public class GenderDAO extends DatabaseHelper {

    private final String TABLE_NAME = "gender";

    public GenderDAO(Context context) {
        super(context);
    }

    public boolean create(GenderBean gender) {
        try {
            final ContentValues values = new ContentValues();

            values.put("name", gender.getName());
            values.put("description", gender.getDescription());

            final SQLiteDatabase db = this.getWritableDatabase();

            final boolean hasCreated = db.insert(this.TABLE_NAME, null, values) > 0;

            db.close();

            return hasCreated;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean update(GenderBean gender) {
        try {
            final SQLiteDatabase db = this.getWritableDatabase();
            final ContentValues values = new ContentValues();

            values.put("name", gender.getName());
            values.put("description", gender.getDescription());

            final boolean hasUpdated = db.update(this.TABLE_NAME, values, "id = ?", new String[]{String.valueOf(gender.getId())}) > 0;

            db.close();

            return hasUpdated;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean delete(int genderId) {
        try {
            final SQLiteDatabase db = this.getWritableDatabase();

            final boolean hasDeleted = db.delete(this.TABLE_NAME, "id = ?", new String[]{String.valueOf(genderId)}) > 0;

            db.close();

            return hasDeleted;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public List<GenderBean> listAll() {
        try {
            final String sql = "SELECT * FROM gender";
            final SQLiteDatabase db = this.getReadableDatabase();

            final Cursor cursor = db.rawQuery(sql, new String[]{});
            final List<GenderBean> genders = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    final GenderBean gender = new GenderBean();

                    gender.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    gender.setName(cursor.getString(cursor.getColumnIndex("name")));
                    gender.setDescription(cursor.getString(cursor.getColumnIndex("description")));

                    genders.add(gender);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return genders;

        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
