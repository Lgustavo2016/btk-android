package com.lsandoval.btk_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lsandoval.btk_android.Helper.DatabaseHelper;
import com.lsandoval.btk_android.Model.UserBean;

public class UserDAO extends DatabaseHelper {

    public UserDAO(Context context) {
        super(context);
    }

    public boolean create(UserBean user) {
        try {
            final ContentValues values = new ContentValues();

            values.put("name", user.getName());
            values.put("email", user.getEmail());
            values.put("password", user.getPassword());

            SQLiteDatabase db = this.getWritableDatabase();

            final boolean hasCreated = db.insert("user", null, values) > 0;

            db.close();

            return hasCreated;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public UserBean authenticate(UserBean user) {
        try {
            final String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            final SQLiteDatabase db = this.getReadableDatabase();

            final Cursor cursor = db.rawQuery(sql, new String[]{user.getEmail(), user.getPassword()});

            if (cursor.moveToFirst()) {
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                return user;
            }
            return null;

        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
