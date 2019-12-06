package com.lsandoval.btk_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.lsandoval.btk_android.Helper.DatabaseHelper;
import com.lsandoval.btk_android.Model.UserBean;

import java.util.ArrayList;
import java.util.List;

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
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));

                db.close();
                cursor.close();

                return user;
            }

            db.close();
            cursor.close();

            return null;

        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public List<UserBean> listAll() {
        try {
            final String sql = "SELECT * FROM user";
            final SQLiteDatabase db = this.getReadableDatabase();

            final Cursor cursor = db.rawQuery(sql, new String[]{});
            final List<UserBean> users = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    UserBean user = new UserBean();

                    user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    user.setName(cursor.getString(cursor.getColumnIndex("name")));
                    user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                    users.add(user);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return users;

        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
