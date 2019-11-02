package com.lsandoval.btk_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lsandoval.btk_android.Helper.DatabaseHelper;
import com.lsandoval.btk_android.Model.UserBean;

public class UserDAO extends DatabaseHelper {

    public UserDAO(Context context){
        super(context);
    }

    public boolean create(UserBean user) {
        ContentValues values = new ContentValues();

        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean created = db.insert("user", null, values) > 0;
        db.close();

        return created;
    }

}
